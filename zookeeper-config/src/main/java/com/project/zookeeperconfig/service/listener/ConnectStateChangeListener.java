package com.project.zookeeperconfig.service.listener;


import java.util.List;

import com.project.zookeeperconfig.model.ClusterInfo;
import com.project.zookeeperconfig.model.User;
import com.project.zookeeperconfig.service.ZookeeperService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkStateListener;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.springframework.web.client.RestTemplate;

import static com.project.zookeeperconfig.util.ZookeeperProperties.getHostPostOfServer;
import static com.project.zookeeperconfig.util.ZookeeperProperties.isEmpty;

@Slf4j
@Setter
public class ConnectStateChangeListener implements IZkStateListener {

    private ZookeeperService zookeeperService;
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void handleStateChanged(KeeperState state) throws Exception {
        log.info(state.name()); // 1. disconnected, 2. expired, 3. SyncConnected
    }

    // Init connection session
    @Override
    public void handleNewSession() throws Exception {
        log.info("connected to zookeeper");

        // sync data from master
        syncDataFromMaster();

        // add new znode to /live_nodes to make it live
        zookeeperService.addToLiveNodes(getHostPostOfServer(), "cluster node");
        ClusterInfo.getClusterInfo().getLiveNodes().clear();
        ClusterInfo.getClusterInfo().getLiveNodes().addAll(zookeeperService.getLiveNodes());

        // re try creating znode under /election
        // this is needed, if there is only one server in cluster
        String leaderElectionAlgo = System.getProperty("leader.algo");
        if (isEmpty(leaderElectionAlgo) || "2".equals(leaderElectionAlgo)) {
            zookeeperService.createNodeInElectionZnode(getHostPostOfServer());
            ClusterInfo.getClusterInfo().setMaster(zookeeperService.getLeaderNodeData2());
        } else {
            if (!zookeeperService.masterExists()) {
                zookeeperService.electForMaster();
            } else {
                ClusterInfo.getClusterInfo().setMaster(zookeeperService.getLeaderNodeData());
            }
        }
    }

    @Override
    public void handleSessionEstablishmentError(Throwable error) throws Exception {
        log.info("could not establish session");
    }

    // Sync data from master
    private List<User> syncDataFromMaster() {

//        if (getHostPostOfServer().equals(ClusterInfo.getClusterInfo().getMaster())) {
//            return;
//        }
        String requestUrl;
        requestUrl = "http://".concat(ClusterInfo.getClusterInfo().getMaster().concat("/users"));
        List<User> users = restTemplate.getForObject(requestUrl, List.class);
        return users;

    }
}