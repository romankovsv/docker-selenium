Used technologies:  <br />

Docker and docker compose should be installed <br />

Command to start up needed Selenium Grid <br />

docker-compose up -d --scale chrome=4 --scale firefox=4 <br />

To run tests either search_module.xml or testng.xml should be run <br />

To run in docker: <br />

1) in project directory docker-compose up -d <br />
2)  For debugging purposes: docker run -it --entrypoint=/bin/sh sromankov/selenium-docker <br />

3) java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DHUB_HOST=<host> org.testng.TestNG
 search_module.xml <br />


1. After changing code using maven plugins create two jars and put all libraries to folder libs<br />
execute command:<br />

mvn clean package -DskipTests <br />

2. Update image <br />
docker build -t=sromankov/selenium-docker . <br />


3. All environment variables are set in docker-compose.yml file <br />
execute command <br />
docker-compose up -d<br />

docker-compose starts services for selenium hub and nodes, than executes entrypoint which starts bash script
healthcheck, the script waits till grid is ready and executes java -cp command which starts test suite (module)

Note: selenium grid  could be started separately than in docker-compose.yml just change HUB_HOST domain and port
in environment section of module