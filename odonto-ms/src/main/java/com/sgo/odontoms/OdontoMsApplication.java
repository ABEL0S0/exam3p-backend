package com.sgo.odontoms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OdontoMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OdontoMsApplication.class, args);
    }

}
