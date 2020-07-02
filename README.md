Used technologies:  <br />

Docker and docker compose should be installed <br />

Command to start up needed Selenium Grid <br />

docker-compose up -d --scale chrome=4 --scale firefox=4 <br />

To run tests either search_module.xml or testng.xml should be run <br />

To run in docker: <br />

1) in project directory docker-compose up -d <br />
2) docker run -it --entrypoint=/bin/sh sromankov/selenium-docker <br />

3) java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DHUB_HOST=<host> org.testng.TestNG
 search_module.xml <br />