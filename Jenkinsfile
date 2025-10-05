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


}
