A repository for Java Software Engineering Bootcamp assignments (12 Feb - 20 Mar, 2022) https://kbtgclassnest.skooldio.com/
## Writeups for assignments
Please refer to the assignment in the link below
* [Week 1 :: Design and Develop RESTful API with Spring Boot](https://github.com/paphopsaw/assignment-java-boot-camp/wiki/Week-01)

## Setup and run
Java 11
```
./mvnw clean
./mvnw compile
./mvnw test
./mvnw package
./mvnw spring-boot:run
```
Sample requests using curl
```
curl http://localhost:8080/api/user/1
curl http://localhost:8080/api/products
curl http://localhost:8080/api/products?search=iPhone
curl http://localhost:8080/api/product/1
curl -X POST http://localhost:8080/api/user/1/cart
   -H 'Content-Type: application/json'
   -d '{
          "productId": 3,
          "quantity": 5
      }'
curl -X POST http://localhost:8080/api/user/1/checkout
   -H 'Content-Type: application/json'
   -d '{
          "paymentMethod": "Credit card",
          "creditCardNo": "1234123412341234",
          "creditCardName": "Paphop Sawasdee",
          "creditCardMonth": 2,
          "creditCardYear": 2026,
          "cvc": "123"
      }'
```
