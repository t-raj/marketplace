# README #



### IDE ###

Eclipse IDE

### Team Members ###

* Bo Lin

* Tara Ray

* Hongbin Sun


### Framework ###

* Maven 
* Hibernate
* Apache CXF

### Database ###
* Postgres on Heroku

### Architecture ###

* Database-DAO

* Domain-Entity and Beans

* Service-Activity(Service), Resource(Endpoint) and Representation(Model)

### Tools ###
* Tomcat for Service layer

### Introduction ###

The Lakeshore Market API is a RESTful API. 


* Overall: A customer can search product and make an order. The product is provided by providers, namely partner, in our context. When the customer wants to buy certain product(s), an order will be created. And product and order together forms orderLine. 


* The endpoint package contains all the resources for a client through model classes. The specific explanations are as followed: 


* Responses: Responses will be in XML format.


**Partners**


*Partner is the provider of certain product.* 


* The PartnerEndpoint class servers as a Resource in the Service Layer. The path of this server was defined as “/partner”. There is a POST method acting as Insert Partner. The Consumes type for the server is xml in our example. Inside the payload, the partner information was added manually, such as Id, login, password, firstName and lastName etc. The data was verified in our database and they are consistent. 


* Attributes: 

* * Id  Int: Unique Id for the partner
* * login String: login name for the partner
* * password String: Short description of the product
* * firstName String: first name of the partner
* * lastName String: last name of the partner
* * streetAddress String: the street address where the partner lives
* * city String: the city where the partner lives
* * state String: the state where the partner lives
* * zip_cod String: the zip code for the partner

* Example:
* * Method: create a partner
* * Arguments: 
* * PartnerModel Required: a partnerModel is required to create a partner, all the attributes will be in the payload when necessary







**Products**

*Each product is identified by a unique ID and is linked to a partner.*


* Attributes: 


* * pId   Int: Unique Id for the product 
* * price  Double: Cost of the product
* * description String: Short description of the product
* * numberAvailable  Int: Number of items of the product that are currently available
* * partnerId  Int: Unique Id for the Partner who supplies this product


* Example:

* * Methods:
* * 
* * Search a product
* * 
* * Search the database for a product based on it’s ID.


ARGUMENTS
pId
Required




Add a product


Add a new product to the database.


Update a product


Update an existing product in the database.


Orders


An order refers to the transaction placed by the customer for products. Each order can have one customer and multiple products. The root path for order is /order.


Attributes: 


Order_id
String: Unique Id for the order 
List<ProductIds>
List<Integer>: List of product ids on this order
Customer_id
String: Unique Id for the customer who places this order


Example:


Methods


Create an order


Create a new order.
 
ARGUMENTS
order_id
Required
customer_id
Required
List<product_id>
Required


POST /order


Request Example:


Response Example: 




Check order status


Checks the status of an order. 


ARGUMENTS
order_id
Optional. If no order id is provided, the status for all orders in progress will be returned. 


GET /order/{order_id}


Request Example:


Response Example: 


Push an order to partner


Pushes an order to the partner for shipping. 


ARGUMENTS
order_id
Optional. If no order id is provided, all orders ready for pushing to partner will be pushed.


PUT /order/{order_id}


Request Example:


Response Example: 




Ship an order


Ships an order. 


ARGUMENTS
order_id
Optional. If no order id is provided, all orders ready for shipping will be shipped.


PUT /order/{order_id}


Request Example:


Response Example: 




Cancel order


Cancels an order. 


ARGUMENTS
order_id
Required. The order specified by the order id will be updated to status “Canceled”.


PUT /order/{order_id}


Request Example:


Response Example: 

### Java Version - Java 1.8 jdk ###