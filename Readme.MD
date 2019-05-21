
## atfarm - Field Condition Statistics 

##### Requirement / Instructions :

We would like you to build a restful API that provides field conditions insights. Your API must be able to handle two use cases: 

	1. Record incoming field condition measurements 
	2. Provide field condition statistics from the last 30 days


## Technology Used

	1. Java 8
	2. Spring boot 2.x
	3. Spring WebFlux
	4. MapStruct 1.3.0
	5. JPA
	6. H2 Database

	
## How to Run and Build


To Build the application, just execute:

```mvn clean install```

To run the application, just execute:

```mvn spring-boot:run```

Please use **http://localhost:8080/fcs/swagger-ui.html**  or you can use any web service testing tools to test the same by using below APIs.

	http://localhost:8080/fcs/field-condition 	-X POST
	http://localhost:8080/fcs/field-statistics 	-X GET



## Design Pattern

* Builder design Pattern


### Builder design Pattern

I decided to use **_Builder design Pattern_** to store vegetation data and retrieve the same from repository since its providing a more flexible way to separate the object creation, functionality from service layer and scalable.

### Auto Mapper with mapstruct

MapStruct is a code generator that greatly simplifies the implementation of mappings between Java bean types based on a convention over configuration approach.The generated mapping code uses plain method invocations and thus is fast, type-safe and easy to understand.

 
## RESTful API

* /field-condition  -X HTTP POST (To store the field condition data into repository)
* /field-statistics        -X HTTP GET (To retrieve 30 days field condition statistics data into repository)	
## Way to Test API

Once the server is started, we can test this API in **_three ways_**.

##### Swagger
	
		This project completely integrated with Swagger API's to test API in a more convenient and easy way. To launch swagger and test, Please use this below URL in any browser.
	
        	http://localhost:8080/fcs/swagger-ui.html
		
##### Curl
```		
1. curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
   "occurrenceAt": "2019-05-21T10:16:29.545Z", \ 
   "vegetation": 0 \ 
 }' 'http://localhost:8080/fcs/field-condition'

2. curl -X GET --header 'Accept: application/json' 'http://localhost:8080/fcs/field-statistics' 
```		
		
##### Webservice Testing Tool
	http://localhost:8080/fcs/field-condition 	-X POST
	http://localhost:8080/fcs/field-statistics 	-X GET
		
## Request JSON schema
```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "occurrenceAt": {
      "type": "date"
    },
    "vegetation": {
      "type": "double"
    }
  },
  "required": [
    "occurrenceAt",
    "vegetation"
  ]
}
```
## Response JSON Schema
```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "code": {
      "type": "string"
    },
    "message": {
      "type": "string"
    },
    "vegetation": {
      "type": "object",
      "properties": {
        "avg": {
          "type": "double"
        },
        "max": {
          "type": "double"
        },
        "min": {
          "type": "double"
        }
      },
      "required": [
        "avg",
        "max",
        "min"
      ]
    }
  },
  "required": [
    "code",
    "message",
    "vegetation"
  ]
}
```
### Constrain

Its recommended to compile and start the application through terminal. if you are going to run through IDE, we need to follow necessary steps to annotation picks up and applies the annotation processor settings automatically for **mapstruct**.

Please find the below link to IDE support of  mapstrct to process annotation based auto generated code,

[IDE Support](http://mapstruct.org/documentation/ide-support/ "mapstruct IDE support")
