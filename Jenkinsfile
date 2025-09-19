pipeline {
    agent any

    environment {
        ANDROID_HOME = "/Users/zulfikarsuweleh/Library/Android/sdk" // Adjust SDK path
        PATH = "${env.ANDROID_HOME}/tools:${env.ANDROID_HOME}/platform-tools:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checkout to master branch..."
                git branch: 'master',
                    url: 'https://github.com/maszulfikarsuweleh/settle-in-japan-mobile.git'
            }
        }

        stage('Build APK') {
            steps {
                echo "Starting APK build..."
                sh './gradlew clean assembleDebug --stacktrace --info'
                echo "APK build completed!"
            }
//                 steps {
//                 sh './gradlew clean assembleDebug' // Builds debug APK
//                 // For release APK:
//                 // sh './gradlew clean assembleRelease'
//                 // For AAB:
//                 // sh './gradlew clean bundleRelease'
//             }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'app/build/outputs/apk/**/*.apk', fingerprint: true
                // For AAB:
                // archiveArtifacts artifacts: 'app/build/outputs/**/*.aab', allowEmptyArchive: false
            }
        }
    }

    post {
        success {
            echo 'Build and archiving successful!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
