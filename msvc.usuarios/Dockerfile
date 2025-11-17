ARG MSVC_NAME=msvc.usuarios

FROM openjdk:24-ea-33-jdk-slim-bookworm as builder
ARG MSVC_NAME

WORKDIR /app/${MSVC_NAME}

COPY ./pom.xml /app

COPY ./${MSVC_NAME}/.mvn  /app/${MSVC_NAME}/.mvn
COPY ./${MSVC_NAME}/pom.xml  /app/${MSVC_NAME}/pom.xml
COPY ./${MSVC_NAME}/mvnw  /app/${MSVC_NAME}/mvnw

RUN ./mvnw  clean package -Dmaven.test.skip=true -Dmaven.main.skip=true -Dspring-boot.repackage.skip=true && rm -r ./target/


COPY ./${MSVC_NAME}/src ./src

RUN ./mvnw  clean package -DskipTests

FROM openjdk:24-ea-33-jdk-slim-bookworm

ARG MSVC_NAME

WORKDIR /app

RUN mkdir ./logs

COPY --from=builder /app/${MSVC_NAME}/target/msvc.usuarios-0.0.1-SNAPSHOT.jar .

ENV PORT 8001

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "msvc.usuarios-0.0.1-SNAPSHOT.jar"]
