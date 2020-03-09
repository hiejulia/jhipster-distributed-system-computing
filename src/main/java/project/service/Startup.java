package project.service;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import project.service.cache.CacheLoader;

@Component
public class Startup {

    @Autowired
    CacheLoader cacheLoader;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        cacheLoader.initializeCache();
    }

}
