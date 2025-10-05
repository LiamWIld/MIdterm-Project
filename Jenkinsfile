stage('Build & Test') {
	steps {
		sh '''
      set -eux
      # show perms so we can see what's going on
      ls -la

      # ensure mvnw is executable (Windows clones often drop the +x bit)
      [ -x mvnw ] || chmod +x mvnw

      # normalize Windows CRLF line endings if present (prevents sh^M errors)
      sed -i 's/\r$//' mvnw

      # run build with wrapper
      ./mvnw -q -B -V clean test package
    '''
  }
  post {
		always {
			junit '**/target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
  }
}
