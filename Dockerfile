FROM anapsix/alpine-java
ADD target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar
EXPOSE 7000
ENTRYPOINT ["java","-jar","customer-service.jar"]
