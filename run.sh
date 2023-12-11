#!/bin/bash

mvn clean package 

docker build -t pmattest .
 
docker stop pmattest-container

docker rm pmattest-container 

docker run --name pmattest-container -p 8080:8080 pmattest