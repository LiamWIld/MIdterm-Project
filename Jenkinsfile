pipeline {
	agent any
  stages {
		stage('Checkout') {
			steps { checkout scm }
    }

    stage('Build & Test') {
			steps {
				sh 'chmod +x mvnw'
        sh './mvnw -q clean test package'
      }
      post {
				always {
					junit '**/target/surefire-reports/*.xml'
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
      }
    }

    stage('Docker Build') {
			steps { sh 'docker build -t pipeline-demo:latest .' }
    }

    stage('Run (Smoke)') {
			steps {
				sh 'docker run --rm pipeline-demo:latest | tee output.txt'
        sh 'grep -q "Hello World" output.txt'
      }
    }
  }
}