pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                script {
                    // Start Docker Compose in detached mode
                    dockerCompose.up '-d'
                    sh 'docker ps'
                }
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
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
