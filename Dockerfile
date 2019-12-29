FROM openjdk:8-jre
MAINTAINER Eduardo Filho <filhoeduardo83@gmail.com>
ADD target/single-registry-service.jar single-registry-service.jar
EXPOSE 7000
ENTRYPOINT ["java","-jar","single-registry-service.jar"]
