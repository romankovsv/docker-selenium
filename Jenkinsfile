pipeline {
    agent none
    stages {
        stage('Build Jar') {
            agent {
                any {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Image') {
            steps {
                script {
                	sh "docker build -t='sromankov/selenium-docker' ."
                }
            }
        }
        stage('Push Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]){
                    sh "docker login --username${user} --password=${pass}"
                    sh "docker push sromankov/selenium-docker:latest"
                }
            }
        }
    }
}