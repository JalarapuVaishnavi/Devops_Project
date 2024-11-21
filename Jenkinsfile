pipeline {
    agent any

    tools {
        // Configure the tools like Git, Maven, etc.
        git 'git'
    }

    environment {
        // Define any environment variables here
        // Example: MY_VAR = 'value'
    }

    stages {
        stage('Checkout SCM') {
            steps {
                // Checkout the latest code from Git
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Run build commands, e.g., using Maven, Gradle, etc.
                echo 'Building project...'
                sh './build.sh' // Replace with your actual build command
            }
        }

        stage('Test') {
            steps {
                // Run unit tests or other tests
                echo 'Running tests...'
                sh './run_tests.sh' // Replace with your test script
            }
        }

        stage('Deploy') {
            steps {
                // Deploy or deliver the project
                echo 'Deploying application...'
                sh './deploy.sh' // Replace with your deployment script
            }
        }

        stage('Cleanup') {
            steps {
                // Clean up temporary files if necessary
                echo 'Cleaning up...'
                sh 'rm -rf build/' // Example cleanup command
            }
        }
    }

    post {
        always {
            // Always run this block after pipeline completion
            echo 'Pipeline finished.'
        }
        success {
            // Actions to take on successful completion
            echo 'Build and tests were successful.'
        }
        failure {
            // Actions to take if the pipeline fails
            echo 'Build or tests failed.'
        }
    }
}
