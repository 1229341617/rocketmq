package com.limao.rocketmq._02_makeorder;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class RocketMQProducer {
	//创建一个生产者
	public static DefaultMQProducer producer;
	
	static {
		try {
			//1.初始化一个名为order_producer_group的生产者对象
			producer = new DefaultMQProducer("order_producer_group");
			//2.为生产者对象指定路由中心的地址,方便通过NameServer路由到需要写入数据的Master Broker
			producer.setNamesrvAddr("localhost:8080");
			//3.启动一个生产者
			producer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}
	
	public static void send(String topic, String message) throws Exception {
		//1.创建一个消息对象
		Message msg = new Message(topic,//指定发送到哪个Topic上去
									"", //指定tag
									message.getBytes(RemotingHelper.DEFAULT_CHARSET));//将消息字符串转换为byte[]
		//2.使用生产者发送消息到RocketMQ
		SendResult sendResult = producer.send(msg);
		System.out.println(sendResult);
	}
	
}
