FROM openjdk:11-jdk
EXPOSE 9090
ARG JAR_FILE=target/employee-managment-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} employee-managment-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/employee-managment-0.0.1-SNAPSHOT.jar"]