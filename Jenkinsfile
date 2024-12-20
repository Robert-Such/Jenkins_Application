pipeline {
    agent any
    tools {
            maven 'Maven_3.9.9' // Use the name of the Maven installation configured in Jenkins
        }
    stages {
        stage('Checkout Repositories') {
            steps {
                script {
                    // Clone LibraryRepo from GitHub
                    dir('Jenkins_Library') {
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: 'main']],
                            userRemoteConfigs: [[url: 'https://github.com/Robert-Such/Jenkins_Library.git']]
                        ])
                    }

                    // Clone ApplicationRepo (automatically handled by Jenkins when it processes the Jenkinsfile)
                    dir('Jenkins_Application') {
                        checkout scm
                    }
                }
            }
        }

        stage('Build Jenkins_Library') {
            steps {
                dir('Jenkins_Library') {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Build Jenkins_Application') {
            steps {
                dir('Jenkins_Application') {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Run Application') {
            steps {
                dir('Jenkins_Application') {
                    // Run the application with the library dependency in the classpath
                    sh '''
                    java -cp target/application-1.0-SNAPSHOT.jar:/config/workspace/MULTI_REPO_TEST/Jenkins_Library/target/library-1.0-SNAPSHOT.jar \
                    org.example.application.Main
                    '''
                }
            }
        }
    }
}

