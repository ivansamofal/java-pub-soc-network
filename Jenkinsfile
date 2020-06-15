pipeline {
   agent any

   stages {
      stage('Build') {

         steps {
          script {
              DATE_TAG = java.time.LocalDate.now()
              DATETIME_TAG = java.time.LocalDateTime.now()
              DATETIME_TAG = java.util.Calendar.getInstance().getTimeInMillis();
            }
            sh "mkdir ${DATETIME_TAG}"
            // Run Maven on a Unix agent.
            sh "cd ${DATETIME_TAG} && git clone --single-branch -b JVMC-2 https://gitlab.com/ioansamofal/java_mvc . && mkdir testsfg"

            sh "echo 'BUILD_NUMBER' :: $BUILD_NUMBER"

            sh "echo 'BRANCH_NAME' :: ${env.BRANCH_NAME}"
            sh "echo 'TAG_NAME' :: ${env.TAG_NAME}"
            sh "echo 'TAG_UNIXTIME' :: ${env.TAG_UNIXTIME}"
            sh "echo 'BRANCH_NAME' :: ${env.BRANCH_NAME}"
            sh "echo 'NODE_NAME' :: ${env.NODE_NAME}"
            sh "echo 'NODE_LABELS' :: ${env.NODE_LABELS}"
            sh "echo 'JENKINS_HOME' :: ${env.JENKINS_HOME}"
            sh "echo 'GIT_COMMIT' :: ${env.GIT_COMMIT}"
            sh "echo 'GIT_BRANCH' :: ${env.GIT_BRANCH}"
            sh "echo 'NODE_NAME' :: ${env.NODE_NAME}"
            sh "touch sometestfile.txt"
         }
      }
   }
}