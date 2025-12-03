pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean test package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t pipeline-demo .'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker rm -f pipeline-demo || true'
                sh 'docker run -d -p 9091:8080 --name pipeline-demo pipeline-demo'
            }
        }
    }
}
