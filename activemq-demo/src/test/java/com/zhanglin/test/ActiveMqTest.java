package com.zhanglin.test;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhanglin.test.mqproducer.TestOneProducer;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring.xml"})
public class ActiveMqTest {

    private final Logger logger = LoggerFactory.getLogger(ActiveMqTest.class);
    
    @Autowired
    TestOneProducer testOneProducer;
    @Autowired
    DefaultMessageListenerContainer testOneContainer;




	/**
     * 测试mq消息消费
     * @throws Exception 
     */
    @Test
    public void testMq(){
    	try {
    		testOneProducer.sendMessage("hello");
    		Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 测试mq消息消费优先级
     * @throws Exception 
     */
    @Test
    public void testMqPriority(){
    	try {
    		for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 10; j++) {
					testOneProducer.sendMessage("hello", j);
				}
			}
    		Thread.sleep(2000);
    		testOneContainer.start();
    		Thread.sleep(20000);

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * 测试mq  jmx读取消费情况
     * @throws Exception
     * 需配置mq服务的配置文件 conf/activemq.xml
     * <managementContext>
           <managementContext createConnector="true" connectorPath="/jmxrmi" connectorPort="9090" jmxDomainName="org.apache.activemq"/>
       </managementContext> 
       	引入jar包依赖 activemq-broker
       	
     */
    @Test
    public void testMqJMX(){
    	String url = "service:jmx:rmi:///jndi/rmi://localhost:9090/jmxrmi";
		try {
			JMXServiceURL urls = new JMXServiceURL(url);
			
		    JMXConnector connector = JMXConnectorFactory.connect(urls,null);
		    connector.connect();
		    MBeanServerConnection conn = connector.getMBeanServerConnection();
		    //这里brokerName的b要小些，大写会报错
		    ObjectName name = new ObjectName("org.apache.activemq:brokerName=localhost,type=Broker");
		    BrokerViewMBean mBean = (BrokerViewMBean)MBeanServerInvocationHandler.newProxyInstance(conn, name, BrokerViewMBean.class, true);
		    for(ObjectName na : mBean.getQueues()){//获取点对点的队列       mBean.getTopics() 获取订阅模式的队列
		        QueueViewMBean queueBean = (QueueViewMBean) 
		        MBeanServerInvocationHandler.newProxyInstance(conn, na, QueueViewMBean.class, true);
		        System.out.print("队列名："+queueBean.getName()+"\t");
		        System.out.print("未消费消息数："+queueBean.getQueueSize()+"\t");
		        System.out.print("消费者数："+queueBean.getConsumerCount()+"\t");
		        System.out.print("入队数："+queueBean.getDequeueCount()+"\t");
		        System.out.println("出队数："+queueBean.getDequeueCount()+"\t");
		        System.out.println("----------------------------------");
		    }
	    } catch (Exception e) {
			e.printStackTrace();
		}
	}
}