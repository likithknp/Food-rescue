FROM maven:3.9.9-eclipse-temurin-24

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java","-jar","target/food-waste-sharing-backend-1.0.0.jar"]