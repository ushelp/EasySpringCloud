package com.easyproject.cloud.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Processor {
	String INPUT = "msgA";
	String OUTPUT = "msgB";

	@Output(Processor.INPUT)
	MessageChannel output();

	@Input(Processor.OUTPUT)
	SubscribableChannel input();
}
