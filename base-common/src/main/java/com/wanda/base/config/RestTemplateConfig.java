/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.base.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * FileName：RestTemplateConfig.java
 * Description：Java实践练习
 * History： 初始提交
 * 2020.08.17 12:37          dwan               RestTemplate 注册到 Spring 容器
 */
@Configuration
public class RestTemplateConfig {

    @Autowired
    private RestTemplateBuilder builder;

    @Bean("rest")
    public RestTemplate restTemplate() {
        return builder.build();
    }
}
