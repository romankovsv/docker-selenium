#!/usr/bin/env bash
echo "Checking if hub is ready - $HUB_HOST"

echo "Remove videos"
 rm ./selenoid/video/*
echo "Videos are removed"


#while [[ "$( curl -s http://$HUB_HOST:4444/wd/hub/status | jq -r .value.ready)" != "true" ]];
#do
 #   sleep 1
#done

#while [[ "docker inspect --format "{{json .State.Status}}" $(docker inspect --format="{{.Id}}" selenoid)" != "running" ]];
#do
   # sleep 1
#done

echo "Checking is done"
# start java command
java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* \
-DHUB_HOST="$HUB_HOST" \
-DBROWSER="$BROWSER"   \
org.testng.TestNG "$MODULE"