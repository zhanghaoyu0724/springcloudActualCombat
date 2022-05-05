package com.study.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "traffic.rule")
@Component
@Data
public class TrafficRule {

    private String type;
    private String name;
    private String value;
}
