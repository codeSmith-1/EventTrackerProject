# EventTrackerProject

This event tracker allows me to upload photos from worldwide travel and display them in a carousel. I can add, update, and delete photos using the modal represented by the "+" button and by clicking directly on the image. 

##Technology Used

The program is written in Java and utilizes Spring Boot, REST Services, MySQL, and ANgular. A RestController relays information to @Services that implement JPARepository capabilities and database CRUD functions.
Angular implements an HttpClient to talk to the Rest Controller's API end-points (below). The carousel, modal, and buttons are constructed with ngBootstrap. 

## Rest Endpoints

| CRUD Op. | HTTP Verb | URI                   		| Request Body           | Response Body                   	|
|----------|-----------|---------------------------	|------------------------|----------------------------------|
| Read     | GET       | `/api/locations`           |                        | List of all locations        	|
| Create   | POST      | `/api/locations`  			| JSON for new location  | JSON of created location			|
| Update   | PUT       | `/api/locations/{id}` 		| JSON to update location| JSON of updated location  	    |
| Delete   | DELETE    | `/api/locations/{id}`		| 						 | 204 (no content) 				|	
|---------------------------------------------------------------------------------------------------------------|
