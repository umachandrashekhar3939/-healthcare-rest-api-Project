pipeline {
    agent any

    environment {
        IMAGE_NAME = "umachandrashekhar3393/healthcare-api"
        TAG = "v1.0"
        DOCKER_CREDENTIALS = "dockerhub-1234"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/umachandrashekhar3393/healthcare-rest-api-Project.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${TAG} ."
            }
        }

        stage('Login & Push to DockerHub') {
            steps {
                script {
                    withCredentials([usernamePassword(
                        credentialsId: DOCKER_CREDENTIALS,
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS')]) {

                        sh """
                        echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin
                        docker push ${IMAGE_NAME}:${TAG}
                        docker logout
                        """
                    }
                }
            }
        }

        stage('Remove Local Image') {
            steps {
                sh "docker rmi ${IMAGE_NAME}:${TAG} || true"
            }
        }

        stage('Deploy to Worker Node') {
            steps {
                sh "ansible-playbook -i inventory deploy.yml"
            }
        }
    }
}


