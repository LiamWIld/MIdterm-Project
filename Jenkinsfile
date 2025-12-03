node {

    stage('Checkout') {
        checkout scm
    }

    stage('Build & Test') {
        bat '''
            mvnw.cmd clean test package
        '''
        junit '**/target/surefire-reports/*.xml'
        archiveArtifacts artifacts: 'target/*.jar'
    }

    stage('Deploy') {
        bat '''
            docker build -t pipeline-demo .
            docker rm -f pipeline-demo || exit 0
            docker run -d -p 8080:8080 --name pipeline-demo pipeline-demo
        '''
    }
}
