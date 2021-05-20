FROM openjdk:11.0
ADD target/e-commerce-docker.jar e-commerce-docker.jar
ENTRYPOINT ["java", "-jar", "/e-commerce-docker.jar"]