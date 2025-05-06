FROM openjdk:17-oracle
VOLUME /tmp
COPY target/*.jar ecommerce-backend.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/ecommerce-backend.jar"]
