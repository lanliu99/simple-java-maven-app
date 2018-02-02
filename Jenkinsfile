pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') { 
            steps {
                sh 'docker build --no-cache --force-rm -t jenkins/app:1.0 ./'
                sh 'oc login -u admin -p redhat https://master.example.com:8443' 
                sh 'docker login -u $(oc whoami) -p $(oc whoami -t) 192.168.56.127:30074' 
                sh 'oc project jenkins && oc delete dc/app'
                sh 'docker tag jenkins/app:1.0 192.168.56.127:30074/jenkins/app:1.0'
                sh 'docker push 192.168.56.127:30074/jenkins/app:1.0'
                sh 'oc new-app -i jenkins/app:1.0'

            }
        }
    }
}
