package com.okdeer.mall.mq;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
/**
 * activeMQ加载配置 直接获取JmsTemplate对象
 * Copyright:   Copyright 2007 - 2016 MPR Tech. Co. Ltd. All Rights Reserved.
 * Date:        2016年9月14日 下午2:42:40
 * Author:      sunsz
 * Version:     1.0.0.0
 * Description: Initialize
 */
public class JMSConfigLoader {

    private static final JMSConfigLoader instance = new JMSConfigLoader();

    private ActiveMQConnectionFactory activeMQConnectionFactory;
    private ActiveMQQueue activeMQQueue;
    private SingleConnectionFactory singleConnectionFactory;
    private JmsTemplate jmsTemplate;
    /**
     * 初始化成员变量
     */
    protected JMSConfigLoader() {
        this.activeMQConnectionFactory = new ActiveMQConnectionFactory();
        this.activeMQQueue = new ActiveMQQueue();
        this.jmsTemplate = new JmsTemplate();
    }

    public static JMSConfigLoader getInstance() {
        return instance;
    }
    /**
     * 创建JmsTemplate
     * @return
     */
    public JmsTemplate load() {
        this.singleConnectionFactory = new SingleConnectionFactory(this.activeMQConnectionFactory);
        this.jmsTemplate.setConnectionFactory(this.singleConnectionFactory);
        this.jmsTemplate.setDefaultDestination(this.activeMQQueue);
        return this.jmsTemplate;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

}

-------------------------------------------------------------------------------------------------------------------

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.mpr.mprsp.cms.event.Event;
import com.mpr.mprsp.cms.event.EventBus;
import com.mpr.mprsp.cms.event.EventBusException;
import com.mpr.mprsp.cms.event.EventSubscriber;
import com.mpr.mprsp.cms.event.EventTopic;
/**
 * EventBus是一款针对Android优化的发布/订阅事件总线。主要功能是替代Intent,Handler,
 * BroadCast在Fragment，Activity，Service，
 * 线程之间传递消息.优点是开销小，代码更优雅。以及将发送者和接收者解耦。
 * 
 * activeMQ加载配置java 获取jms并发送消息
 * Copyright:   Copyright 2007 - 2016 MPR Tech. Co. Ltd. All Rights Reserved.
 * Date:        2016年9月14日 下午2:47:20
 * Author:      sunsz
 * Version:     1.0.0.0
 * Description: Initialize
 */
public class SpringJmsEventBus implements EventBus {

    private JmsTemplate jmsTemplate = JMSConfigLoader.getInstance().load();
    /**
     * 发布jms异步消息到EventBus
     */
    @Override
    public void publish(Event event) throws EventBusException {
        this.jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello queue world");
            }
        });

    }

    @Override
    public UUID subscribe(EventTopic topic, EventSubscriber subscriber)
            throws EventBusException {
        return null;
    }

    @Override
    public void unsubscribe(UUID subscriberId, EventSubscriber subscriber)
            throws EventBusException {
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}

