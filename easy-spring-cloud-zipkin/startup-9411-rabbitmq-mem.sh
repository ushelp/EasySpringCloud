#!/bin/bash
java -jar -Dspring.config.additional.location=application-rabbitmq-mem.yml zipkin-server-2.12.9-exec.jar 
# nohup java -jar -Dspring.config.additional.location=application-rabbitmq.yml zipkin-server-2.12.9-exec.jar &