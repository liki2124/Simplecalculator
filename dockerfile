FROM maven:3.6.3-jdk-11 as maven
COPY ./ ./docker-maven
WORKDIR /docker-maven 
RUN mvn install
FROM openjdk:11
WORKDIR /maven-app
COPY --from=maven /dockermaven/target/*.jar ./Simplecalculator.jar 
ENTRYPOINT ["java","-jar","./Simplecalculator.jar"]
