package com.example.mastercrawl.distributed;

import com.example.mastercrawl.Command;

import java.io.BufferedOutputStream;

import java.io.EOFException;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

// Handler - Slave
public class Handler implements Runnable{

    private Socket socket = null;

    private InetAddress inetAddress = null;

    // socket thread pool
    private String serverId = null;

    private OnAsyncTaskListener onAsyncTaskListener = null;

    public Handler(Socket socket) {
        this.socket = socket;
        this.inetAddress = socket.getInetAddress();
        this.serverId = inetAddress.getHostAddress() + ":" + socket.getPort();
    }

    public Socket getSocket(){
        return this.socket;
    }


    public void setOnAsyncTaskListener(OnAsyncTaskListener onAsyncTaskListener){
        this.onAsyncTaskListener = onAsyncTaskListener;
    }

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
                    System.out.println(this.serverId + "---closed");
                    e.printStackTrace();
                }
            }
        }

        onAsyncTaskListener.onClose(serverId);
    }

    public interface OnAsyncTaskListener {


        public void onAccept(String slaveId, Handler handler);

        public void onReceive(Handler handler, Command command);

        public void onClose(String slaveId);
    }
}
