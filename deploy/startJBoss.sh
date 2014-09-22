#!/bin/sh
echo "start JBoss"
cd $JBOSS_HOME/bin/
nohup sh standalone.sh > nohup.out 2>&1 &
echo "start JBoss success."
