#!/bin/bash
mvn clean package -e wildfly:deploy
#mvn flyway:migrate
#mvn checkstyle:check
#cp target/springbootwildfly.war /opt/jboss/wildfly/standalone/deployments/