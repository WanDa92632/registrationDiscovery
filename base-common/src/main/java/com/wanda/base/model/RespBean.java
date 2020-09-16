/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.base.model;

import com.wanda.base.enums.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * FileName：RespBean.java
 * Description：Java实践练习
 * History： 初始提交
 * 2020.08.17 12:37          dwan               数据传输封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {
    /**
     * 响应码
     */
    private Integer status;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应数据
     */
    private Map<String, Object> data;

    /**
     * 空构造函数
     *
     * @return
     */
    public static RespBean build() {
        return new RespBean();
    }

    /**
     * 请求成功
     *
     * @param msg
     * @return
     */
    public static RespBean ok(String msg) {
        return new RespBean(Constants.HTTP_RES_CODE_200.getCode(), msg, null);
    }

    /**
     * 请求成功
     *
     * @param data
     * @return
     */
    public static RespBean ok(Map<String, Object> data) {
        return new RespBean(Constants.HTTP_RES_CODE_200.getCode(), "success", data);
    }

    /**
     * 请求成功
     *
     * @param msg
     * @param data
     * @return
     */
    public static RespBean ok(String msg, Map<String, Object> data) {
        return new RespBean(200, msg, data);
    }

    /**
     * 请求失败
     *
     * @param msg
     * @return
     */
    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    /**
     * 请求失败
     * 
     * @param msg
     * @param data
     * @return
     */
    public static RespBean error(String msg, Map<String, Object> data) {
        return new RespBean(500, msg, data);
    }

}
