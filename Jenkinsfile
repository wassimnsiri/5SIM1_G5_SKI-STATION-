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
                dockerCompose.up '-d'
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                sh  'mvn package'
            }
        }

    }

    post {
        always {
            dockerCompose.down()
        }
        success {
            echo 'Build and Deploy succeeded!'
        }
        failure {
            echo 'Build or Deploy failed!'
        }
    }
}