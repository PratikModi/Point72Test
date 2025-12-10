Invert Service — REST API Assignment
====================================

A simple Spring Boot REST service that inverts each word of a sentence.
Example:
"abc def" → "cba fed"

The service stores each request/response pair in an H2 in-memory database and provides APIs to:
	•	Invert a sentence
	•	Search request/response history by word
	•	Get full processing history

Swagger UI is included for easy testing.

Tech Stack
----------
	•	Java 17  
	•	Spring Boot 3.3.x  
	•	Spring Web  
	•	Spring Data JPA  
	•	H2 Database  
	•	Spring Validation (Jakarta)  
	•	Lombok  
	•	SpringDoc OpenAPI (Swagger UI)  

Prerequisites
------------

You must have the following installed:  
	•	Java 17  
	•	Maven 3.8+  

  Check installation:  
  sh java -version  
  sh mvn -version

How to Run the Application
--------------------------

1. Clone the Repository  
sh git clone https://github.com/PratikModi/Point72Test  
cd invert-service  

2. Build the Project
sh mvn clean package

3. Run the Application
USING MAVEN
sh mvn spring-boot:run
OR
java -jar target/invert-service-0.0.1-SNAPSHOT.jar

Application will be available at:  
http://localhost:8080

API Documentation (Swagger UI)  
http://localhost:8080/swagger-ui.html

Available Endpoints
-------------------
  1. Invert Sentence  
     POST /api/v1/invert  

     Request Body:  
     {  
        "sentence": "hello world"  
     }  

     Response:  
     {  
        "id": 1,  
        "requestText": "hello world",  
        "responseText": "olleh dlrow",  
        "createdAt": "2025-02-10T10:20:30"  
     }  

 2. Search by Word  
    GET /api/v1/invert/search?word=hello  
    Returns all entries where the word appears in request or response.  

 3. Get Processing History  
    GET /api/v1/invert/history  
    Returns all saved request/response records.  

Project Structure  
-----------------  
src/main/java/com/point72/invertservice/  
 │  
├── controller/              → REST endpoints  
├── model/                   → Entities & DTOs  
├── repository/              → Spring Data JPA repos  
├── service/                 → Business logic  
└── InvertServiceApplication.java  
