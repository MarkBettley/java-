FROM eclipse-temurin:11-jdk AS build

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean installDist --no-daemon

FROM eclipse-temurin:11-jre

WORKDIR /app

COPY --from=build /app/build/install/biblioteca-java/ ./

CMD ["bin/biblioteca-java"]
