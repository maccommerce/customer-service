FROM anapsix/alpine-java
ADD target/single-registry-service.jar single-registry-service.jar
ENTRYPOINT ["java","-jar","single-registry-service.jar"]
