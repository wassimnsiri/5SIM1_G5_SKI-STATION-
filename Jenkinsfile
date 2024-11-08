pipeline {
    agent any
    environment {
        MYSQL_HOST = '127.0.0.1'
        MYSQL_PORT = '3306'
        MYSQL_DB = 'stationSki'
        MYSQL_USER = 'root'
        MYSQL_PASSWORD = ''  // Use your MySQL password if needed
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                script {
                    // Check MySQL connection
                    retry(5) {
                        sleep(5) // Wait a bit for MySQL if needed
                        sh """
                        mysql -h${MYSQL_HOST} -P${MYSQL_PORT} -u${MYSQL_USER} -p${MYSQL_PASSWORD} -e "USE ${MYSQL_DB};"
                        """
                    }
                }
                sh 'mvn test'
            }
        }
    }
}
