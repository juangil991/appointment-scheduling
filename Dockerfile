# ===========================================
# Stage 1 - Build
# ===========================================
FROM eclipse-temurin:25-jdk AS builder

WORKDIR /app

# Copiar archivos de Gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle* settings.gradle* ./

# Descargar dependencias
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon || true

# Copiar el código fuente
COPY src src

# Compilar la aplicación
RUN ./gradlew clean bootJar --no-daemon

# ===========================================
# Stage 2 - Runtime
# ===========================================
FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]