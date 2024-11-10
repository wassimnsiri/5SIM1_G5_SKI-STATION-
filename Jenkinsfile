pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your/repo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }
        stage('Docker Build and Push') {
            steps {
                sh 'docker build -t rihemrd/DrissiRihem_G5_GESTION-STATION-SKI .'
                sh 'docker push rihemrd/DrissiRihem_G5_GESTION-STATION-SKI'
            }
        }
    }
}
