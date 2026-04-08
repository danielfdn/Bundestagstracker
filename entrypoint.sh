#!/bin/bash

# Read .env file and convert to system properties
set -a
source /app/.env 2>/dev/null
set +a

# Build Java options from environment variables
JAVA_OPTS="-Dspring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}"
JAVA_OPTS="${JAVA_OPTS} -Dspring.datasource.username=${DB_USERNAME}"
JAVA_OPTS="${JAVA_OPTS} -Dspring.datasource.password=${DB_PASSWORD}"

echo "Starting app with JDBC URL: jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}"

exec java ${JAVA_OPTS} -jar /app.jar "$@"

