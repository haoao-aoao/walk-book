package com.neusoft.demo.activemq.dao;

public interface ActiveMQDao {
    /**
     * 将数据添加到数据库
     * @param code
     * @param Json
     * @return
     */
    int addActiveMQ(String code,String Json);
}
