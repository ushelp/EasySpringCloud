#!/bin/bash
java -jar -Dspring.config.additional.location=application-kafka-mem.yml zipkin-server-2.12.9-exec.jar 
# java -jar -Dspring.config.additional.location=application-kafka.yml zipkin-server-2.12.9-exec.jar &