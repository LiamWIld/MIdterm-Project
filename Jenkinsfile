pipeline {
    agent any

    stages {

        stage('Build & Test') {
            steps {
                bat 'mvnw.cmd clean package'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t pipeline-demo .'
            }
        }

        stage('Deploy') {
            steps {
                bat 'docker rm -f pipeline-demo || exit 0'
                bat 'docker run -d -p 9091:8080 --name pipeline-demo pipeline-demo'
            }
        }
    }
}
