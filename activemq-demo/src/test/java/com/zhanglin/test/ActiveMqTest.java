package com.zhanglin.test;

import javax.jms.Destination;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.zhanglin.test.mqproducer.TestOneProducer;

@Component
public class ActiveMqTest {

    private final Logger logger = LoggerFactory.getLogger(ActiveMqTest.class);
    
    @Autowired
    TestOneProducer testOneProducer;
    @Autowired
    Destination testOneQueue;
    
    
    /**
     * 测试mq消息消费
     * @throws Exception 
     */
    @Test
    public void testMq() throws Exception{
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    	testOneProducer.sendMessage(testOneQueue, "hello");
    	Thread.sleep(20000);
    	context.destroy();
    }
}
