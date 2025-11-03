FROM openjdk:24

WORKDIR /app

COPY ./target/msvc.usuarios-0.0.1-SNAPSHOT.jar /app/msvc.usuarios.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "msvc.usuarios.jar"]
