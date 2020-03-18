package com.example.slavecrawl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.slavecrawl.model.WebPage;
import redis.clients.jedis.Jedis;


public abstract class BaseCrawler {
    private final static int TASK_NUM = 10;

    // Url - key - url - MD5
    private ConcurrentHashMap<String, Integer> urlDeeps;

    protected Jedis jedis;

    // Wait list
    private LinkedList<String> waitList;

    // Executor Framework
    private ExecutorService taskPool;

    private String charset = "utf-8";

    private String domain = "";
    private int crawlerDeeps = 2;
    /*
    private int crawlerDeeps = 2;

    private int delay = 200;

    private boolean isRunning = false;

    private boolean isPaused = false;

    public BaseCrawler(){
        urlDeeps = new ConcurrentHashMap<String, Integer>();
        waitList =  new LinkedList<String>();
        taskPool = Executors.newCachedThreadPool();
        jedis = RedisUtil.getInstance();
    }


    public void initSeeds(){
        reset();
        loadWaitList();
    }

    public void start(){
        this.isRunning = false;
        this.isPaused = false;
        doCrawl();
    }

    public void pause(){
        this.isRunning = false;
        this.isPaused = true;
    }

    public void stop(){
        this.isRunning = false;
        this.isPaused = true;
        this.waitList.clear();
    }

    public void reset(){
        this.isRunning = false;
        this.isPaused = false;
        this.waitList.clear();
    }


    private void doCrawl(){
        if (isRunning) {
            return;
        }
        new Thread(){
            @Override
            public void run(){
                isRunning = true;
                System.out.println("----------启动线程----------------------");
                while(!waitList.isEmpty() && !isPaused){
                    String url = popWaitList();
                    taskPool.execute(new ProcessThread(url));
                    try {
                        Thread.sleep(delay);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                isRunning = false;
            }
        }.start();
    }

    /**
     * waitList
     * @return
     */
    public synchronized String popWaitList(){
        String temp = waitList.poll();
        return temp;
    }


    // Add wailist to Redis - url - unique key
    public synchronized void addWaitList(String url, String uniqueKey){
        // check if url is already
        if(!jedis.exists(uniqueKey)){
            waitList.offer(url);
            jedis.set(uniqueKey, 0+"");
            return;
        }
        // url
        if(jedis.get(uniqueKey).equals("0") && !waitList.contains(url)){
            waitList.offer(url);
        }
    }


    // add unvisited url : to Redis
    public synchronized void addUnVisitPath(String uniqueKey){
        jedis.set(uniqueKey, 0+"");
    }


    // Visited url - unique key : md5 - url
    public synchronized void visitUrl(String uniqueKey){
        jedis.set(uniqueKey, 1+"");
    }


    // get url
    public ConcurrentHashMap<String, Integer> getUrlDeeps(){
        return this.urlDeeps;
    }

    // Abstract class to extract Web Page
    public abstract void exactor(WebPage webPage);


    public abstract void loadWaitList();


    public void setCharset(String charset){
        this.charset = charset;
    }


    public void setDomain(String domain){
        this.domain = domain;
    }


    public void setCrawlerDeeps(int deeps){
        if(deeps >= 0){
            this.crawlerDeeps = deeps;
        }
    }


    public class ProcessThread implements Runnable{

        private String url;
        public ProcessThread(String url){
            this.url = url;
        }

        @Override
        public void run() {
            // Thread run task - get URL
                // Use HTTP get
            HttpUtil httpUtil = new HttpUtil();
            httpUtil.setCharset(charset);
            String pageContent = httpUtil.get(url);
            WebPage webPage;
            try {
                webPage = new WebPage(pageContent, new URL(url));
                exactor(webPage);
            } catch (MalformedURLException e) {
                exactor(null);
                e.printStackTrace();
            }

            if(!waitList.isEmpty() && !isRunning && !isPaused){
                doCrawl();
            }
        }
    }

}
