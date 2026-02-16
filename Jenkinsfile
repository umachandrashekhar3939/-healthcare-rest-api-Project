pipeline {
    agent any

    environment {
        IMAGE_NAME = "umachandrashekhar3393/healthcare-api"
        TAG = "v1.0"
        DOCKER_CREDENTIALS = "dockerhub-credentials"
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/umachandrashekhar3393/healthcare-rest-api-Project.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${TAG} ."
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "dockerhub-1234",
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS')]) {

                    sh "echo $PASS | docker login -u $USER --password-stdin"
                }
            }
        }

        stage('Push Image') {
            steps {
                sh "docker push ${IMAGE_NAME}:${TAG}"
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

