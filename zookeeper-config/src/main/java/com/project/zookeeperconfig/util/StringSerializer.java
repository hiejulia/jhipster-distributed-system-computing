package com.project.zookeeperconfig.util;


import java.nio.charset.StandardCharsets;
import org.I0Itec.zkclient.serialize.ZkSerializer;

public class StringSerializer implements ZkSerializer {

    @Override
    public byte[] serialize(Object data) {
        return ((String) data).getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}