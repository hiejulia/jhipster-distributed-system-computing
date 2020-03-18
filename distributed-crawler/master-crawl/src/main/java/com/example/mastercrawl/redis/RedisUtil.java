package com.example.mastercrawl.redis;


import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import com.example.mastercrawl.util.PageUtil;
import redis.clients.jedis.Jedis;

public class RedisUtil {
    // Client
    static Jedis jedis = null;


    private RedisUtil(){

    }

    // Get instance
    public static Jedis getInstance(){
        if(jedis == null){
            // File in /resources
            String[] connstr = PageUtil.readFile(new File("./redis.conf")).split(":");
            String ip = connstr[0];
            int port = Integer.parseInt(connstr[1]);
            jedis = new Jedis(ip, port);
        }
        return jedis;
    }

    public static Jedis getInstance(String ip, int port){
        if(jedis == null){
            jedis = new Jedis(ip, port);
        }
        return jedis;
    }

    public static void close(){
        if(jedis != null){
            jedis.disconnect();
            jedis = null;
        }
    }

    // Get unvisited Url
    public static void getUnVisistedUrl() throws Exception{
        Jedis jedis = RedisUtil.getInstance();
        Set<String> keySet = jedis.keys("*");
        Iterator<String> iterator = keySet.iterator();
        PrintWriter writer = new PrintWriter("data/unvisited.txt");
        int count = 0;
        while(iterator.hasNext()){
            String key = iterator.next();
            if(jedis.get(key).equals("0")){
                count ++;
                String surl = key.substring(0, key.lastIndexOf("-"));
                writer.println(surl);
            }
        }
        writer.flush();
        writer.close();
        System.out.println(count);
    }
}
