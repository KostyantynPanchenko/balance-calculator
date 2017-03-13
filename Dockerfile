FROM openjdk:8-jdk
COPY /api/BalanceCalculator.yml /data/BalanceCalculator/BalanceCalculator.yml  
COPY /api/target/api-0.0.1-SNAPSHOT.jar /data/BalanceCalculator/api-0.0.1-SNAPSHOT.jar
WORKDIR /data/BalanceCalculator
RUN java -version
CMD ["java","-jar","api-0.0.1-SNAPSHOT.jar","server","BalanceCalculator.yml"]
EXPOSE 9000-9001