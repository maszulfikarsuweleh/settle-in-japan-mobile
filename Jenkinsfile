pipeline {
    agent any

    tools {
        jdk 'jdk17'  // Make sure this matches your installed JDK in Jenkins
        gradle 'gradle' // Make sure you have a Gradle installation configured in Jenkins
    }

    environment {
        ANDROID_HOME = "/Users/zulfikarsuweleh/Library/Android/sdk" // Adjust if different
        PATH = "${env.ANDROID_HOME}/tools:${env.ANDROID_HOME}/platform-tools:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/maszulfikarsuweleh/settle-in-japan-mobile.git'
            }
        }

        stage('Build APK') {
            steps {
                // For debug APK
                sh './gradlew assembleDebug'

                // If you want release APK instead, uncomment:
                // sh './gradlew assembleRelease'

                // For bundle (AAB), uncomment:
                // sh './gradlew bundleRelease'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/app/build/outputs/**/*.apk', allowEmptyArchive: false
                // If using AAB:
                // archiveArtifacts artifacts: '**/app/build/outputs/**/*.aab', allowEmptyArchive: false
            }
        }
    }

    post {
        success {
            echo 'Build completed successfully!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
