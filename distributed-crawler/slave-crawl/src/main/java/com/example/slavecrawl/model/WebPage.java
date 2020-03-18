package com.example.slavecrawl.model;

import lombok.Data;

import java.net.URL;


@Data
public class WebPage {

    private String pageContent = null;
    private URL url = null;
    private int layer = 0;

}
