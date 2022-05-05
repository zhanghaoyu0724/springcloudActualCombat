package com.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class GrayInterceptor implements ClientHttpRequestInterceptor {


    private TrafficRule rule;

    public GrayInterceptor(TrafficRule rule){
        this.rule=rule;
    }

    //静态绑定
   /* @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        if (httpRequest.getHeaders().containsKey("Gray")) {
            String value=httpRequest.getHeaders().getFirst("Gray");
            if(value.equals("true")){
                RibbonRequestContextHolder.getCurrentContext().put("Gray",Boolean.TRUE.toString());
            }
        }
        return clientHttpRequestExecution.execute(httpRequest,bytes);
    }*/

//    动态绑定


    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        if (rule.getType().equalsIgnoreCase("header")){
            if (httpRequest.getHeaders().containsKey(rule.getName())) {
                String value = httpRequest.getHeaders().get(rule.getName()).iterator().next();
                if (value.equalsIgnoreCase(rule.getValue())){
                    RibbonRequestContextHolder.getCurrentContext().put("Gray",Boolean.TRUE.toString());
                }
            }
        }else if (rule.getType().equalsIgnoreCase("param")){
            String query = httpRequest.getURI().getQuery();
            String[] splitkv = query.split("&");
            for (String queryItem : splitkv) {
                String[] splitInfo = queryItem.split("=");
                if (splitInfo[0].equalsIgnoreCase(rule.getName())&& splitInfo[1].equals(rule.getValue())){
                    RibbonRequestContextHolder.getCurrentContext().put("Gray",Boolean.TRUE.toString());

                }

            }
        }
        return clientHttpRequestExecution.execute(httpRequest,bytes);
    }
}
