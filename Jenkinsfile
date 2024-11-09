pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/wassimnsiri/5SIM1_G5_SKI-STATION-.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
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
        success {
            echo 'Build and Deploy succeeded!'
        }
        failure {
            echo 'Build or Deploy failed!'
        }
    }
}