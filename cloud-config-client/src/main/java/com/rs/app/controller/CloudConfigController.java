package com.rs.app.controller;

import com.rs.app.model.Config;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * By default, the configuration values are read on the client’s startup, and not again.
 * You can force a bean to refresh its configuration - to pull
 * updated values from the Config Server - by annotating the CloudConfigController
 * with the Spring Cloud Config @RefreshScope and then by triggering a refresh event.
 *
 *  Note: If you enable @RefreshScope it just populates the new configuration values on existing object,
 *  it will not create another object and populate.
 *
 * curl -X POST http://localhost:4015/actuator/refresh -I
 * http://localhost:4015/actuator/env
 */
@RestController
@RequestMapping(CloudConfigController.RESOURCE_URI)
@RefreshScope
public class CloudConfigController {
    protected static final String RESOURCE_URI = "/cloud-config";
    @Value("${author: No author}")
    private String author;
    @Value("${message: No message}")
    private String message;
    @Autowired
    private Config config;

    public CloudConfigController() {
        System.out.println("CloudConfigController: 0-param constr");
    }

    @GetMapping("/getConfigInfo")
    public String getAuthorAndMessage() {
        return String.format("Author: %s - Message: %s", author, message);
    }

    @GetMapping("/getConfig")
    public Config getConfig() {
        System.out.println("--- config");
        System.out.println(config);
        System.out.println("Config HashCode? " + this.config.hashCode());
        System.out.println("Config -> message -> HashCode? " + this.config.getMessage().hashCode());
        Config config = new Config();
        BeanUtils.copyProperties(this.config, config);
        return config;
    }

}
