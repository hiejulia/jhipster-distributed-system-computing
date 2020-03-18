package com.example.slavecrawl.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import com.example.slavecrawl.model.WebPage;

import org.springframework.stereotype.Service;


public class MovieCrawler {

    public MovieCrawler(){

    }

    /**
     * URL
     */
    public String generateUrl(String sid, double mapX, double mapY){
        double r = 2000;
        double left = mapX - r;
        double right = mapX + r;
        double top = mapY - r;
        double bottom = mapY + r;
        String url = "http://google.com/ajax/movies/searcharound?sid=" +
            sid + "&wd=%E9%85%92%E5%BA%97&is_detail=0&nb_x=" +
            mapX + "&nb_y=" + mapY + "&r=" + r + "&b=(" + left + "," + top + ";" + right + "," + bottom + ")";
        return url;
    }

    @Override
    public void loadWaitList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("./data/list-movies.txt")));
            String buff = null;
            while((buff = reader.readLine()) != null){
                String[] arr = buff.split("\\s");
                String sid = arr[0];
                double mapX = Double.parseDouble(arr[1]);
                double mapY = Double.parseDouble(arr[2]);
                String url = this.generateUrl(sid, mapX, mapY);
                String uniqueKey = sid + "-" + mapX + "-" + mapY;
                // add unvisited url
                this.addUnVisitPath(uniqueKey);
                // add wait lsit
                this.addWaitList(url, uniqueKey);
                System.out.println(url);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MAIN FUNCTION FOR EXTRACT
    @Override
    public void exactor(WebPage webPage) {
        HashMap<String, String> params = UrlParmUtil.parseUrl(webPage.getUrl().toString());
        String sid = params.get("sid");
        double mapX = Double.parseDouble(params.get("nb_x"));
        double mapY = Double.parseDouble(params.get("nb_y"));
        String uniqueKey = sid + "-" + mapX + "-" + mapY;
        // Add visited url
        this.visitUrl(uniqueKey);

        // json
        String filename = "./web-movies/" + sid + ".txt";
        File targetFile = new File(filename);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        // export file
        PageUtil.exportFile(targetFile, webPage.getPageContent());
    }

    // initSeeds()
    // start
}
