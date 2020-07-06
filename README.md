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
healthcheck, the script waits till grid is ready and executes java -cp command which starts test suite (module)<br />

Note: selenium grid  could be started separately than in docker-compose.yml just change HUB_HOST domain and port
in environment section of module<br />

Configuration browsers for selenoid located in selenoid/browsers.json<br />
Video results for run located in selenoid/video<br />

For building image and pushing it to dockerhub Jenkinsfile is used <br/>
Master jenkins starts with command <br/>
docker run -p 8899:8080 -p 50000:50000 -v "$PWD/jenkins-data:/var/jenkins_home" jenkins/jenkins:lts <br/>

In Jenkins we install default plugins and <br/>
!!! Important to install Docker pipeline plugin <br/>
for using docker methods in Jenkinsfile <br/>

Master executors must be set to 0 !!! <br/>
Because executor node has to have docker <br/>

For actual slave I use my own machine:<br/>
1. Add node slave in Jenkins config<br/>
2. Set remote folder which will be used for saving data for slave e.g. /home/oem/jenk<br/>
3. Download agent.jar <br/>
4. Run following command, which will be shown in jenkins <br/>
java -jar agent.jar -jnlpUrl <br/>
http://localhost:8899/computer/mymachine/slave-agent.jnlp<br/>
-secret 32fd223949644a78739c827487c5f611461230258ac11d322525b54578d881be <br/>
-workDir "/home/oem/jenk"<br/>

5. Set executors to 2 or 3 for this slave<br/>

6. For Jenkins Job config set Pipeline script from SCM<br/>
7. Set repository URL for git where is your code with Jenkinsfile<br/>
8 Set script path in Job config to Jenkinsfile if it is placed in root of <br/>
your project  <br/>