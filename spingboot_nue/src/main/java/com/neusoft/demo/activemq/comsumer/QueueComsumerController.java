package com.neusoft.demo.activemq.comsumer;

import com.neusoft.demo.activemq.dao.ActiveMQDao;
import com.neusoft.demo.goodsSort.entity.GoodsSort;
import com.neusoft.demo.util.StringUtil;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QueueComsumerController {

    @Resource
    private ActiveMQDao activeMQDao;

    //queue模式的消费者
    @JmsListener(destination="${spring.jms.queue-name}", containerFactory="queueListener")
    public void readActiveQueue(String goodsSortJson) {
        String code = StringUtil.getCommonCode(2);
        activeMQDao.addActiveMQ(code,goodsSortJson);
        System.out.println("[queue]客户接受到：" + goodsSortJson);
    }
}
