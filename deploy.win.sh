#!/bin/sh
mvn install
echo "copy war..."
cp Analytics-Web/target/Analytics-Web.war D://software/jboss-as-web-7.0.1.Final/standalone/deployments
echo "deploy success!"

sh D:/software/jboss-as-web-7.0.1.Final/bin/standalone.sh
