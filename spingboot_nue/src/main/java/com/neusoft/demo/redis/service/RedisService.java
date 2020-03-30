package com.neusoft.demo.redis.service;

import com.neusoft.demo.goodsMessage.service.GoodsMessageService;
import com.neusoft.demo.util.AppResponse;
import com.neusoft.demo.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisService {

    @Autowired
    private RedisOperator redisOperator;

    public AppResponse redisSet(String key,String value,int time){
        redisOperator.set(key,value,time);
        return AppResponse.success("操作成功！");
    }

}
