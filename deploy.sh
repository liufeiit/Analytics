#!/bin/sh
mvn clean
mvn install
echo "copy war..."
cp Analytics-Web/target/Analytics-Web.war ~/dev/jboss-as-web-7.0.2.Final/standalone/deployments/
echo "deploy success!"
sh /home/lf/dev/jboss-as-web-7.0.2.Final/bin/standalone.sh

