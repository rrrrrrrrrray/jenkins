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
                branchFilter('origin1/(.*)')
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
                    gitSCM {
                      userRemoteConfigs {
                        userRemoteConfig {
                          url(git_jks_conf)
                          // credentialsId()
                          name('origin')
                          refspec('+refs/heads/*:refs/remotes/jenkins-ci/*')
                          browser {
                            gitLab {
                              repoUrl("https://github.com/")
                              version("15.3")
                            }
                          }
                          gitTool("/opt/homebrew/bin/git")
                        }
                        
                        userRemoteConfig {
                          url(git_jks_job)
                          // credentialsId()
                          name('origin1')
                          refspec('+refs/heads/*:refs/remotes/sms-gateway/*')
                          browser {
                            gitLab {
                              repoUrl("https://github.com/")
                              version("15.3")
                            }
                          }
                          gitTool("/opt/homebrew/bin/git")
                        }
                      }
                      branches {
                        branchSpec {
                          name("main")
                          }
                        }
                    }
                }

                scriptPath("JenkinsFiles/none-prod-golang-jenkinsfile")

            }
        }
    }