FROM openjdk:24-ea-33-jdk-slim-bookworm as builder

WORKDIR /app/msvc-cursos

COPY ./pom.xml /app

COPY ./msvc.cursos/.mvn  /app/msvc-cursos/.mvn
COPY ./msvc.cursos/pom.xml  /app/msvc-cursos/pom.xml
COPY ./msvc.cursos/mvnw  /app/msvc-cursos/mvnw

RUN ./mvnw  clean package -Dmaven.test.skip=true -Dmaven.main.skip=true -Dspring-boot.repackage.skip=true && rm -r ./target/

COPY ./msvc.cursos/src ./src

RUN ./mvnw  clean package -DskipTests

FROM openjdk:24-ea-33-jdk-slim-bookworm

WORKDIR /app/

COPY --from=builder /app/msvc-cursos/target/msvc.cursos-0.0.1-SNAPSHOT.jar .
EXPOSE 8001

ENTRYPOINT ["java", "-jar", "msvc.cursos-0.0.1-SNAPSHOT.jar"]
