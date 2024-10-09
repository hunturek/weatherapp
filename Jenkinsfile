pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'develop', url: 'https://github.com/hunturek/weatherapp.git'
            }
        }

        stage('Build') {
            steps {
                dir('weather-app') {
					sh 'mvn clean package'
				}
            }
        }

        stage('Test') {
            steps {
                dir('weather-app') {
					sh 'mvn test'
				}
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t hunturek/weather-app .'
            }
        }

        stage('Save Docker Image') {
            steps {
		sh 'docker tag weather-app localhost:5000/weather-app'
                sh 'docker save -o weather-app.tar localhost:5000/weather-app'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/deployment.yaml'
                sh 'kubectl apply -f k8s/service.yaml'
		sh 'kubectl apply -f k8s/ingress.yaml'
            }
        }
    }
}
