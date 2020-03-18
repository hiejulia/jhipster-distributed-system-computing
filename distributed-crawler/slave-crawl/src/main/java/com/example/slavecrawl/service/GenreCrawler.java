package com.example.slavecrawl.service;


import com.example.slavecrawl.model.WebPage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;



public class GenreCrawler {

    private final int listRows = 16;

    private long timestamp ;

    public GenreCrawler(){
        super();
        this.timestamp = System.currentTimeMillis();
        this.setDomain("http://google.com");
    }

    // Generate url - genre
    public String generateUrl(String surl, int cid, int page){
        String url = "http://google.com/genre/ajax/?format=ajax&";
        url  += "surl=" + surl+ "&cid=" + cid + "&pn=" + page + "&t=" + this.timestamp;
        return url;
    }

    /**
     * url - page
     */
    public String generateUrl(String surl, int page){
        return this.generateUrl(surl, 0, page);
    }

    // Load wait list
    @Override
    public void loadWaitList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./data/baidu-scene.txt"));
            String tmpStr = null;
            while((tmpStr = reader.readLine()) != null){
                if (!tmpStr.startsWith("#")) {
                    String url = this.generateUrl(tmpStr, 1);
                    String uniqueKey = tmpStr + "-1";
                    super.addWaitList(url, uniqueKey);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // START EXTRACT - PAGE
    @Override
    public void exactor(WebPage page) {
        // validate
        if (page == null) {
            return;
        }
        String result = page.getPageContent();
        try {
            // format
            result = AppUtil.jsonFormatter(result);
            JSONObject jsonObj = JSONObject.fromObject(result);
            JSONObject dataObj = jsonObj.getJSONObject("data");

            String sid = dataObj.getString("sid");
            String surl = dataObj.getString("surl");
            String sname = dataObj.getString("sname");

//			String star = dataObj.getString("star");

//			double rating = dataObj.getDouble("rating");
//			int ratingCount = dataObj.getInt("rating_count");
            JSONObject extObj = dataObj.getJSONObject("ext");

            int sceneTotal = dataObj.getInt("genre_total");
            int currentPage = dataObj.getInt("current_page");

            // ulr
            super.visitUrl(surl + "-" + currentPage);


            int pageNums = (int) Math.ceil((double)sceneTotal / listRows);
            for(int i=currentPage+1; i<=pageNums; i++){
                // url
                String uniqueKey = surl + "-" + i;
                // generate url
                String tmpUrl = this.generateUrl(surl, i);
                // add wait list
                addWaitList(tmpUrl, uniqueKey);
            }


            JSONArray sceneList = dataObj.getJSONArray("scene_list");
            this.parseSceneList(sceneList);

            // json
            String filename = surl + "-" + currentPage + ".json";
            filename = "./web/" + filename;
            File targetFile = new File(filename);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            PageUtil.exportFile(targetFile, AppUtil.jsonFormatter(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * parse genre list
     */
    private void parseSceneList(JSONArray sceneList){
        for(int i=0; i<sceneList.size(); i++){
            JSONObject sceneObj = sceneList.getJSONObject(i);
            String sid = sceneObj.getString("sid");
            String surl = sceneObj.getString("surl");
            String sname = sceneObj.getString("sname");
            String sceneLayer = sceneObj.getString("genre_layer");

            // url
            String tmpUrl = this.generateUrl(surl, 1);
            String uniqueKey = surl + "-" + 1;

            addWaitList(tmpUrl, uniqueKey);

        }

    }


}
