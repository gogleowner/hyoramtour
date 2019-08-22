FROM openjdk:8
WORKDIR /
ADD build/libs/hyoram-0.0.1-SNAPSHOT.jar hyoramtour.jar
EXPOSE 8080
CMD java -Xms64M -Xmx64M -jar hyoramtour.jar

