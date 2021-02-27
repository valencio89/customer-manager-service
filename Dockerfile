FROM openjdk:8-jdk-alpine
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/hsbc/customer-manager-service-1.0.0.jar"]
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/hsbc/customer-manager-service-1.0.0.jar
EXPOSE 8080
