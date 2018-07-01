# README #



### IDE ###

Eclipse

### Team Members ###

* Bo Lin

* Tara Raj

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

The Lakeshore Market API is a RESTful API for and online marketplace, allowing customers to search for and order products and track their orders, and vendor partners to manage inventory and orders.

The endpoint package provides resources for a client via model classes. These models can be created and updated through the client (sample source at https://github.com/t-raj/marketplace-consumer.) The specific endpoints are detailed below. Responses will be in XML.


**Partners**


*A partner is the provider of certain product. The PartnerEndpoint class serves as a Resource in the Service Layer. The path of this server was defined as “/partner”. There is a POST method acting as Insert Partner. The root path to the partner endpoint is “/partner”.*


Attributes: 

* Id  Int: Unique Id for the partner
* login String: login name for the partner
* password String: Short description of the product
* firstName String: first name of the partner
* lastName String: last name of the partner
* streetAddress String: the street address where the partner lives
* city String: the city where the partner lives
* state String: the state where the partner lives
* zip_code String: the zip code for the partner


**create a partner**

*Add a new partner to the database.*

POST /partner

ARGUMENTS

*PartnerModel: Required. This model will contain the above attributes recieved from the client. These attributes will be validated by type.



**Products**


*Each product is identified by a unique ID and is linked to a partner. The root path to the product endpoint is “/product”.*


Attributes: 

* pId   Int: Unique Id for the product 
* price  Double: Cost of the product
* description String: Short description of the product
* numberAvailable  Int: Number of items of the product that are currently available
* partnerId  Int: Unique Id for the Partner who supplies this product


**Search a product**

*Search the database for a product based on it’s ID.*

GET /product/{pId}

ARGUMENTS

* pId: Required


**Add a product**

*Add a new product to the database.*

POST /product

ARGUMENTS

*ProductModel: Required. This model will contain the above attributes recieved from the client. These attributes will be validated by type.


**Update a product**

*Update an existing product in the database.*

PUT /product/{pId}

ARGUMENTS

*ProductModel: Required

**Orders**

*An order refers to a transaction placed by a customer for a set of products. Each order can have one customer and multiple products. The root path to the order endpoint is /order.*


Attributes: 

* Order_id    String: Unique ID for the order 
* List<ProductIds>    List<Integer>: List of product ID's on this order
* Customer_id String: Unique ID for the customer who places this order


**Create an order**

*Add a new order to the database.*

POST /order

ARGUMENTS

* order_id: Required
* customer_id: Required
* List<product_id> :Required


**Check order status**

*Return the status of an order.*

GET /order/{order_id}

ARGUMENTS

* order_id: Optional. If no order id is provided, the status for all orders in progress will be returned. 


**Push an order to partner**

*Push an order to the partner for shipping.*

PUT /order/{order_id}

ARGUMENTS

* order_id: Optional. If no order id is provided, all orders ready for pushing to partner will be pushed.


**Ship an order**

*Set an order's status to shipped.*

PUT /order/{order_id}

ARGUMENTS
* order_id: Optional. If no order id is provided, all orders ready for shipping will be shipped.


**Cancel order**

*Set an order's status to cancelled.*

PUT /order/{order_id}

ARGUMENTS
* order_id: Required.



### Java Version - Java 1.8 jdk ###