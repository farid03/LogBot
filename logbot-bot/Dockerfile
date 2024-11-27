FROM amazoncorretto:17
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} logbot-bot.jar
ENTRYPOINT ["java","-jar","/logbot-bot.jar"]