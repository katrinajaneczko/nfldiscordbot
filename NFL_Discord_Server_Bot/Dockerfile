# Specify Java version
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Pass env variable(s)
ARG BOT_TOKEN
ENV BOT_TOKEN=${BOT_TOKEN}

# Copy the JAR file from the local file system to the container
COPY target/NFL_Discord_Server_Bot-jar-with-dependencies.jar /app/NFL_Discord_Server_Bot-jar-with-dependencies.jar

# Copy the help.txt file from the local file system to the container
COPY src/main/resources/help.txt /app/NFL_Discord_Server_Bot/src/main/resources/help.txt

# Run the JAR file
CMD ["java", "-jar", "NFL_Discord_Server_Bot-jar-with-dependencies.jar"]
