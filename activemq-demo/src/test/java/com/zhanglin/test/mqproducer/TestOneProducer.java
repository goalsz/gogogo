package com.zhanglin.test.mqproducer;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("testOneProducer")
public class TestOneProducer {

	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendMessage(Destination destination, String message) {
		jmsTemplate.convertAndSend(destination, message);
	}
	
}
