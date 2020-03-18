package com.example.mastercrawl;


import java.io.Serializable;

public class Command implements Serializable{
    private static final long serialVersionUID = 1L;


    public static final int CMD_DISPATCH_TASK = 1001;


    private int type;
    private String info;

    public Command(int type, String info) {
        this.type = type;
        this.info = info;
    }





}
