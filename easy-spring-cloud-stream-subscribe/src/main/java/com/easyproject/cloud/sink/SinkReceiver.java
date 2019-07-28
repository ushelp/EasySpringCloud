package com.easyproject.cloud.sink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

//实现对消息通道（Channel）的绑定:指定一个或多个定义了 @Input 或 @Output 注解的接口。
@EnableBinding(Sink.class)
public class SinkReceiver {
	private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

	// 将方法注册为消息中间件上数据流的事件监听器，指定订阅目标名称
	@StreamListener(Sink.INPUT)
	public void receive(Object payload) {
		logger.info("Received: " + payload);
	}

}
