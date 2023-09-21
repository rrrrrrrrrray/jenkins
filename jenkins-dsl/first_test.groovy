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
                useRepository($GIT_URL_JOB)
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
                          url($GIT_URL_JOB)
                          credentialsId()
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
                          url($GIT_URL_JOB)
                          credentialsId()
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