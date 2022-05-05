package com.study.controller;

import com.study.config.TrafficRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TrafficRule rule;

    @GetMapping("/echo")
    public String echo(HttpServletRequest request){
        HttpHeaders headers = new HttpHeaders();
        if (!StringUtils.isEmpty(request.getHeader("Gray"))){
            headers.add("Gray", request.getHeader("Gray"));
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return  restTemplate.exchange("http://cloud-provider/echo", HttpMethod.GET,entity,String.class).getBody();
    }

    @GetMapping("/info")
    public String info(){
        return rule.toString();
    }
}
