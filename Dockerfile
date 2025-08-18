# syntax=docker/dockerfile:experimental
FROM amazoncorretto:22-alpine-jdk AS builder-base
LABEL authors="cloud"
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean install -DskipTests package spring-boot:repackage
RUN mkdir -p bin && (cd bin; jar -xf ../target/*.jar)
RUN rm -rf target

FROM amazoncorretto:22-alpine-jdk
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/bin
COPY --from=builder-base ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder-base ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder-base ${DEPENDENCY}/BOOT-INF/classes /app
RUN rm -rf bin

ENV PORT 8080
EXPOSE 8082

ENTRYPOINT ["java","-cp","app:app/lib/*","com.sudocode.arrow.Application"]