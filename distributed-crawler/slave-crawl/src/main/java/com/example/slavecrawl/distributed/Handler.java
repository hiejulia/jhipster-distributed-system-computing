package com.example.slavecrawl.distributed;


import com.example.slavecrawl.model.Command;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

// Handler - Slave
public class Handler implements Runnable{

    private Socket socket = null;

    private InetAddress inetAddress = null;

    // Socket Thread Pool
    private String serverId = null;

    private OnAsyncTaskListener onAsyncTaskListener = null;

    public Handler(Socket socket) {
        this.socket = socket;
        //
        this.inetAddress = socket.getInetAddress();
        //
        this.serverId = inetAddress.getHostAddress() + ":" + socket.getPort();
    }

    public Socket getSocket(){
        return this.socket;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public String getServerId() {
        return serverId;
    }


    public void setOnAsyncTaskListener(OnAsyncTaskListener onAsyncTaskListener){
        this.onAsyncTaskListener = onAsyncTaskListener;
    }


    // SEND
    public boolean send(Command command) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));

            outputStream.writeObject(command);
            outputStream.flush();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(
                socket.getInputStream());

            Object obj = null;

            while ((obj = inputStream.readObject()) != null) {
                Command command = (Command) obj;
                if (onAsyncTaskListener != null) {
                    onAsyncTaskListener.onReceive(Handler.this, command);
                }

                try{
                    inputStream = new ObjectInputStream(socket.getInputStream());
                }catch(EOFException e){
                    continue;
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        onAsyncTaskListener.onClose(serverId);
    }

    public interface OnAsyncTaskListener {


        public void onReceive(Handler handler, Command command);

        public void onClose(String serverId);
    }
}
