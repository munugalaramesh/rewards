Swagger API
============

  http://localhost:8080/rewards/swagger-ui/index.html
  
H2 DB Console 
==============

http://localhost:8080/rewards/h2
	jdbc:h2:mem:rewards
	rwmanagement
	rwmanagement
	
H2 DB Configuration 
==================
I have added the ddl-auto as create-drop, so all the data will be removed on app restart

Services 

==========

Added 4 services to to manage customers and rewards.

GET - /Customer - To get All the customers with Monthly Rewards and Yearly

GET -/Customer/{name} - To get individual user rewards

POST -/Customer - To create new customer into the system

POST - /Customer - To create transactions for a customer.	

	

