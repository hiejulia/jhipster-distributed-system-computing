package com.example.mastercrawl.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/scrap")
public class IndexController {

    @RequestMapping(value = "/info/{cname}")
    public String crawler(@PathVariable String cname) {
        CrawlerModel model = CrawlerCache.getCrawlerModel(cname);
        if (model == null) {
            return "not find " + cname;
        }
        return model.queueInfo();
    }


    @RequestMapping(value = "send_req")
    public String sendRequest(Request request){
        CrawlerCache.consumeRequest(request);
        return "consume suc";
    }


}
