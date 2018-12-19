# Easy Spring Cloud Eureka Server

快速启动分布式微服务注册中心。

## Configuration

- Standalone Mode

	Port:`8700`

- Cluster Mode

	 ```Properties
	 eureka.instance.hostname=YOUR-HOST-NAME
	 eureka.client.serviceUrl.defaultZone=YOUR-CLUSTER-NODES
	 ```
 
## Run
 
- Standalone Mode

- Cluster Mode

	 ```Properties
	 spring.profiles.active=localhost
	 ```
	 	 
	 or
	 
	 ```BASH
	 mvn springboot:run -Dspring.profiles.active=localhost2
	 ```
	 
	 or
	 
	 ```SHELL
	 java -jar xxxx.jar --spring.profiles.active=localhost3
	 ```

# END