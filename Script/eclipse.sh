#!/bin/sh
cd ..
mvn eclipse:clean
mvn eclipse:eclipse -DdownloadSources=true -DdownloadJavadocs=true
