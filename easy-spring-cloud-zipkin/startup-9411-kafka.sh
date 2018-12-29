#!/bin/bash
java -jar -Dspring.config.location=application-kafka.yml zipkin-server-2.11.12-exec.jar 
# java -jar -Dspring.config.location=application-kafka.yml zipkin-server-2.11.12-exec.jar &