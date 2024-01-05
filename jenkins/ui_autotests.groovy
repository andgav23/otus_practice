import groovy.io.*

def listfiles(dir) {
    dlist = []
    flist = []
    new File(dir).eachDir {dlist << it.name }
    dlist.sort()
    new File(dir).eachFile(FileType.FILES, {flist << it.name })
    flist.sort()
    return (dlist << flist).flatten()
}




node('maven_otus') {
    timestamps {
        wrap([$class: 'BuildUser']) {
            currentBuild.description = "User: ${env.BUILD_USER}"
        }
        params = readYaml text: env.YAML_CONFIG ?: '{}'
        if (params !=null) {
//            for (key in params.ketSet()) {
//                value = params[key]
//                env.setProperty(key, value)
//            }
            for (entry in params) {
                env.setProperty(entry.key, entry.value)
            }
        }
                stage("tests stage") {
                    fs = listfiles(".")
                    fs.each {
                        println it
                    sh "mvn test -Dbrowser=${env.BROWSER_NAME}"
        }
//        stage("Checkout") {
//            scm checkout
//        }
//        stage("Running UI tests") {
//            if (env.BROWSER_NAME == "") {
//                echo "BROWSER_NAME parameter is required"
//                exit(1)
//            }
//            // sh "mvn test -Dbrowser=${env.BROWSER_NAME}"
//            status = sh(script: "mvn test -Dbrowser=${env.BROWSER_NAME}",
//                    returnStatus: true)
//            if (status == 1) {
//                currentBuild.status = "UNSTABLE"
//            }
//        }
//        stage("Allure report") {
//            allure(
//                    results: ['$WORKSPACE/target/allure-results'],
//                    disabled: false,
//                    reportBuildPolicy: 'ALWAYS'
//            )
//        }
    }
}}