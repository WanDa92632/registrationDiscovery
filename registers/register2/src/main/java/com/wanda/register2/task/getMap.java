package com.wanda.register2.task;

import com.wanda.base.model.RespBean;
import com.wanda.register2.model.MySystemInfo;
import org.assertj.core.util.Maps;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 知非
 * @date 2020/8/18 11:33
 */
@Component
public class getMap {

    @Resource(name = "info")
    private MySystemInfo mySystemInfo;
    @Resource(name = "rest")
    private RestTemplate restTemplate;
    @Value("${register}")
    private String register;
    /**
     * 定时交换缓存区的数据
     */
    @Scheduled(fixedDelay = 1000 * 6)
    public void get() {
        System.out.println("------------------- 同步节点信息 START -------------------");
        System.out.println("同步前："+mySystemInfo.getServerMap().toString());

        RespBean respBean = restTemplate.getForObject(register+"list", RespBean.class);
        HashMap<String,List<String>> map;
        map = (HashMap<String, List<String>>) respBean.getData().get("serverMap");
        mySystemInfo.setServerMap(map);

        System.out.println("同步后："+mySystemInfo.getServerMap().toString());
        System.out.println("------------------- 同步节点信息 END -------------------");
    }
}
