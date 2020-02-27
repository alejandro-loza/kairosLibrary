def project = 'kairos-financiero-portal'
def appName = 'kairosAPi'
def branchMap = [develop: 'qa', master: 'prod']
def projectMap = [develop: 'kairos-devops-management', master: 'kairos-financiero-portal']
def imageTag = "gcr.io/${projectMap[env.BRANCH_NAME]}/${appName}:${branchMap[env.BRANCH_NAME]?:env.BRANCH_NAME}.${env.BUILD_NUMBER}"
def isBranchDeployable = branchMap[env.BRANCH_NAME]

pipeline {
  agent {
    kubernetes {
      label "kairosAPi"
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: java8
    image: openjdk:8-jdk
    command:
    - cat
    tty: true
  - name: gcloud
    image: kairosfinanciero/gcloud-docker-node:2
    volumeMounts:
    - mountPath: /var/run/docker.sock
      name: docker-socket-volume
    securityContext:
      privileged: true
    command:
    - cat
    tty: true
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
  volumes:
  - name: docker-socket-volume
    hostPath:
      path: /var/run/docker.sock
      type: File
"""
}
  }
  stages {
    stage('Test & Sonar anaylsis') {
      steps {
        withSonarQubeEnv('sonarqube-tools-k8s') {
          container('java8') {
            sh """
              ./gradlew clean build
              ./gradlew sonarqube -Dsonar.host.url=https://sonar.tools.kairosfinanciero.app -Dsonar.login=6270f566692472922c606d6293f79a0fd30a2a32 -Dsonar.projectKey=${appName} -Dsonar.exclusions=**/KairosAPi*
            """
          }
        }
        script {
          sh "sleep 1"
          def qg = waitForQualityGate()
          if (qg.status != 'OK') {
            error "Pipeline aborted due to quality gate failure: ${qg.status}"
          }
        }
      }
    }
	
	stage('Build and push image with Container Builder') {
     when {
       expression {
        return isBranchDeployable
       }
     }
     steps {
       container('gcloud') {
         sh """
           docker build -t ${imageTag} .
           gcloud docker -- push ${imageTag}
          """
        }
      }
   }

   stage("Deployment") {
     when {
       expression {
        return isBranchDeployable
       }
     }
     steps {
       println "Deploying to ${branchMap[env.BRANCH_NAME]}"
       container('kubectl') {
         sh("sed -i.bak 's#gcr.io/${projectMap[env.BRANCH_NAME]}/${appName}#${imageTag}#' ./k8s/${branchMap[env.BRANCH_NAME]}.yml")
         sh("kubectl apply -f k8s/${branchMap[env.BRANCH_NAME]}.yml")
       }
     }
   }

  }

  post {
    failure {
      script {
        currentBuild.result = 'FAILURE'
      }
    }

    always {
      step([$class: 'Mailer',
        notifyEveryUnstableBuild: true,
        recipients: "tecnologia@kairosfinanciero.com",
        sendToIndividuals: true])
    }
  }

}
