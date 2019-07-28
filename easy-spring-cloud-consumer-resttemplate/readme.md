# Easy Spring Cloud Consumer RestTemplate

可与 Eureka 服务中心集成的基于 RestTemplate 的服务消费客户端。
	
 - 支持 Fallback 失败备援
 - 集成了 Hystrix 断路器和 Hystrix Dashboard 监控
 - 集成了 Ribbon 负载均衡
 - 支持文件上传下载

## Test

- `users/{id}` (When `id<10000`, the `data` is null)

	http://localhost:8902/users/10002
	
	http://localhost:8902/users/9000 

- `users/upload` (`int id, MultipartFile file`)

	http://localhost:8902/users/upload
	
- `users/download`

	http://localhost:8902/users/download
	
## Hystrix

- Hystrix Dashboard: http://localhost:8902/hystrix
- Hystrix Stream: http://localhost:8902/actuator/hystrix.stream

# END
