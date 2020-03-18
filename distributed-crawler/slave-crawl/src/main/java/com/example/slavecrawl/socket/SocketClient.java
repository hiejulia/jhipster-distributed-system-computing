package com.example.slavecrawl.socket;



import com.example.slavecrawl.distributed.Handler;
import com.example.slavecrawl.model.Command;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class SocketClient {

    private String masterIp = "127.0.0.1";
    private int masterPort = 9090;

    private Socket serverSocket = null;
    private Handler handler = null;

    public SocketClient(){

    }
    public SocketClient(String masterIp, int masterPort) throws UnknownHostException, IOException{
        this.masterIp = masterIp;
        this.masterPort = masterPort;
        this.serverSocket = new Socket(masterIp, masterPort);
        this.handler = new Handler(serverSocket);
    }

    public void setMaster(String masterIp, int masterPort){
        this.masterIp = masterIp;
        this.masterPort = masterPort;
    }


    public void start(){
        new Thread(this.handler).start();
    }


    public void setOnAsyncTaskListener(Handler.OnAsyncTaskListener onAsyncTaskListener){
        handler.setOnAsyncTaskListener(onAsyncTaskListener);
    }


    public boolean send(Command command) {
        return handler.send(command);
    }

    public Handler getHandler(){
        return handler;
    }


}
