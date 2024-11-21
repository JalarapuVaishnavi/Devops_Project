pipeline {
    agent any

    environment {
        // Set the workspace directory for the project (adjust if needed)
        SRC_DIR = 'src'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your repository
                checkout scm
            }
        }

        stage('List Workspace Contents') {
            steps {
                // List all files to check the directory structure
                bat 'dir /s /b'
            }
        }

        stage('Compile Java') {
            steps {
                script {
                    // Compile the Java code from the correct src directory
                    echo "Compiling Java code..."
                    bat """
                    cd ${SRC_DIR}
                    javac student/StudentGradeManagementSystem.java
                    if %errorlevel% neq 0 exit /b %errorlevel%
                    """
                }
            }
        }

        stage('Run Application') {
            steps {
                script {
                    // Run the application from the src directory
                    echo "Running the Java application..."
                    bat """
                    cd ${SRC_DIR}
                    java -cp . student.StudentGradeManagementSystem
                    """
                }
            }
        }

        stage('Save Students') {
            steps {
                script {
                    // If saving functionality is implemented, trigger that part
                    echo "Triggering save function..."
                    bat """
                    cd ${SRC_DIR}
                    java -cp . student.StudentGradeManagementSystem
                    """
                }
            }
        }

        stage('Post-Build') {
            steps {
                // Clean up or other post-build actions if needed
                echo "Build completed successfully."
            }
        }
    }
    
    post {
        always {
            // Actions that always happen after the pipeline, regardless of success or failure
            echo 'Pipeline execution finished!'
        }

        success {
            // Actions to run only if the build is successful
            echo 'Build succeeded!'
        }

        failure {
            // Actions to run only if the build fails
            echo 'Build failed!'
        }
    }
}
