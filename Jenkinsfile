node {

    stage('Checkout') {
        checkout scm
    }

    stage('Build & Test') {
        bat 'mvnw.cmd clean test package'
    }

    stage('Deploy') {

        bat 'docker build -t pipeline-demo .'

        bat 'docker rm -f pipeline-demo || exit 0'

        bat 'docker run -d -p 9091:8080 --name pipeline-demo pipeline-demo'
    }
}
