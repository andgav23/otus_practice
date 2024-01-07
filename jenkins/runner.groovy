pipeline {
    agent any
    stages {
        stage('build job-a') {

//                build job: 'job-a', parameters: [[$class: 'StringParameterValue', name: 'BRANCH_ONE', value: "two"]]
                for (branch in env.BRANCHES) {
                    echo "Branch for running: ${branch}"

            }
        }

    }
}