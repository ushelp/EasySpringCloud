# Easy Spring Cloud Eureka Server

快速启动分布式微服务注册中心。

## Configuration

- hosts(`Local clusetr test`)

	```
	127.0.0.1 host1
	127.0.0.1 host2
	127.0.0.1 host3
	```


- Standalone Mode

	Port:`8701`

- Cluster Mode

	 ```Properties
	 eureka.instance.hostname=YOUR-HOST-NAME
	 eureka.client.serviceUrl.defaultZone=YOUR-CLUSTER-NODES
	 ```
 
## Run
 
- Standalone Mode

- Cluster Mode

	 ```Properties
	 spring.profiles.active=host1
	 ```
	 	 
	 or
	 
	 ```BASH
	 mvn springboot:run -Dspring.profiles.active=host2
	 ```
	 
	 or
	 
	 ```SHELL
	 java -jar xxxx.jar --spring.profiles.active=host3
	 ```

# END