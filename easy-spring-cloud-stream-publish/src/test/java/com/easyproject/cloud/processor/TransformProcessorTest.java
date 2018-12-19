package com.easyproject.cloud.processor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.easyproject.main.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TransformProcessorTest {
	
	
	
	@Autowired
	private TransformProcessor transformProcessor;

	@Test
    public void sinkSenderTester() {
		Object msg=transformProcessor.transform("Spring cloud stream! Procesor！");
		System.out.println(msg);
    }
}
