package com.project.zookeeperconfig.service.zookeeperwatchers;


import java.util.List;

import com.project.zookeeperconfig.model.ClusterInfo;
import com.project.zookeeperconfig.service.ZookeeperService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.springframework.beans.factory.annotation.Autowired;


@Setter
@Slf4j
public class MasterNodeChangeListener implements IZkChildListener {

    @Autowired
    private ZookeeperService zookeeperService;

    /**
     * listens for creation/deletion of znode "master" under /election znode and updates the
     * clusterinfo
     *
     * @param parentPath
     * @param currentChildren
     */
    @Override
    public void handleChildChange(String parentPath, List<String> currentChildren) {
        if (currentChildren.isEmpty()) {
            log.info("master deleted, recreating master!");
            ClusterInfo.getClusterInfo().setMaster(null);
            try {

                zookeeperService.electForMaster();
            } catch (ZkNodeExistsException e) {
                log.info("master already created");
            }
        } else {
            String leaderNode = zookeeperService.getLeaderNodeData();
            log.info("updating new master: {}", leaderNode);
            ClusterInfo.getClusterInfo().setMaster(leaderNode);
        }
    }
}
