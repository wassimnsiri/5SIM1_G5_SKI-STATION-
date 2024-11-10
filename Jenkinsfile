pipeline {
    agent any

    stages {
        stage('Build') {
          steps {
            script {
              dockerCompose.up('--build')
            }
          }
        }
        stage('Test') {
          steps {
            script {
              dockerCompose.run('app', 'mvn test')
            }
          }
        }
      }

    post {
        always {
            script {
                // Stop Docker Compose services
                dockerCompose.down()
            }
        }
        success {
            echo 'Build and Deploy succeeded!'
        }
        failure {
            echo 'Build or Deploy failed!'
        }
    }
}
