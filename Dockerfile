FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.6_10_openj9-0.18.1-alpine-slim as build

WORKDIR /app
COPY . ./

RUN ./gradlew build shadowJar

FROM adoptopenjdk/openjdk11-openj9:jre-11.0.6_10_openj9-0.18.1-alpine as release

WORKDIR /app

COPY build/libs/sources-*-all.jar sources.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-XX:+IdleTuningGcOnIdle", "-Xtune:virtualized", "-jar", "sources.jar"]
