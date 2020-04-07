package com.xzsd.pc.redis.controller;/*
package com.neusoft.demo.redis.controller;

import com.neusoft.demo.redis.service.RedisService;
import com.neusoft.demo.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisService redisService;

    private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

    @PostMapping("/redisSet")
    public AppResponse redisSet(String key,String value,int time){
        try{

            return redisService.redisSet(key, value, time);
        }catch (Exception e){
//            logger.error("新增商品异常",e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
*/