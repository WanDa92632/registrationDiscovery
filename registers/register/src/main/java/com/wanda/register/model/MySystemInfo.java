/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.register.model;

import com.wanda.base.model.Config;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 知非
 * @date 2020/8/17 12:39
 */
@Data
@Configuration
public class MySystemInfo {

    private volatile static MySystemInfo mySystemInfo;

    private AtomicInteger index = new AtomicInteger(0);
    /**
     * 服务名：IP 地址列表
     */
    private volatile Map<String, List<String>> serverMap;

    /**
     * 服务名：IP 地址列表 缓存
     */
    private volatile Map<String, List<String>> serverMapCache;


    /**
     * 服务注册
     *
     * @param config
     */
    public void addServer(Config config) {

        // 写入
        setServer(serverMap, config);
        // 写入缓存
        setServer(serverMapCache, config);


    }

    public void setServer(Map<String, List<String>> map, Config config) {
        // 服务 url
        String url = "http://" + config.getIp() + ":" + config.getPort();
        List<String> servers;
        // 服务是否已注册
        if (map.containsKey(config.getServer())) {
            // 服务已存在
            servers = map.get(config.getServer());
        } else {
            // 服务第一次注册
            servers = new ArrayList<>();
            map.put(config.getServer(), servers);
        }
        // 添加到列表中
        if (!servers.contains(url)) {
            servers.add(url);
        }
    }

    /**
     * 获取服务地址
     *
     * @param server
     * @return
     */
    public String getServer(String server) {
        String url = null;
        //
        if (this.serverMap.containsKey(server)) {
            // 服务已存在
            // TODO 负载均衡
            List<String> servers = serverMap.get(server);

            if (index.get() > servers.size()) {
                index = new AtomicInteger(0);
            }
            int i = index.getAndIncrement();
            // 轮询
            url = servers.get(i >= servers.size() ? 0 : i);
            if (url == null) {
                url = getServer(server);
            }

        }
        return url;
    }

    @Bean(name = "info")
    public MySystemInfo mySystemInfo() {
        MySystemInfo mySystemInfo = new MySystemInfo();

        mySystemInfo.setServerMap(new ConcurrentHashMap<>(16));
        mySystemInfo.setServerMapCache(new ConcurrentHashMap<>(16));

        return mySystemInfo;
    }

    public void deleteNode(String server, String address) {
        System.out.println("节点失效：" + address);
        System.out.println("去除失效节点前：" + getServerMap().toString());

        List<String> list = getServerMap().get(server);
        int index = list.indexOf(address);
        if (index >= 0) {
            list.remove(index);
        }

        list = getServerMapCache().get(server);
        index = list.indexOf(address);
        if (index >= 0) {
            list.remove(index);
        }

        System.out.println("去除失效节点后：" + getServerMap().toString());
    }
}
