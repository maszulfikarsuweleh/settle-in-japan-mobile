pipeline {
    agent any

    environment {
        JAVA_HOME = tool(name: 'jdk17')  // Ensure you defined jdk17 in Jenkins global tools
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    tools {
        // Make sure you have Gradle configured in Jenkins or rely on ./gradlew
        jdk 'jdk17'  // Adjust if your KMP project needs another JDK
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/maszulfikarsuweleh/settle-in-japan-mobile.git',
                    credentialsId: 'your-github-token'
            }
        }

        stage('Prepare') {
            steps {
                sh 'chmod +x ./gradlew'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
    }

    post {
        success {
            echo "✅ Build successful!"
        }
        failure {
            echo "❌ Build failed!"
        }
    }
}
