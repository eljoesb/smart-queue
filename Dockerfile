FROM quay.io/quarkus/ubi-quarkus-graalvm-native-builder:22.3-java17 AS build
COPY --chown=quarkus:quarkus . /code
USER quarkus
WORKDIR /code
RUN ./mvnw package -Pnative

FROM registry.access.redhat.com/ubi8/openjdk-17:1.18
ENV LANGUAGE='en_US:en'

COPY --from=build /code/target/*-runner /application

EXPOSE 8080
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]