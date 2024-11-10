pipeline {
    agent any
    environment {
        dockerComposeFile = 'docker-compose.yml' // Path to your Docker Compose file
    }
    stages {
        stage('Build') {
            steps {
                script {
                    // Start the services using Docker Compose
                    dockerCompose up -d
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    // Run tests using Docker Compose
                    dockerCompose run app mvn test
                }
            }
        }
    }
    post {
        always {
            script {
                // Stop and remove the services using Docker Compose
                dockerCompose down
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
