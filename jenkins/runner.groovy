pipeline {
    agent any
    stages {
        stage('build job-a') {
            steps {
//                build job: 'job-a', parameters: [[$class: 'StringParameterValue', name: 'BRANCH_ONE', value: "two"]]
                   script {
                       String[] branchList = "${env.BRANCHES}".split(',')
                       for (branch in branchList) {
                           echo "Branch for running: ${branch}"
                       }
                   }

            }
        }

    }
}