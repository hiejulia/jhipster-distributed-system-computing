package com.example.mastercrawl;


import java.io.Serializable;

public class Command implements Serializable{
    private static final long serialVersionUID = 1L;

    public static final int CMD_START = 1000;
    public static final int CMD_DISPATCH_TASK = 1001;
    public static final int CMD_STOP = 1002;
    public static final int CMD_RECALL_FILE = 1003;
    public static final int CMD_PAUSE = 1004;
    public static final int CMD_RESTART = 1005;
    public static final int CMD_MSG = 1006;
    public static final int CMD_WRITE_URL = 1007;

    private int type;
    private String info;

    public Command(int type, String info) {
        this.type = type;
        this.info = info;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }


}
