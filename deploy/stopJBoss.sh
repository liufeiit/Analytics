#!/bin/sh
echo "stop JBoss"
$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:9999 --commands=:shutdown
pid=`ps aux |grep jboss |grep java| awk '{ print $2}'`
echo $pid
kill -9 $pid
echo "stop JBoss success."

