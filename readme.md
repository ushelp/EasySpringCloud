# Easy Spring Cloud

基于 Spring Cloud 的快速云开发基础环境。

提供 Spring Cloud 生态下最新(`GA 版本`)并经测试的开发基础框架，集成了 Spring Cloud 常用组件 ，让您无需在框架构建，调试方面花费精力，快速启动您的云开发业务。


## 当前最新版本

|Spring Cloud Version | Spring Boot Version|
|:---:|:---:|
| Finchley.SR2 | 2.0.7.RELEASE |

## 主要组件

- Eureka 服务注册中心，服务注册发现
- Spring Cloud Config 分布式配置管理
	- Git, SVN, Native
	- 支持 Spring Cloud Bus 全局刷新
- Feign 服务消费客户端
- RestTemplate 服务消费客户端
- Hystrix 断路器，容错保护
- Hystrix Dashboard 系统监控
- Turbine 分布式集群监控 
- Ribbon 客户端负载均衡
- Zuul 服务网关
- Stream 消息驱动服务
	- Kafka, RabbitMQ
- Sleuth 和 Zipkin 分布式服务追踪系统(Distributed Systems Tracing System)
	- HTTP 收集器 + Mem 存储
	- Kafka 收集器 + ElasticSearch 存储
	- RabbitMQ 收集器 + ElasticSearch 存储

## 包含组件

- `easy-spring-cloud-eureka-server`

	快速启动分布式微服务注册中心。
	
	提供基于 Eureka 的 Server 注册中心。支持  `Standalone Mode` 和 `Cluster Mode`。

- `easy-spring-cloud-config-server`

	支持 Git, SVN, Native 模式的高可用 Config Server(分布式配置管理中心)。
	
	- Easy Spring Cloud 项目均已实现和集成了`Native` 模式，但默认未启用 Config Server。在 Config Client 项目的 `bootstrap.yml` 中可直接开启或关闭配置中心服务

		```properties
		spring.cloud.config.enabled=true
		```

- `easy-spring-cloud-eureka-provider`

	基于 Eureka 的服务提供者。
	
	- 支持文件上传下载

- `easy-spring-cloud-eureka-consumer-feign`

	基于 Feign 的服务消费客户端。
	
	- 支持 Fallback 失败备援
	- 集成了 Hystrix 断路器和 Hystrix Dashboard 监控
	- 支持文件上传下载


- `easy-spring-cloud-eureka-consumer-resttemplate`

	基于 RestTemplate 的服务消费客户端。
	
	- 支持 Fallback 失败备援
	- 集成了 Hystrix 断路器和 Hystrix Dashboard 监控
	- 支持文件上传下载	
	
- `easy-spring-cloud-zuul`

 	 基于 Eureka 的服务网关，提供动态路由及过滤器。
 	 - Filter 常用过滤器
	 - Ribbon 负载均衡
	 - FallBack 失败备援
	 - Retry 重试

- `easy-spring-cloud-turbine`

	Hytrix 聚合监控系统 Turbine。
	
	- 支持集群聚合分类监控

- `easy-spring-cloud-stream-publish`

	Spring Cloud Stream 消息驱动服务发布者。
	
	- Source 示例支持
	- Processor 示例支持

- `easy-spring-cloud-stream-subscribe`

	Spring Cloud Stream 消息驱动服务订阅者。
	
	- Source 示例支持
	- Processor 示例支持
	- 支持 Group 消费组

- `easy-spring-cloud-zipkin`

	基于 Sleuth 和 Zipkin 的分布式服务追踪技术(Distributed Systems Tracing System) 。
	- 耗时分析，可视化错误，链路优化
	- HTTP Collector + Mem Storage
	- Kafka Collector  + Mem Storage
	- RabbitMQ Collector  + Mem Storage
	- Kafka Collector  + ElasticSearch Storage
	- RabbitMQ Collector  + ElasticSearch Storage


## 结构

![easy-spring-cloud](docs/imgs/easy-spring-cloud.png)

	

## 基本启动步骤

**Eureka** --> **Config** --> **Kafka/RabbitMQ**（`可选`） --> **Provider** --> **Consumer** --> **Zuul** --> **Turbine**(`可选`) --> **Zipkin**(`可选`)


## Kafka/RabbitMQ

