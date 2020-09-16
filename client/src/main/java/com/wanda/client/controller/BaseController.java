/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 * <p>
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.client.controller;

import com.wanda.base.model.RespBean;
import com.wanda.client.util.RequestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName：BaseController.java
 * Description：Java实践练习
 * History： 初始提交
 * 2020.08.17 12:37          dwan               BaseController
 */
@RequestMapping("client")
@RestController
public class BaseController {
    @Resource(name = "rest")
    private RestTemplate restTemplate;
    @Resource
    private RequestUtil requestUtil;
    @Value("${registry-centre.url}")
    private String registryCentre;

    @GetMapping("{count}")
    public RespBean helloWord(@PathVariable(name = "count") Integer count) {
        Map<String,Object> map = new HashMap<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            String path = "/port";
            Map<String, Object> data;
            RespBean respBean = requestUtil.get(path, "server");
            data = respBean.getData();
            map.put(String.valueOf(i),data);
        }
        long endTime = System.currentTimeMillis();
        return RespBean.ok("用时："+(endTime-startTime)+"毫秒",map);
    }

}
