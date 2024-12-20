# Use Maven with a compatible JDK version
FROM maven:3.9.3-eclipse-temurin-17

# Set working directory inside the container
WORKDIR /app

# Copy the commons project files into the container
COPY . .

# Default command to execute Maven deploy
CMD ["sh", "-c", "mvn clean deploy -P release -s /app/settings.xml "]