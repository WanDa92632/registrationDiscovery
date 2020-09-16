/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.base.model;

import lombok.Data;

/**
 * FileName：Config.java
 * Description：Java实践练习
 * History： 初始提交
 * 2020.08.17 12:37          dwan               服务注册/心跳机制 信息封装
 */
@Data
public class Config {

    private String server;
    private String ip;
    private String port;
    private String address;
}
