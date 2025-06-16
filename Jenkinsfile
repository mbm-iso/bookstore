pipeline {

    agent {
        label 'docker-jdk21'
    }

    environment {
        NEXUS_URL = 'https://host.docker.internal:8081/repository/maven-releases/'
        NEXUS_CREDENTIALS_ID = 'nexus-creds' // Jenkins credential ID
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile -DskipTests'
            }
        }

        stage('Build & Unit Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Integration Test') {
            steps {
                sh 'mvn verify -Pintegration-tests'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "${NEXUS_CREDENTIALS_ID}",
                    usernameVariable: 'NEXUS_USER',
                    passwordVariable: 'NEXUS_PASS'
                )]) {
                    sh """
                      mvn deploy -DskipTests \
                        -Dnexus.url=${NEXUS_URL} \
                        -Dnexus.username=${NEXUS_USER} \
                        -Dnexus.password=${NEXUS_PASS}
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
