FROM jenkins/jenkins:lts

USER root
RUN apt-get update && \
	apt-get install -y maven openjdk-17-jdk kubernetes-client && \
	apt-get clean
RUN jenkins-plugin-cli --plugins docker-workflow

WORKDIR /var/jenkins_home

USER jenkins