- 可选  Kafka/RabbitMQ 服务运行的项目
	- Spring Cloud Bus 配置中心全局刷新服务
	- Zipkin Kafka/RabbitMQ Collector 收集服务

- 必须  Kafka/RabbitMQ 服务运行的项目
	- Spring Cloud Stream
	

- EasySpringCloud 配置 Kafka 或 RabbitMQ 信息

    为了减少项目启动对第三方中间件的依赖，EasySpringCloud 所有项目**注释了 `Sleuth +Zipkin` 和 `Spring Cloud Bus` 运行配置**。如有需要，可按如下配置开启即可。
	
	
	- Maven
	
		```XML
		<!-- Spring Cloud Bus: Kafka or RabbitMQ -->
		<!-- Kafka -->
		<dependency>
		  <groupId>org.springframework.cloud</groupId>
		  <artifactId>spring-cloud-starter-bus-kafka</artifactId>
		</dependency>
		<!-- RabbitMQ -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-bus-amqp</artifactId>
		</dependency>
		
		<!-- Sleuth & Zipkin -->
		<!-- Sleuth with Zipkin over HTTP -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-zipkin</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-sleuth</artifactId>
		</dependency>
		<!-- Sleuth with Zipkin over RabbitMQ -->
		<dependency>
			<groupId>org.springframework.amqp</groupId>
			<artifactId>spring-rabbit</artifactId>
		</dependency>
		```
	
	- `Zipkin Kafka Collector` + `Spring Cloud Bus`
	
		```YML
		spring:
		  cloud:
		    stream:
		      # e.g., 'kafka', 'rabbit'
		      default-binder: kafka
		  # Kafka (Zipkin & Spring Cloud Bus)
		  kafka: 
		    bootstrap-servers:
		     - localhost:9092
		  # zipkin Kafka 收集
		  zipkin:
		    sender:
		      type: kafka
		  # Sleuth  
		  sleuth: 
		    sampler: 
		      probability: 1.0 
		```

	- `Zipkin RabbitMQ Collector` + `Spring Cloud Bus` 
	
		```YML
		spring:
		  cloud:
		    stream:
		      # e.g., 'kafka', 'rabbit'
		      default-binder: rabbit
		  # RabbitMQ (Zipkin & Spring Cloud Bus)
		  rabbitmq:
		    host: localhost
		    password: 123
		    port: 5672
		    username: admin
		  # Sleuth  
		  sleuth: 
		    sampler: 
		      probability: 1.0 
		```
	
	- `Zipkin HTTP Collector` + `Spring Cloud Bus(Kafka)`
	
		```YML
		spring:
		  cloud:
		    stream:
		      # e.g., 'kafka', 'rabbit'
		      default-binder: kafka
		  # Kafka (Spring Cloud Bus)
		  kafka: 
		    bootstrap-servers:
		     - localhost:9092
		  # zipkin HTTP 收集
		  zipkin: 
		    baseUrl: http://127.0.0.1:9411
		    sender:
		      type: web
		  # Sleuth  
		  sleuth: 
		    sampler: 
		      probability: 1.0 
		```

## 端口速查

- Eureka Server：`8700`, `8701`, `8702`
- Spring Cloud Config Server: `6000`
- Service Provider：`8800`
- Service Consumer Feign：`8900`
- Service Consumer RestTemplate：`8901`
- Zuul: `5000`
- Turbine：`3000`
- Zipkin: `9411`
- Kafka: `9092`
- RabbitMQ: `5672`
		
# End

Email：<inthinkcolor@gmail.com>

[http://www.easyproject.cn](http://www.easyproject.cn "EasyProject Home")


**支付宝钱包扫一扫捐助：**

我们相信，每个人的点滴贡献，都将是推动产生更多、更好免费开源产品的一大步。

**感谢慷慨捐助，以支持服务器运行和鼓励更多社区成员。**

<img alt="支付宝钱包扫一扫捐助" src="http://www.easyproject.cn/images/s.png"  title="支付宝钱包扫一扫捐助"  height="256" width="256"></img>



We believe that the contribution of each bit by bit, will be driven to produce more and better free and open source products a big step.

**Thank you donation to support the server running and encourage more community members.**

[![PayPal](http://www.easyproject.cn/images/paypaldonation5.jpg)](https://www.paypal.me/easyproject/10 "Make payments with PayPal - it's fast, free and secure!")
