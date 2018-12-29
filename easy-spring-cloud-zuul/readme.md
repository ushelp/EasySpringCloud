# Easy Spring Cloud Zuul

基于 Eureka 服务实例自动化检测的服务网关，提供动态路由及过滤器。
可实现鉴权，安全控制，日志审计，静态响应，路由转发等。

支持：

- 动态路由
- 过滤器
- 错误过滤器
- Ribbon 负载均衡
- FallBack 失败备援
- Retry 重试

## 访问

```
http://localhost:5000/{service-id}/xxx
```

http://127.0.0.1:5000/users-consumer-feign/users/100000

# END