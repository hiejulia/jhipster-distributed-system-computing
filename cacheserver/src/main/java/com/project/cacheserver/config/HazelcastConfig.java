package com.project.cacheserver.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config hazelCastConfig() {
        return new Config()
            .setInstanceName(Constants.INSTANCE_NAME)
            .addMapConfig(
                new MapConfig()
                    .setName(Constants.MAP)
                    .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                    .setEvictionPolicy(EvictionPolicy.LRU));
    }
}
