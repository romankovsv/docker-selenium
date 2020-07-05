#!/usr/bin/env bash

echo "!!! _________________Docker compose down execution"
docker-compose down

echo "!!!__________________Maven clean package execution"
mvn clean package -DskipTests

echo "!!!__________________Update image"
docker build -t=sromankov/selenium-docker .

echo "!!!__________________Docker compose up exec"
docker-compose up

