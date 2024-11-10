pipeline {
    agent any
    environment {
        dockerCompose = 'docker-compose'
    }
    stages {
        stage('Build') {
            steps {
                script {
                    sh "${dockerCompose} up -d"
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh "${dockerCompose} run app mvn test"
                }
            }
        }
    }
    post {
        always {
            script {
                // Stop Docker Compose services
                sh "${dockerCompose} down"
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
