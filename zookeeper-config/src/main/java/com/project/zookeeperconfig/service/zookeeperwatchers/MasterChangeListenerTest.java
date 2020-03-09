package com.project.zookeeperconfig.service.zookeeperwatchers;



import java.util.Collections;
import java.util.List;

import com.project.zookeeperconfig.model.ClusterInfo;
import com.project.zookeeperconfig.service.ZookeeperService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.springframework.beans.factory.annotation.Autowired;

import static com.project.zookeeperconfig.util.ZookeeperProperties.ELECTION_NODE_2;

/** @author "Bikas Katwal" 27/03/19 */
@Slf4j
@Setter
public class MasterChangeListenerTest implements IZkChildListener {

    @Autowired
    private ZookeeperService zookeeperService;

    /**
     * listens for deletion of sequential znode under /election znode and updates the
     * clusterinfo
     *
     * @param parentPath
     * @param currentChildren
     */
    @Override
    public void handleChildChange(String parentPath, List<String> currentChildren) {
        if (currentChildren.isEmpty()) {
            throw new RuntimeException("No node exists to select master!!");
        } else {
            //get least sequenced znode
            Collections.sort(currentChildren);
            String masterZNode = currentChildren.get(0);

            // once znode is fetched, fetch the znode data to get the hostname of new leader
            String masterNode = zookeeperService.getZNodeData(ELECTION_NODE_2.concat("/").concat(masterZNode));
            log.info("new master is: {}", masterNode);

            //update the cluster info with new leader
            ClusterInfo.getClusterInfo().setMaster(masterNode);
        }
    }
}