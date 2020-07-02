FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

WORKDIR /usr/share/test

ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs

ADD search_module.xml search_module.xml
ADD testng.xml testng.xml

ADD healthcheck_and_run.sh healthcheck_and_run.sh

ENTRYPOINT sh healthcheck_and_run.sh
