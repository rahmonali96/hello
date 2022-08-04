FROM openjdk:11
COPY /target/hello.jar hello.jar
CMD ["java","-jar","hello.jar"]