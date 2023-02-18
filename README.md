# **Customer Reward Management**
## **Project Overview**
In this project, we are going to create a RESTful API using Spring Boot to calculate reward points earned by a customer during a three-month period. The retailer offers a rewards program to its customers, awarding points based on each recorded purchase. A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction. We will create an endpoint that takes a record of every transaction during a three-month period and returns the reward points earned for each customer per month and total.

### **Technologies Used**
* Spring Boot
* Spring Data JPA
* MySQL 
* Maven


### **API Endpoints**
POST /transactions
This endpoint accepts a list of transactions in JSON format and saves them to the database. Each transaction should contain the following fields.

```json
[
  {
    "customer_id": 1,
    "transaction_date": "2022-01-01",
    "amount": 150
  },
  {
    "customer_id": 1,
    "transaction_date": "2022-01-10",
    "amount": 50
  },
  {
    "customer_id": 1,
    "transaction_date": "2022-02-05",
    "amount": 250
  },
  {
    "customer_id": 2,
    "transaction_date": "2022-01-15",
    "amount": 120
  },
  {
    "customer_id": 2,
    "transaction_date": "2022-02-20",
    "amount": 80
  },
  {
    "customer_id": 3,
    "transaction_date": "2022-01-25",
    "amount": 70
  },
  {
    "customer_id": 3,
    "transaction_date": "2022-02-28",
    "amount": 130
  },
  {
    "customer_id": 3,
    "transaction_date": "2022-03-05",
    "amount": 200
  }
]
```

```api
GET /rewards?customerId={customerId}
```
This endpoint returns the reward points earned for a particular customer during the last three months. The response includes the reward points earned for each month and the total reward points earned. The endpoint accepts a query parameter customerId which specifies the customer for which rewards need to be calculated.

### **Example*
```api
Request: GET /rewards?customerId=1
```

### **Example Response:**

```json
{
  "customerId": 1,
  "monthlyRewards": {
    "January": 100,
    "February": 250,
    "March": 0
 }
 }
 ```
 
 ### **Link to the File in GITHUB **
[https://github.com/heman1402/Customer-Reward-System]
