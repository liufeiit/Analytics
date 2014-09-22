#!/bin/sh

echo "ps aux | grep $1"
pids=`ps aux | grep $1 | awk '{ print $2}'`
#echo "pids = $pids"
for pid in $pids ; do
echo "kill -9 $pid"
kill -9 $pid
done



