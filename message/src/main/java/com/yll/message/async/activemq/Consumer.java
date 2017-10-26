package com.yll.message.async.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * @author：linlin.yang
 * @date：2017/10/26 16:38
 */
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private static volatile boolean handleFlag = true;

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD,"tcp://localhost:61616");

        //获取连接
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
        } catch (JMSException e) {
            logger.error("获取连接异常", e);
        }

        //创建会话
        Session session = null;
        try {
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            logger.error("创建会话异常", e);
        }

        //创建消息传递的目的地：消息队列
        Destination destination = null;
        try {
            destination = session.createQueue("TestQueue");
        } catch (JMSException e) {
            logger.error("创建消息队列出现异常", e);
        }

        //创建消息消费者
        MessageConsumer consumer = null;
        try {
            consumer = session.createConsumer(destination);
        } catch (JMSException e) {
            logger.error("创建消费者异常", e);
        }

        //接收消息
        ObjectMessage message = null;
        while (handleFlag) {
            try {
                message = (ObjectMessage) consumer.receive();
                String msg = (String) message.getObject();
                logger.info("consumer receive a message");
                logger.info(msg);
            } catch (JMSException e) {
                logger.error("接收消息异常", e);
            }
        }

        //提交会话
        try {
            session.commit();
        } catch (JMSException e) {
            logger.error("提交会话异常", e);
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                logger.error("关闭连接异常", e);
            }
        }
    }
}
