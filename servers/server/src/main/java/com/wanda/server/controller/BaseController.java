/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.server.controller;


import com.wanda.base.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 知非
 * @date 2020/8/17 12:16
 */
@RequestMapping
@RestController
public class BaseController {

    @Resource(name = "rest")
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String port;

    /**
     * 心跳检测接口
     *
     * @return
     */
    @GetMapping("hello")
    public boolean res(){
        return true;
    }

    @GetMapping("/port")
    public RespBean test(){
        Map<String,Object> data = new HashMap<>();
        data.put("port",port);
        return RespBean.ok(data);
    }
}
