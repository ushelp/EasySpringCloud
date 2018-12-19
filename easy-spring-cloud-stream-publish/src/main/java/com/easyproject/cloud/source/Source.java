package com.easyproject.cloud.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {

	// 输出数据的发布目标名称
	String OUTPUT = "input";

	@Output(Source.OUTPUT)
	MessageChannel output();
}
