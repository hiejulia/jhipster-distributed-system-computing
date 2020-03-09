package com.project.zookeeperconfig.service.zookeeperwatchers;


import java.util.List;

import com.project.zookeeperconfig.model.ClusterInfo;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;


@Slf4j
public class AllNodesChangeListener implements IZkChildListener {

    /**
     * - This method will be invoked for any change in /all_nodes children
     * - During registering this
     * listener make sure you register with path /all_nodes
     * - after receiving notification it will update the local clusterInfo object
     *
     * @param parentPath this will be passed as /all_nodes
     * @param currentChildren current list of children, children's string value is znode name which is
     *     set as server hostname
     */
    @Override
    public void handleChildChange(String parentPath, List<String> currentChildren) {
        log.info("current all node size: {}", currentChildren.size());
        ClusterInfo.getClusterInfo().getAllNodes().clear();
        ClusterInfo.getClusterInfo().getAllNodes().addAll(currentChildren);
    }
}