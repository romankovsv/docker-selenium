pipeline {
    agent none
    stages {
        stage('Build Jar') {
            agent {
            //agent has to have maven
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
            // Make jars with code and test and put libs dependencies
            //according to configs in pom.xml
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
        //Build Image using Dockerfile instructions
            steps {
                script {
                	image = docker.build("svromankov/selenium-docker")
                }
            }
        }
        stage('Push Image') {
            steps {
                script {
                //push app image to registry (dockerhub) with creds marked with ID dockerhub in Jenkins credentials
			        docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
			        	image.push("${BUILD_NUMBER}"+"latest")

			        }
                }
            }
        }

    }
}