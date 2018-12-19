package com.easyproject.cloud.source;

import javax.annotation.Resource;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

//绑定输出信息通道接口
@EnableBinding(Source.class)
public class SourceSender {

	@Resource
	private Source source;

	public void send(String msg) {
		//发送信息到输出通道
		source.output().send(MessageBuilder.withPayload(msg).build());
	}
}
