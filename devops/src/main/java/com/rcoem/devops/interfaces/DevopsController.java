package com.rcoem.devops.interfaces;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/devops")
public class DevopsController {
    @Value("${source.path}")
    private String path;

    @GetMapping("/health")
    public ResponseEntity<String> getHealth(){
        return ResponseEntity.ok("Online");
    }

    @GetMapping("/env-path")
    public String getPath(){
        return path;
    }

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Value("environment")
    private String env;

    @GetMapping("/invoke")
    public ResponseEntity<String> invokeBasedOnEnv() {

        if ("prod".equalsIgnoreCase(activeProfile)) {
            return ResponseEntity.ok("Invoke in prod");
        } else {
            int randomValue = new Random().nextInt(1000);
            return ResponseEntity.ok("Random Value: " + randomValue);
        }
    }
}
