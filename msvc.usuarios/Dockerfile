FROM openjdk:24-ea-33-jdk-slim-bookworm as builder

WORKDIR /app/msvc-usuarios

COPY ./pom.xml /app

COPY ./msvc.usuarios/.mvn  /app/msvc-usuarios/.mvn
COPY ./msvc.usuarios/pom.xml  /app/msvc-usuarios/pom.xml
COPY ./msvc.usuarios/mvnw  /app/msvc-usuarios/mvnw

RUN ./mvnw  clean package -Dmaven.test.skip=true -Dmaven.main.skip=true -Dspring-boot.repackage.skip=true && rm -r ./target/

COPY ./msvc.usuarios/src ./src

RUN ./mvnw  clean package -DskipTests

FROM openjdk:24-ea-33-jdk-slim-bookworm

WORKDIR /app

COPY --from=builder /app/msvc-usuarios/target/msvc.usuarios-0.0.1-SNAPSHOT.jar .
EXPOSE 8001

ENTRYPOINT ["java", "-jar", "msvc.usuarios-0.0.1-SNAPSHOT.jar"]
