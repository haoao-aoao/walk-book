package com.xzsd.pc.redis.service;

import com.xzsd.pc.util.AppResponse;
import com.xzsd.pc.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RedisService {

    @Autowired
    private RedisOperator redisOperator;

    public AppResponse redisSet(String key, String value, int time){
        redisOperator.set(key,value,time);
        return AppResponse.success("操作成功！");
    }

}
