# EventTrackerProject

This is the backend of a travel log application. Users can add information related to their travels including location, dates, notes, photos. Users can also upload comments for visits and log multiple visits to the same place. The program allows users to update and delete data.

## Technology Used

The program is written in Java and utilizes Spring Boot, REST Services, and MySQL to deliver a full CRUD database experience to the a front end application.

## Rest Endpoints

| CRUD Op. | HTTP Verb | URI                   	| Request Body           | Response Body                   	|
|----------|-----------|------------------------	|------------------------|----------------------------------	|
| Read     | GET       | `/api/locations`           	|                        | List of all locations           	|
| Read     | GET       | `/api/locations/{id}`   	    |                        | View single location            	|
| Create   | POST      | `/api/locations`  			| JSON for new location  | JSON of created location			|
| Update   | PUT       | `/api/locations/{id}` 			| JSON to update location| JSON of updated location        	|
| Delete   | DELETE    | `/api/locations/{id}`			| JSON to delete location| 204 (no content) 					|
| Read     | GET       | `/api/locations/{id}/visits`   	|                        | List of all visits	           	|
| Create   | POST      | `/api/locations/{id}/visits`		| JSON for new visit     | JSON of created visit			    |
| Update   | PUT       | `/api/locations/{id}/visits/{id}`	| JSON to update visit   | JSON of updated visit        	    |
| Delete   | DELETE    | `/api/visits/{id}`			| JSON to delete visit   | 204 (no content) 					|
|------------------------------------------------------------------------------------------------------------	|
