#!/bin/bash
java -jar -Dspring.config.additional.location=application-kafka-elasticsearch.yml zipkin-server-2.12.9-exec.jar 
# nohup java -jar -Dspring.config.additional.location=application-kafka-elasticsearch.yml zipkin-server-2.12.9-exec.jar &