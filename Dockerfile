FROM openjdk
WORKDIR /usr/src
COPY ./target/classes /usr/src
RUN ls -l /usr/src/com/mycompany/app
CMD ["java", "com.mycompany.app.App"]
