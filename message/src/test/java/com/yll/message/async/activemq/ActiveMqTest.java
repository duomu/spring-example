package com.yll.message.async.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * @author：linlin.yang
 * @date：2017/10/26 19:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-activemq.xml")
public class ActiveMqTest {
    private static final Logger logger = LoggerFactory.getLogger(ActiveMqTest.class);
    @Resource(name = "jmsTemplate")
    private JmsOperations jmsOperations;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Test
    public void testSendMessage() {
//        jmsTemplate.send("FirstQueue", new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage("hello duomu");
//            }
//        });

        //更简单
        jmsTemplate.convertAndSend("hello linlin");
    }

    @Test
    public void testReceiveMessage(){
//        if (jmsTemplate.receive() != null) {
//            try {
//                ObjectMessage objectMessage = (ObjectMessage) jmsTemplate.receive();
//                logger.info("" + objectMessage.getObject());
//            } catch (JMSException e) {
//                logger.error("接收消息异常", e);
//            }
//        }

        if (jmsTemplate.receiveAndConvert() != null) {
            logger.info("" + jmsTemplate.receiveAndConvert());
        }

    }
}
