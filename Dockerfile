FROM anapsix/alpine-java
MAINTAINER Eduardo Filho <filhoeduardo83@gmail.com>
ADD target/single-registry-service.jar single-registry-service.jar
ENTRYPOINT ["java","-jar","single-registry-service.jar"]