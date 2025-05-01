# Etapa 1: Construcción del JAR
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia los archivos necesarios para construir
COPY pom.xml .
COPY src ./src

# Construir el proyecto sin tests
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera para producción
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar el JAR desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto de la app
EXPOSE 8086

# Ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
