FROM openjdk:8u191-jre-alpine3.8

WORKDIR /usr/share/test

ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs

ADD search_module.xml search_module.xml
ADD testng.xml testng.xml
ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/*  -DBROWSER=$BROWSER  -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE
