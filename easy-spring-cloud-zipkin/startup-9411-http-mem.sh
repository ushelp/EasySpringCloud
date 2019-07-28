#!/bin/bash
java -jar -Dspring.config.additional.location=application-http-mem.yml zipkin-server-2.12.9-exec.jar
# nohup java -jar zipkin-server-2.12.9-exec.jar &