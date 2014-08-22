#!/bin/sh
cd /home/lf/workspace/Swan
mvn clean package -Pprd -Dmaven.test.skip=true
cp /home/lf/workspace/Swan/Swan-Web/target/Swan-Web.war /home/lf/dev/jboss-as-7.1.1.Final/standalone/deployments/
