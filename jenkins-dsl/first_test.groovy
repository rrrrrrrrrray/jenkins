def projects = [
    "git_job" : [
        "URL": "https://github.com/rrrrrrrrrray/vms.git"
    ]
]

def git_jks_job = "https://github.com/rrrrrrrrrray/vms.git"
def git_jks_conf = "https://github.com/rrrrrrrrrray/nconf.git"

pipelineJob("dsl/job-just-test") {
        environmentVariables {
            env('GIT_URL_JOB', "https://github.com/rrrrrrrrrray/vms.git")
            env('GIT_URL_CONF', "https://github.com/rrrrrrrrrray/nconf.git")
        }

        logRotator {
            numToKeep(3)
        }

        parameters {
            gitParameter {
                name('branch')
                type('PT_BRANCH_TAG')
                defaultValue('main')
                description('')
                branch('')
                branchFilter('upstream/(.*)')
                tagFilter('*')
                sortMode('NONE')
                selectedValue('NONE')
                useRepository(git_jks_job)
                quickFilterEnabled(true)
                listSize('8')
                requiredParameter(false)
            }
        }

        definition {
            cpsScm {
                scm {
                  git {
                    remote {
                        name('origin')
                        url(git_jks_conf)
                    }
                    remote {
                        name('upstream')
                        url(git_jks_job)
                    }
                    branch('master')
                  }
                }

                scriptPath("nconf/Jenkinsfile")

            }
        }
    }
