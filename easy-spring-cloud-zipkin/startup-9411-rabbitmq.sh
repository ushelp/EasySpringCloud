#!/bin/bash
java -jar -Dspring.config.location=application-rabbitmq.yml zipkin-server-2.11.12-exec.jar 
# nohup java -jar zipkin-server-2.11.12-exec.jar --server.port=9411 --zipkin.collector.rabbitmq.addresses=localhost --zipkin.collector.rabbitmq.username=admin --zipkin.collector.rabbitmq.password=123 &