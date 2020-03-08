package com.project.cacheserver.service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;
import com.project.cacheserver.config.Constants;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;



@Service
public class UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void addUser(String id, String user) throws Exception {
        IMap<String, String> map = Hazelcast.getHazelcastInstanceByName(Constants.INSTANCE_NAME).getMap(Constants.MAP);
        map.put(id, user);
    }

    public String readUser(String id) {
        IMap<String, String> map = Hazelcast.getHazelcastInstanceByName(Constants.INSTANCE_NAME).getMap(Constants.MAP);
        return map.get(id);
    }

    public boolean removeUser(String id) {
        IMap<String, String> map = Hazelcast.getHazelcastInstanceByName(Constants.INSTANCE_NAME).getMap(Constants.MAP);
        String obj;
        Map<String, String> tmap = new HashMap<>();
        int originalSize = map.size();
        if(map.containsKey(id)) {
            // Found user in cache server
            for(String key : map.keySet()) {
                if (!key.equals(id)) {
                    tmap.put(key, map.get(key));
                }
            }
            map.clear();
            map.putAll(tmap);
        } else {
            // No user found in cache
        }
        if (map.size() < originalSize) {
            return true;
        } else {
            return false;
        }
    }



}
