pipeline {
	agent any

    stages {
		stage('Checkout') {
			steps {
				checkout scm
            }
        }

        stage('Build & Test') {
			steps {
				bat 'mvn -q clean test package'
            }
            post {
				always {
					junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('Docker Build') {
			steps {
				bat 'docker build -t pipeline-demo:latest .'
            }
        }

        stage('Run (Smoke)') {
			steps {
				bat 'docker run --rm pipeline-demo:latest > output.txt'
                bat 'findstr /C:"Hello World" output.txt'
            }
        }
    }
}
