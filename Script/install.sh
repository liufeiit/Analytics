#!/bin/sh
cd ..
mvn clean install -Pprd -Dmaven.test.skip=true

