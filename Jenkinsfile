pipeline {
    environment {
        VERSION  = "1.1"
        OTHERS = "abc"
    }

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
                sh 'docker build --no-cache --force-rm -t jenkins/app:$VERSION ./'
                sh 'oc login -u admin -p redhat https://master.example.com:8443' 
                sh 'docker login -u $(oc whoami) -p $(oc whoami -t) 192.168.56.127:30074' 
                sh 'oc project jenkins'
                sh 'oc delete dc/app$VERSION || echo "no dc should be deleted"'
                sh 'docker tag jenkins/app:$VERSION 192.168.56.127:30074/jenkins/app:$VERSION'
                sh 'docker push 192.168.56.127:30074/jenkins/app:$VERSION'
                sh 'oc new-app -i jenkins/app$VERSION'

            }
        }
    }
}
