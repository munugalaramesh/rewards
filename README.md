

Requirement : 

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.



Solution :
============
  1. Run the application as Spring Boot Application / Deploy in Tomcat.
  2. Application will be running on 8080, Access Swagger API using http://localhost:8080/rewards/swagger-ui/index.html
  3. Application will be started with DB schema with empty Data.
  4. Lets create the Customer by accessing POST -/Customer API using Swagger
  5. Add transactions to the created Customer using POST - /Customer/transactions
     5.1 ) Allowed to add both customername, amount and the date of the transaction (To support different years)
  6. Get All Customers with Monthly Rewards along with Yearly total.   


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

POST - /Customer/transactions - To create transactions for a customer.
