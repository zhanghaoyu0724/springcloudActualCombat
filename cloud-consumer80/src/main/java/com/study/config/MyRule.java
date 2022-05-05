package com.study.config;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyRule  extends AbstractLoadBalancerRule {
    private Random random = new Random();
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {

        try {
            boolean grayInvocation = false;
            String grayTag = RibbonRequestContextHolder.getCurrentContext().get("Gray");
            if(!StringUtils.isEmpty(grayTag) && grayTag.equals("true")) {
                grayInvocation = true;
            }

            List<Server> serverList = this.getLoadBalancer().getAllServers();
            List<Server> grayServerList = new ArrayList<>();
            List<Server> normalServerList = new ArrayList<>();
            for(Server server : serverList) {
                NacosServer nacosServer = (NacosServer) server;
                if(nacosServer.getMetadata().containsKey("gray")
                        && nacosServer.getMetadata().get("gray").equals("true")) {
                    grayServerList.add(server);
                } else {
                    normalServerList.add(server);
                }
            }

            if(grayInvocation) {
                return grayServerList.get(random.nextInt(grayServerList.size()));
            } else {
                return normalServerList.get(random.nextInt(normalServerList.size()));
            }
        } finally {
            RibbonRequestContextHolder.clearContext();
        }
    }
}
