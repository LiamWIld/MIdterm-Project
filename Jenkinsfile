node {
	stage('Checkout') {
		checkout scm
  }

  stage('Build & Test') {
		sh '''
      set -eux
      ls -la
      [ -x mvnw ] || chmod +x mvnw
      sed -i 's/\r$//' mvnw || true
      ./mvnw -q -B -V clean test package
    '''
    junit '**/target/surefire-reports/*.xml'
    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
  }

  stage('Docker Build') {
		sh 'docker build -t pipeline-demo:latest .'
  }

  stage('Run (Smoke)') {
		sh 'docker run --rm pipeline-demo:latest | tee output.txt'
    sh 'grep -q "Hello World" output.txt'
  }
}
