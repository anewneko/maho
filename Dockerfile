FROM alpine:3
RUN apk add --no-cache openjdk17
WORKDIR /app
COPY ./target/maho_bot-1.0.0.jar /app/myapp.jar
ENV active=prod
EXPOSE 8080
CMD ["java", "-jar", "myapp.jar"]