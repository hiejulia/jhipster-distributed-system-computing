package com.project.zookeeperconfig.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ClusterInfo {

    private static ClusterInfo clusterInfo = new ClusterInfo();

    public static ClusterInfo getClusterInfo() {
        return clusterInfo;
    }

    private List<String> liveNodes = new ArrayList<>();

    private List<String> allNodes = new ArrayList<>();

    private String master;
}