package com.example.mastercrawl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


import javax.swing.table.DefaultTableModel;


import com.example.mastercrawl.consistentHash.ConsistentHash;
import com.example.mastercrawl.consistentHash.HashFunc;
import com.example.mastercrawl.consistentHash.ServerNode;
import com.example.mastercrawl.distributed.Handler;


public class Index {

    private SocketServer socketServer;
    private DefaultTableModel tableModel;
    private Map<String, Integer> slaveIdMap;
    private ArrayList<String> seedsList;
    private ArrayList<String> selectedSlaveList;
    private ConsistentHash<ServerNode> consistentHash;

    public Index() {
        try {
            this.socketServer = new SocketServer(9090);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initData();

        bindAsyncEvent();
    }



    private void initData(){
        //initialize table model
        tableModel = new DefaultTableModel();
        String header[] = new String[] { "Status", "IP", "Port", };
        tableModel.setColumnIdentifiers(header);

        //initialize slaveId map
        slaveIdMap = new HashMap<String, Integer>();
        consistentHash = new ConsistentHash<ServerNode>(new HashFunc(), 1000);
        seedsList = new ArrayList<String>();
        selectedSlaveList = new ArrayList<String>();
    }

    private void bindAsyncEvent(){
        //listening slave socket
        socketServer.setOnAsyncTaskListener(new OnAsyncTaskListener() {

            @Override
            public void onAccept(String slaveId, Handler handler) {
                tableModel.addRow(new Object[] { true, handler.getSocket().getInetAddress().getHostAddress(), handler.getSocket().getPort()});
                // slave ID
                slaveIdMap.put(slaveId, slaveIdMap.size());
            }

            @Override
            public void onReceive(Handler handler, Command command) {
            }

            @Override
            public void onClose(String slaveId) {
                socketServer.removeSlave(slaveId);
                tableModel.removeRow(slaveIdMap.get(slaveId));
                slaveIdMap.remove(slaveId);
            }

        });

    }


    private void loadSlave(){
        //select the checked slave node
        int row = tableModel.getRowCount();
        consistentHash.clear();
        selectedSlaveList.clear();
        for (int i = 0; i < row; i++) {
            boolean isSlected = (boolean)tableModel.getValueAt(i, 0);
            if(isSlected){
                String ip = (String)tableModel.getValueAt(i, 1);
                int port = (int)tableModel.getValueAt(i, 2);
                ServerNode node = new ServerNode(ip, port);
                consistentHash.add(node);
                selectedSlaveList.add(node.toString());
            }
        }
    }


    private void loadSeeds(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("./data/movies.txt")));
            String buff = null;
            while((buff = reader.readLine()) != null){
                seedsList.add(buff);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void dispatchUrl(){
        for (String surl : seedsList) {
            ServerNode node = consistentHash.get(surl);
            String slaveId = node.toString();
            Command cmd = new Command(Command.CMD_DISPATCH_TASK, surl);
            socketServer.send(slaveId, cmd);
            System.out.println(slaveId);
        }
    }
}
