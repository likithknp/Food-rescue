# Food Waste Sharing Backend

## Tech Stack
- Java 17
- Spring Boot
- MySQL
- Spring Data JPA
- Maven

## Run Project

1. Create MySQL database:
   food_rescue_db

2. Update database username/password in:
   src/main/resources/application.properties

3. Run:
   mvn spring-boot:run

## APIs

### Create Donation
POST /api/donations

### Get Donations
GET /api/donations
