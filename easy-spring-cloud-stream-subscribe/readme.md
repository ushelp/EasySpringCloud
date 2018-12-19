# Easy Spring Cloud Stream Subscribe

Spring 消息驱动订阅者。

- Source 示例支持。
- Processor 示例支持。
- 支持 Group 消费组。

## 测试步骤

1. 启动 `easy-spring-cloud-eureka-server`
2. 启动 `easy-spring-cloud-config-server`
3. 启动 `easy-spring-cloud-stream-subscribe`
2. 运行`easy-spring-cloud-stream-publish`中的单元测试类
   - `SourceSenderTest`测试消息发送
   - `TransformProcessorTest` 测试消息发送并接收输入数据
  
  
  # END
  