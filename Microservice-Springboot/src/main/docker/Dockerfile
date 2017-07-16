FROM maven:3.5-jdk-8
EXPOSE 8080

RUN mkdir /usr/src/microservice \
    && cd /usr/src/microservice \
    && wget "https://drive.google.com/uc?id=0B2BVGDhe2Wm2T09YOUVGMkJvS3c&export=download" -O "/usr/src/microservice/Microservice.tar.gz" \
    && tar -xzvf /usr/src/microservice/Microservice.tar.gz

CMD ["--port 8080"]

ENTRYPOINT cd /usr/src/microservice/Microservice && mvn spring-boot:run
