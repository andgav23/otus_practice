pipeline {
    agent any
    stages {
        stage('build job-a') {
            steps {
//                build job: 'job-a', parameters: [[$class: 'StringParameterValue', name: 'BRANCH_ONE', value: "two"]]
                   script {
                       for (branch in env.BRANCHES) {
                           echo "Branch for running: ${branch}"
                       }
                   }

            }
        }

    }
}