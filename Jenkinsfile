pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                git url: 'https://github.com/JalarapuVaishnavi/Devops_Project.git'
            }
        }
        stage('Compile Java') {
            steps {
                bat '''
                cd src
                javac student/StudentGradeManagementSystem.java
                '''
            }
        }
        stage('Run Application') {
            steps {
                bat '''
                java student.StudentGradeManagementSystem
                '''
            }
        }
    }
}
