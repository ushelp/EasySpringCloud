# Easy Spring Cloud Config Server


支持 Git, SVN, Native 模式的高可用 Config Server(分布式配置管理中心)。


## 在项目中启用 Config Server


Easy Spring Cloud 项目均已实现和集成了 `Config Server Native` 模式，但默认未启用 Config Server。

在 Config Client 项目的 `bootstrap.yml` 中可直接开启或关闭配置中心：

```properties
spring.cloud.config.enabled=true
```

## Native Configuration

1. 将 Config Client（分布式配置客户端）的配置文件保存到 Config Server（分布式配置中心）的 `classpath:/config/{application}/application-{profile}.yml` 中。

2. 在 Config Client（分布式配置客户端）中创建 `bootstrap.yml` 文件，开启分布式配置中心

	```yml
	spring:
	  application:
	    name: users-provider # {application}
	  cloud:
	    config:
	      # Use Spring Cloud Config Server 
	      enabled: true 
	      # Config file
	      profile: dev # {profile}
	#      profile: default
	      label: master
	      # Direct Config Server
	#      uri: http://admin:123@localhost:6001/
	      # Eureka Config Server
	      discovery:
	        enabled: true
	        serviceId: config-server # Config Server 'spring.application.name'
	      ## Config Server Security
	      password: 123
	      username: admin
	
	# Eureka Config Server
	eureka:
	  client:
	    serviceUrl:
	      defaultZone: http://admin:123@localhost:8701/eureka
	#      defaultZone: http://admin:123@host1:8701/eureka/,http://admin:123@host2:8702/eureka/,http://admin:123@host3:8703/eureka/
	
	```

## 文件拉取测试

```BASH
curl http://admin:123@localhost:6001/users-provider/dev
```

对应 Config Server 的 `classpath:/config/users-provider/application-dev.properties`文件。 


## 配置刷新地址

- 客户端配置刷新

	```
	curl -X POST http://{client-host}:{client-port}/actuator/refresh
	```

- 基于 `Spring Cloud Bus` 的全局统一刷新（**需要安装配置 Kafka 或  RabbitMQ**）

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
	```
	
	 ```
	 curl -X POST http://admin:123@127.0.0.1:6001/actuator/bus-refresh
	 ```

# END
