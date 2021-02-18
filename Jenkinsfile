pipeline {
    agent any

   tools {
        maven 'maven'
        jdk 'jdk11'
    }
    stages {
         stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
                sh 'pwd'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn -Dtest="web/*Test" test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
        post {
            always {
                junit 'build/reports/**/*.xml'
            }
        }
    }
}
