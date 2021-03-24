FROM maven:3.6.0-jdk-8-alpine

#copying src of my framework
COPY src /home/AssignmentAPI/src

#copying pom.xml of my framework
COPY pom.xml /home/AssignmentAPI

#copyin testng.xml of your framework
COPY testng.xml /home/AssignmentAPI

RUN mvn -f /home/AssignmentAPI/pom.xml clean test -DskipTests=true