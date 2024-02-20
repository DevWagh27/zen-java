pipeline {
    agent any
    environment {
        MAVEN_HOME="/opt/maven"
        M2_HOME="/opt/maven"
        PATH="${M2_HOME}/bin:${PATH}"
    }

    triggers {
        gitlab(triggerOnPush: true, triggerOnMergeRequest: true, branchFilterType: 'All')
    }
    stages {
        stage('Package and Deploy Account-Info Service') { 
            steps {
                echo "Running build and deploying to Jboss Server. Account Info Service"
                sh 'mvn clean install'
            }
        }
    }
}