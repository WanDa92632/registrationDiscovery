/**
 * -------------------------------------------------------
 * Copyright (c) 2020, 万得信息
 * All rights reserved.
 *
 * 版权声明 本文件所有权归万得信息所有
 * -------------------------------------------------------
 */

package com.wanda.register.task;

import com.wanda.register.model.MySystemInfo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 知非
 * @date 2020/8/17 18:20
 */
@Component
public class CacheTask {

    @Resource(name = "info")
    private MySystemInfo mySystemInfo;


    /**
     * 定时交换缓存区的数据
     */
    @Scheduled(fixedDelay = 1000 * 3)
    public void cacheTask() {
        System.out.println("交换前：" + mySystemInfo.getServerMap().toString());
        Map<String, List<String>> map = mySystemInfo.getServerMap();
        mySystemInfo.setServerMap(mySystemInfo.getServerMapCache());
        map.clear();
        mySystemInfo.setServerMapCache(map);
        System.out.println("交换后：" + mySystemInfo.getServerMap().toString());

    }
}
