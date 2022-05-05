package com.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.study")
public class CloudProviderMain1010 {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderMain1010.class,args);
    }
}
