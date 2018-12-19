package com.easyproject.cloud.processor;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.Transformer;

@EnableBinding(Processor.class)
public class TransformProcessor {

	@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Object transform(String message) {
		System.out.println("transform: " + message);
		return message.toUpperCase();
	}

}
