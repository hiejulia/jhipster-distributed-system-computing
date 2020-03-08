package com.project.cacheserver.service;


import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.IMap;
import com.project.cacheserver.config.Constants;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CacheInitService {

    public boolean initializeCache(String allRecords) {
        IMap<String, String> map = Hazelcast.getHazelcastInstanceByName(Constants.INSTANCE_NAME).getMap(Constants.MAP);

        JSONObject jsonObject = new JSONObject(allRecords);
        for (String key : jsonObject.keySet()) {
            map.put(key, jsonObject.getString(key));
        }
        return true;
    }

    public void clearCache() {
        IMap<String, String> map = Hazelcast.getHazelcastInstanceByName(Constants.INSTANCE_NAME).getMap(Constants.MAP);
        map.clear();
    }
}
