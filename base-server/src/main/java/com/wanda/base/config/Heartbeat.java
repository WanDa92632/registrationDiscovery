/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.base.config;

import com.wanda.base.model.Config;
import com.wanda.base.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * FileName：Heartbeat.java
 * Description：Java实践练习
 * History： 初始提交
 * 2020.08.17 12:37          dwan               心跳检测
 */
@Component
public class Heartbeat {

    @Resource(name = "rest")
    private RestTemplate restTemplate;

    /**
     * 服务名
     */
    @Value("${server.server}")
    private String server;
    /**
     * 服务端口
     */
    @Value("${server.port}")
    private String port;
    /**
     * 注册中心
     */
    @Value("${registry-centre.url}")
    private String registryCentreUrl;


    /**
     * 心跳机制
     */
    @Scheduled(fixedRate = 2000)
    public void heartbeatDetection() {
        System.out.println("---------- 心跳检测 START ----------");
        InetAddress address = null;

        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Config config = new Config();
        config.setServer(server);
        config.setIp(address.getHostAddress());
        config.setPort(port);
        restTemplate.postForObject(registryCentreUrl + "/check", config, RespBean.class);

        System.out.println("---------- 心跳检测 END ----------");
    }
}
