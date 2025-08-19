pipeline {
    agent any

    stages {
        stage('Check Maven') {
            steps {
                // Display Maven version to ensure it is installed
                bat 'mvn -v'
            }
        }
        stage('Build') {
            steps {
                // Compile and package the project with Maven
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('List Workspace') {
            steps {
                // List workspace content to check if target is generated
                bat 'dir'
            }
        }
        stage('Test') {
            steps {
                // Run unit tests
                bat 'mvn test'
            }
        }
        stage('Archive') {
            steps {
                // Archive the generated JAR
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}
