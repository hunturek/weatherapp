pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'develop', url: 'https://github.com/hunturek/weatherapp.git'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    	def app = docker.build("hunturek/weather-app", "weather-app/.")
			sh 'docker tag hunturek/weather-app localhost:5000/weather-app'
                    	sh 'docker push localhost:5000/weather-app'
                }
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
