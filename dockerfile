FROM openjdk:11
COPY /target/hello.jar hello.jar
#COPY test.p12 test.p12
CMD ["java","-jar","hello.jar"]