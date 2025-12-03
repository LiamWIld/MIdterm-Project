node {

  stage('Checkout') {
    checkout scm
  }

  stage('Build & Test') {
    sh '''
      chmod +x mvnw || true
      ./mvnw clean test package
    '''
    junit '**/target/surefire-reports/*.xml'
    archiveArtifacts artifacts: 'target/*.jar'
  }

  stage('Performance Testing') {
    sh '''
      docker run --rm \
        -v $WORKSPACE/jmeter:/tests \
        -v $WORKSPACE/results:/results \
        justb4/jmeter \
        -n -t /tests/loadtest.jmx \
        -l /results/output.jtl
    '''
    archiveArtifacts 'results/**'
  }

  stage('Deploy') {
    sh '''
      docker build -t pipeline-demo .
      docker rm -f pipeline-demo || true
      docker run -d -p 8080:8080 --name pipeline-demo pipeline-demo
    '''
  }

  post {
    failure {
      echo "Build or deployment failure detected"
    }
  }
}
