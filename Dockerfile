FROM openjdk:8
EXPOSE 8090
ADD target/spring-sns.jar spring-sns.jar
ENTRYPOINT ["java","-jar","/spring-sns.jar"]