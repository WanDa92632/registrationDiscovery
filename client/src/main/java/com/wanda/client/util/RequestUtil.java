/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.client.util;

import com.wanda.base.model.Config;
import com.wanda.base.model.RespBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 知非
 * @date 2020/8/17 14:33
 */
@Component
public class RequestUtil {

    /**
     * 注册中心
     */
    @Value("${registry-centre.url}")
    private String registryCentreUrl;

    @Resource(name = "rest")
    private RestTemplate restTemplate;

    /**
     * get 请求
     *
     * @param path
     * @param server
     * @return
     */
    public RespBean get(String path, String server) {
        String address = getServerUrl(server);
        // 真实地址
        String url = address + path;
        System.out.println("get 请求：" + url);
        // 请求数据
        RespBean respBean = null;
        try {
            respBean = restTemplate.getForObject(url, RespBean.class);
        } catch (Exception e) {
            nodeLose(server, address);
        }
        return respBean;
    }

    /**
     * post 请求
     *
     * @param path   路径
     * @param server 服务名
     * @return
     */
    public RespBean post(String path, Object object, String server) {
        String address = getServerUrl(server);
        // 真实地址
        String url = address + path;
        // 输出真实请求地址
        System.out.println("post 请求：" + url);
        // 请求数据
        RespBean respBean = restTemplate.postForObject(url, object, RespBean.class);
        if (respBean == null) {
            // 请求错误
            nodeLose(server, address);
        }
        return respBean;
    }

    /**
     * put 请求
     *
     * @param path
     * @param server
     * @return
     */
    public void put(String path, Object object, String server) {
        // 真实地址
        String url = getServerUrl(server) + path;
        // 请求数据
        restTemplate.put(url, RespBean.class);
    }


    /**
     * 向注册中心获取服务地址
     *
     * @return
     */
    public String getServerUrl(String server) {
        RespBean respBean = restTemplate.getForObject(registryCentreUrl + "/" + server, RespBean.class);
        Map<String, Object> data = respBean.getData();
        String url = (String) data.get("url");
        return url;
    }

    /**
     * 通知服务节点失效
     *
     * @param server  服务名
     * @param address 地址
     */
    public void nodeLose(String server, String address) {
        Config config = new Config();
        config.setServer(server);
        config.setAddress(address);
        System.out.println("节点失效：server：" + server + "address：" + address);
        restTemplate.put(registryCentreUrl, config, RespBean.class);
    }
}
