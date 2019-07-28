# Easy Spring Cloud Provider

可与 Eureka 服务中心集成的微服务提供者结构。

 - 支持`Standalone Mode` 或 `Cluster Mode`Eureka
 - 支持文件上传下载

## Test

- `users/{id}` (When `id<10000` return null)

	http://localhost:8801/users/10002
	
	http://localhost:8801/users/9000 

- `users/upload` (`int id, MultipartFile file`)

	http://localhost:8801/users/upload
	
- `users/download`

	http://localhost:8801/users/download
	
# END