package com.example.mastercrawl.repo;

import org.springframework.stereotype.Repository;

@Repository
public interface CrawData extends CrudRepository<Command, Integer> {

//    int nums = count("select count(*) from t_crawled where sid=?", new String[]{"63b3f3f9afe9f8c75f3ca2f360e726d"});


}
