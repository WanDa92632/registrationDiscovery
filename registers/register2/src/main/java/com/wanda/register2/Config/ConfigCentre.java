/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */
package com.wanda.register2.Config;

import com.wanda.base.model.Config;
import com.wanda.base.model.RespBean;
import com.wanda.register2.model.MySystemInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 知非
 * @date 2020/8/17 10:35
 */
@RequestMapping("server")
@RestController
public class ConfigCentre {


    @Resource(name = "info")
    private MySystemInfo mySystemInfo;

    /**
     * 服务注册
     *
     * @param config
     * @return
     */
    @PostMapping()
    public RespBean serviceRegistry(@RequestBody Config config) {
        HashMap<String, Object> data = new HashMap<>();
        mySystemInfo.addServer(config);
        data.put("success", true);
        return RespBean.ok(data);
    }

    /**
     * 服务发现
     *
     * @param server
     * @return
     */
    @GetMapping("{server}")
    public RespBean getServer(@PathVariable(value = "server") String server) {
        Map<String, Object> data = new HashMap<>();
        String ip = mySystemInfo.getServer(server);
        data.put("url", ip);
        System.out.println("返回 ip：" + ip);
        return RespBean.ok(data);
    }

    /**
     * 服务列表
     *
     * @return
     */
    @GetMapping("list")
    public RespBean list() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("serverMap",mySystemInfo.getServerMap());
        return RespBean.ok(map);
    }


    /**
     * 心跳检测接口
     *
     * @param config
     * @return
     */
    @PostMapping("/check")
    public RespBean check(@RequestBody Config config) {
        HashMap<String, Object> data = new HashMap<>();
        mySystemInfo.addServer(config);
        data.put("success", true);
        return RespBean.ok(data);
    }

    @PutMapping("")
    public void nodeLose(@RequestBody Config config) {
        mySystemInfo.deleteNode(config.getServer(), config.getAddress());
    }


}
