package com.rs.app.controller;

import com.rs.app.model.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    private static final String SERVICE_ID = "ribbon-as-loadbalencer-service";
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/serverInfo")
    public ServerInfo getServerInfo() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(SERVICE_ID);
        return new ServerInfo(serviceInstance.getHost(), serviceInstance.getPort());
    }

}
