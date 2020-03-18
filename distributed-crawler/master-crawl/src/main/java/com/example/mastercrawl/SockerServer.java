package com.example.mastercrawl;


import com.example.mastercrawl.distributed.Handler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SockerServer {

    private int port = 9090;
    private ServerSocket serverSocket = null;
    private ExecutorService threadPool = null;

    private HashMap<String, Handler> slaveMap = null;

    private Handler.OnAsyncTaskListener onAsyncTaskListener = null;

    private boolean isRunning = false;

    public SockerServer(int port) throws IOException{
        this.port = port;
        this.threadPool = Executors.newCachedThreadPool();
        this.serverSocket = new ServerSocket(port);
        this.slaveMap = new HashMap<String, Handler>();
    }

    public HashMap<String, Handler> getSlaveMap(){
        return this.slaveMap;
    }


    public void setOnAsyncTaskListener(Handler.OnAsyncTaskListener onAsyncTaskListener){
        this.onAsyncTaskListener = onAsyncTaskListener;
    }


    // send - slaveId -
    public boolean send(String slaveId, Command command){
        Handler handler = this.slaveMap.get(slaveId);
        return handler.send(command);
    }

    // send ALl to slave

    public void sendAll(Command command){
        Iterator<String> iter = this.slaveMap.keySet().iterator();
        while(iter.hasNext()){
            String key = iter.next();
            Handler handler = slaveMap.get(key);
            handler.send(command);
        }
    }


    public void removeSlave(String slaveId){
        this.slaveMap.remove(slaveId);
    }


    public void start(){
        isRunning = true;

        new Thread(){
            public void run(){

                while(isRunning){
                    try {
                        Socket socket = serverSocket.accept();
                        Handler handler = new Handler(socket);
                        handler.setOnAsyncTaskListener(onAsyncTaskListener);

                        InetAddress address = socket.getInetAddress();
                        String key = address.getHostAddress() + ":" + socket.getPort();



                        // Distributed to slave
                        slaveMap.put(key, handler);
                        if(onAsyncTaskListener != null){
                            onAsyncTaskListener.onAccept(key, handler);
                        }

                        threadPool.execute(handler);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


}
