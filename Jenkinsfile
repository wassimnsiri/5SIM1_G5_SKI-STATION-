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
                sh 'docker build -t yourdockerhubname/nomprenom_groupe_nomProjet .'
                sh 'docker push yourdockerhubname/nomprenom_groupe_nomProjet'
            }
        }
    }
}
