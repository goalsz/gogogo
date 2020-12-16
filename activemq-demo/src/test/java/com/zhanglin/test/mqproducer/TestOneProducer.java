package com.zhanglin.test.mqproducer;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component("testOneProducer")
public class TestOneProducer {

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("testOneQueue")
	Destination notifyQueue;

	public void sendMessage(String message) {
		jmsTemplate.convertAndSend(notifyQueue, message);
	}
	
	public void sendMessage(String message, int priority) {
		jmsTemplate.setPriority(priority);
		jmsTemplate.convertAndSend(notifyQueue, message);
	}
	
}
