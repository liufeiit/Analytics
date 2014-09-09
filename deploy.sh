#!/bin/sh
mvn install
echo "copy war..."
cp Analytics-Web/target/Analytics-Web.war ~/dev/jboss-as-web-7.0.2.Final/standalone/deployments/
echo "deploy success!"
mvn clean
mvn eclipse:clean
mvn eclipse:eclipse
echo "clean success!"
sh /home/lf/dev/jboss-as-web-7.0.2.Final/bin/standalone.sh

