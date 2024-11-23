pipeline {
    agent any

    stages {
        stage('Build stage') {
            steps {
                echo 'Building project…'
                // Execute Maven clean and package command
                //sh 'mvn clean package'
            } 
        }

        stage('Test stage') {
            steps {
                echo 'Testing project…'
                // Execute Maven test command
               // sh 'mvn test'
            }
        }

        stage('Deploy stage') {
            steps {
                echo 'Deploying project…'
                // Execute deployment script
                //sh './deploy.sh'
            }
        }
    }
}
