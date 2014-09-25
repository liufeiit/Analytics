#!/bin/sh
mvn clean
mvn install
echo "copy war..."
cp ~/Analytics-Web/target/Analytics-Web.war ~/dev/jboss-as-web-7.0.2.Final/standalone/deployments/
echo "deploy success!"

#scp ~/Analytics/Analytics/Analytics-Web/target/Analytics-Web.war ubuntu@stats:/home/ubuntu/soft/jboss-as-web-7.0.2.Final/standalone/deployments/
echo "scp to stats server success."
#nohup sh standalone.sh > nohup.out 2>&1 &
sh /home/lf/dev/jboss-as-web-7.0.2.Final/bin/standalone.sh

