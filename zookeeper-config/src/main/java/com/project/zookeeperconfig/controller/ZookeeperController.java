package com.project.zookeeperconfig.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.project.zookeeperconfig.model.ClusterInfo;
import com.project.zookeeperconfig.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.project.zookeeperconfig.util.ZookeeperProperties.getHostPostOfServer;
import static com.project.zookeeperconfig.util.ZookeeperProperties.isEmpty;

@RestController
public class ZookeeperController {

    private RestTemplate restTemplate = new RestTemplate();

    @PutMapping("/users/{id}/{name}")
    public ResponseEntity<String> saveUser(
            HttpServletRequest request,
            @PathVariable("id") Integer id,
            @PathVariable("name") String name) {

        String requestFrom = request.getHeader("request_from");
        // Leader
        String leader = ClusterInfo.getClusterInfo().getMaster();
        if (!isEmpty(requestFrom) && requestFrom.equalsIgnoreCase(leader)) {
            User user = new User(id, name);

            return ResponseEntity.ok("SUCCESS");
        }
        // If I am leader I will broadcast data to all live node, else forward request to leader
        if (amILeader()) {
            List<String> liveNodes = ClusterInfo.getClusterInfo().getLiveNodes();

            int successCount = 0;
            for (String node : liveNodes) {

                if (getHostPostOfServer().equals(node)) {

                    successCount++;
                } else {
                    String requestUrl =
                            "http://"
                                    .concat(node)
                                    .concat("users")
                                    .concat("/")
                                    .concat(String.valueOf(id))
                                    .concat("/")
                                    .concat(name);
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("request_from", leader);
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    HttpEntity<String> entity = new HttpEntity<>(headers);
                    restTemplate.exchange(requestUrl, HttpMethod.PUT, entity, String.class).getBody();
                    successCount++;
                }
            }

            return ResponseEntity.ok()
                    .body("Successfully update ".concat(String.valueOf(successCount)).concat(" nodes"));
        } else {
            String requestUrl =
                    "http://"
                            .concat(leader)
                            .concat("users")
                            .concat("/")
                            .concat(String.valueOf(id))
                            .concat("/")
                            .concat(name);
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            return restTemplate.exchange(requestUrl, HttpMethod.PUT, entity, String.class);
        }
    }

    /**
     * Check if the current cluster is Leader node
     * @return
     */
    private boolean amILeader() {
        String leader = ClusterInfo.getClusterInfo().getMaster();
        return getHostPostOfServer().equals(leader);
    }


    /**
     * Get cluster info
     * @return
     */
    @GetMapping("/clusterInfo")
    public ResponseEntity<ClusterInfo> getClusterinfo() {
        return ResponseEntity.ok(ClusterInfo.getClusterInfo());
    }
}