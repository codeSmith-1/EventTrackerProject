# EventTrackerProject

This personal travel log application allows me to track and document trips. I can add locations, dates, notes, and photos for specific trips. Users can also upload comments for visits and log multiple visits to the same place. The program allows users to update and delete data.

## Technology Used

The program is written in Java and JavaScript and utilizes Spring Boot, REST Services, and MySQL to deliver a full CRUD database experience to the front end.

## Rest Endpoints

| CRUD Op. | HTTP Verb | URI                   		| Request Body           | Response Body                   	|
|----------|-----------|----------------------------|------------------------|----------------------------------|
| Read     | GET       | `/api/locations`          	|                    	 | List of all locations        	|
| Create   | POST      | `/api/locations`  			| JSON for new location  | JSON of created location			|
|------------------------------------------------------------------------------------------------------------	|
| Read     | GET       | `/api/locations/{id}/visits`|                       | List of all visits	    		|
| Update   | PUT       | `/api/visits/{id}`			 | JSON to update visit  | JSON of updated visit    		|
| Delete   | DELETE    | `/api/visits/{id}`			 | JSON to delete visit  | 204 (no content) 				|	
|---------------------------------------------------------------------------------------------------------------|