FROM maven:3.5-jdk-8
EXPOSE 8086

RUN mkdir /usr/src/microservice \
    && cd /usr/src/microservice \
    && wget "URL to download" -O "/usr/src/microservice/Springboot-MVC-HtmlAnalyzer.zip" \
    && unzip /usr/src/microservice/Springboot-MVC-HtmlAnalyzer.zip 
    
CMD ["--port 8086"]

ENTRYPOINT cd /usr/src/microservice/Springboot-MVC-HtmlAnalyzer && mvn spring-boot:run
