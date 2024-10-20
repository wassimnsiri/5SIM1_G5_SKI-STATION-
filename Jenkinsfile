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
                    // Start MySQL container without a root password
                    def mysqlContainer = docker.image('mysql:5.7')
                    mysqlContainer.run('-e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=stationSki -p 3306:3306')
                    sleep(15) // Allow time for MySQL to start
                }
                // Run tests
                sh 'mvn test'
            }
            post {
                always {
                    // Stop the MySQL container
                    sh 'docker stop $(docker ps -q --filter ancestor=mysql:5.7)'
                }
            }
        }
    }
}
