FROM jenkins/jenkins:lts

USER root
RUN apt-get update && \
	apt-get install -y kubernetes-client docker.io && \
	apt-get clean
RUN jenkins-plugin-cli --plugins docker-workflow
RUN usermod -aG docker jenkins

WORKDIR /var/jenkins_home
COPY Jenkinsfile .

RUN chown -R jenkins:jenkins /var/jenkins_home
 
USER jenkins
