#!/bin/bash
java -jar -Dspring.config.location=application.yml zipkin-server-2.11.12-exec.jar
# nohup java -jar zipkin-server-2.11.12-exec.jar &