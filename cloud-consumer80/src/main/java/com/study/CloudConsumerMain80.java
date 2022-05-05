package com.study;

import com.study.config.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RibbonClient(value = "cloud-provider",configuration = MyRule.class)
@EnableConfigurationProperties
@ComponentScan("com.study")
public class CloudConsumerMain80 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerMain80.class,args);
    }
}
