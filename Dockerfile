# Usar una imagen base de Java
FROM openjdk:17-jdk-slim

# Crear un directorio para la aplicación
WORKDIR /app

# Copiar el archivo JAR generado por Spring Boot
COPY target/bkedinventario-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8862
EXPOSE 8862

# Variables de entorno para la base de datos


# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]