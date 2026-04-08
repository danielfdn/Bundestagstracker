# Build Stage
FROM eclipse-temurin:21-jdk-jammy

# Copy entrypoint script
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Copy JAR
ADD target/*.jar app.jar

ENTRYPOINT ["/entrypoint.sh"]
