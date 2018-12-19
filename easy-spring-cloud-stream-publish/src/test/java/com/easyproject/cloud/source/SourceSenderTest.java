package com.easyproject.cloud.source;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.easyproject.cloud.source.SourceSender;
import com.easyproject.main.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SourceSenderTest {
	
	@Autowired
	private SourceSender sourceSender;

	@Test
    public void sinkSenderTester() {
		sourceSender.send("Spring cloud stream! Source！");
    }
}
