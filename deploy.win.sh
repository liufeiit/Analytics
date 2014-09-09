#!/bin/sh
mvn install
echo "copy war..."
cp Analytics-Web/target/Analytics-Web.war D://software/jboss-as-web-7.0.1.Final/standalone/deployments
echo "deploy success!"
mvn clean
echo "clean success!"
