#!/bin/bash

if [ -z "${SPRING_PROFILES_ACTIVE}" ]
then
   export SPRING_PROFILES_ACTIVE=dev
fi
echo "SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}"

export POSEITECH_SERVICE_NAME=poseitechActivtyDailysign
echo "POSEITECH_SERVICE_NAME=${POSEITECH_SERVICE_NAME}"

deploy() {
   stop
   
   # test does pid file exist? if not, move jar from jenkins workspace to ${POSEITECH_SERVICE_APP_HOME}/bin/
   if [ -f "$PIDFILE" ]
   then
    
      echo "Can not stop ${POSEITECH_SERVICE_NAME}, ignore updated."
      
   else
      
      echo "move ${JENKINS_WORKSPACE}/target/${POSEITECH_SERVICE_JAR} to ${POSEITECH_SERVICE_APP_HOME}/bin"
   
      sudo cp -ru $JENKINS_WORKSPACE/target/$POSEITECH_SERVICE_JAR $POSEITECH_SERVICE_APP_HOME/bin/$POSEITECH_SERVICE_JAR
      ls -al $POSEITECH_SERVICE_APP_HOME/bin/$POSEITECH_SERVICE_JAR
      echo "${POSEITECH_SERVICE_JAR} updated"
   
      start
   fi
}

stop() {
   echo "stopping ${POSEITECH_SERVICE_NAME}"
   sudo service $POSEITECH_SERVICE_NAME stop
}

start() {
   echo "starting ${POSEITECH_SERVICE_NAME}"
   sudo service $POSEITECH_SERVICE_NAME start   
}

if [ "${SPRING_PROFILES_ACTIVE}" == "dev" ]
then

   # poseitech cloud config server uri and credentials
	export CLOUD_CONFIG_URI=http://192.168.8.100:8888/config
	export CLOUD_CONFIG_USER=user
	export CLOUD_CONFIG_PASSWORD=poseitech
	export APP_LOG_FOLDER=./target/logs
		
	#mvn clean spring-boot:run
   mvn clean package

elif [ "${SPRING_PROFILES_ACTIVE}" == "cit" ]
then
   
   JENKINS_HOME=/storage/services/jenkins
   echo "JENKINS_HOME=${JENKINS_HOME}"
   
   JENKINS_WORKSPACE=`echo ${JENKINS_HOME}/${POSEITECH_SERVICE_NAME}-SNAPSHOT`

   export JENKINS_WORKSPACE=${JENKINS_WORKSPACE}
   echo "JENKINS_WORKSPACE=${JENKINS_WORKSPACE}"

   export POSEITECH_JAVA_APP_HOME=/storage/services/poseitech/java
   echo "POSEITECH_JAVA_APP_HOME=${POSEITECH_JAVA_APP_HOME}"

   export POSEITECH_SERVICE_APP_HOME=${POSEITECH_JAVA_APP_HOME}/${POSEITECH_SERVICE_NAME}
   echo "POSEITECH_SERVICE_APP_HOME=${POSEITECH_SERVICE_APP_HOME}"
   
   POSEITECH_SERVICE_JAR=`echo ${POSEITECH_SERVICE_NAME}.jar`
   export POSEITECH_SERVICE_JAR=${POSEITECH_SERVICE_JAR}
   echo "POSEITECH_SERVICE_JAR=${POSEITECH_SERVICE_JAR}"
   
   PIDFILE=`echo ${POSEITECH_SERVICE_APP_HOME}/${POSEITECH_SERVICE_NAME}.pid`
   export PIDFILE
   echo "PIDFILE=${PIDFILE}"
 
   echo "deploy...."
   deploy
   
else
   
   echo "SPRING_PROFILES_ACTIVE not set."
   exit 1
fi
