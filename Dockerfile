FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/made-merits.jar /made-merits/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/made-merits/app.jar"]
