package study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.study")

public class CloudProviderMain2020 {
    public static void main(String[] args) {
        SpringApplication.run(CloudProviderMain2020.class,args);
    }
}
