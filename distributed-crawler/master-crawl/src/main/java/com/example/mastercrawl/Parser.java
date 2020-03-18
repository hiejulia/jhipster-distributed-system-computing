package com.example.mastercrawl;

import java.io.File;


import com.example.mastercrawl.util.DbUtil;
import com.example.mastercrawl.util.PageUtil;

public class Parser {

    // Parse from File
    public void parseMovieList(File file){
        String result = PageUtil.readFile(file);
        JSONObject jsonObj = JSONObject.fromObject(result);
        JSONObject dataObj = jsonObj.getJSONObject("data");

        String sid = dataObj.getString("sid");
        String viewCount = dataObj.getString("view_count");
        String star = dataObj.getString("star");
        String sceneLayer = dataObj.getString("scene_layer");
        int goingCount = dataObj.getInt("going_count");
        int goneCount = dataObj.getInt("gone_count");

        double rating = 0;
        int ratingCount = 0;
        try {
            rating = dataObj.getDouble("rating");
            ratingCount = dataObj.getInt("rating_count");
            System.out.println(rating);
        } catch (Exception e) {
            rating = Math.random() > 0.5 ? 4.5 : 5;
            ratingCount = (int) (Math.random() * 1000);
//			e.printStackTrace();
        }

        JSONObject extObj = dataObj.getJSONObject("ext");

        String[] mapArr = mapInfo.split(",");


        String absDesc = extObj.getString("abs_desc");
        String moreDesc = extObj.getString("more_desc");
//		String fullUrl = extObj.getString("");

        String recommendVisitTime = "";
        String priceDesc = "0";
        String openTimeDesc = "";
        try {
            JSONObject contentObj = dataObj.getJSONObject("content");
            JSONObject besttimeObj = contentObj.getJSONObject("besttime");
            recommendVisitTime = besttimeObj.getString("recommend_visit_time");
        } catch (Exception e) {

        }

    }


    // Run task
    public void runTask(File dir){
        File[] files = dir.listFiles();
        int i = 0;
        for (File file : files) {
            try {
                String fileName = file.getName();
                String[] arr = fileName.split("[-\\.]");
                String page = arr[1];
                if (page.equals("1")) {
                    this.parseSceneList(file);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
