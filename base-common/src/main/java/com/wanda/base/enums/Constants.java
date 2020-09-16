/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.base.enums;

/**
 * FileName：Constants.java
 * Description：Java实践练习
 * History： 初始提交
 * 2020.08.17 12:37          dwan               HTTP 状态码
 */
public enum Constants {
    /**
     * 请求成功状态码
     */
    HTTP_RES_CODE_200(200, "响应请求成功code"),

    /**
     * 服务器错误状态码
     */
    HTTP_RES_CODE_500(500, "系统错误");

    private String desc;
    private Integer code;

    Constants(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }


    public Integer getCode() {
        return code;
    }
}
