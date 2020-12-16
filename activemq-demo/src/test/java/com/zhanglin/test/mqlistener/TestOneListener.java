package com.zhanglin.test.mqlistener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("testOneListener")
public class TestOneListener implements MessageListener{

	Logger logger = LoggerFactory.getLogger(TestOneListener.class);

	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			logger.info("接受到消息:{}, 优先级为:{}", msg.getText(), msg.getJMSPriority());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
