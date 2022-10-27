FROM adoptopenjdk/openjdk11-openj9:alpine-slim
VOLUME /tmp
ARG DEPENDENCY=/target/dependency
COPY ${DEPENDENCY}/lib /app/lib
COPY ${DEPENDENCY}/resources /app
COPY /target/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.pvh.ocrapi.ReviewApplication"]
