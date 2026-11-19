
pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven-3.9.14'
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out PeopleSphere source code'
                checkout scm
            }
        }

        stage('Build Employee Service') {
            steps {
                dir('employee-service') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Build Attendance Service') {
            steps {
                dir('attendance-service') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Build Payroll Service') {
            steps {
                dir('payroll-service') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Build Notification Service') {
            steps {
                dir('notification-service') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Build API Gateway') {
            steps {
                dir('api-gateway') {
                    bat 'mvn clean package'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar',
                                 fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'PeopleSphere CI pipeline completed successfully!'
        }

        failure {
            echo 'PeopleSphere CI pipeline failed. Check the stage logs.'
        }
    }
}
