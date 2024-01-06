node('maven_otus') {
    timestamps {
        wrap([$class: 'BuildUser']) {
            currentBuild.description = "User: ${env.BUILD_USER}"
        }

        stage("Hello stage") {
            echo "Hello from stage"
            echo "Branches: ${env.BRANCHES}"
        }

    }
}