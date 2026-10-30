pipeline {
  agent any
  tools { maven 'Maven3' }
  triggers { pollSCM('H/2 * * * *') }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Build')    { steps { sh 'mvn clean compile' } }
    stage('Test')     { steps { sh 'mvn test' } }
    stage('Package')  { steps { sh 'mvn package -DskipTests' } }
  }
  post {
    always  { junit 'target/surefire-reports/*.xml' }
    success { archiveArtifacts artifacts: 'target/*.jar', fingerprint: true }
  }
}