# Cheapest Transfer Route Application

A logistics company is optimizing its package delivery network. Packages
need to be transferred between multiple cities, and each transfer has an
associated cost. Your task is to help the company find the **best
combination of transfers** while ensuring that the total package weight
stays within a given limit.

## Requirements

### Inputs
- A list of available transfers, where each transfer has:
  - **weight** (in kg): The weight the transfer can handle.
  - **cost** (in currency): The fee for that transfer.
- A maximum weight limit (**maxWeight**): The total weight that can
  be transferred in one route.

### Objective
- Find the combination of transfers that maximizes the total
  cost while ensuring the total weight of the transfers is less
  than or equal to **maxWeight**.

### Output
- A list of transfers (weights and costs) selected for the
route.
- The total cost of the selected transfers.

## Build And Run the Application Using Maven
**1. Compile and Package the Application:**

Run the following Maven command to compile the code and package the application into a JAR file:

```bash
mvn clean package
```

**2. Run the Application:**

Run Spring Boot application with:
```bash
java -jar target/cheapest-transfer-route-app-0.0.1-SNAPSHOT.jar
```
This will start the application and expose the API on http://localhost:8080.

## Example CURL requests and responses for the REST API

**CURL Request:**
```bash
curl -X POST http://localhost:8080/api/transfers/optimal \
-H "Content-Type: application/json" \
-d '{
  "maxWeight": 15,
  "availableTransfers": [
    {"weight": 5, "cost": 10},
    {"weight": 10, "cost": 20},
    {"weight": 3, "cost": 5},
    {"weight": 8, "cost": 15}
  ]
}'
```

**Response:**
```json
{
  "selectedTransfers" : [ {
    "weight" : 10,
    "cost" : 20
  }, {
    "weight" : 5,
    "cost" : 10
  } ],
  "totalCost" : 30,
  "totalWeight" : 15,
  "boxedTransfers" : [ ]
}
```



**CURL Request:**
```bash
curl -X POST http://localhost:8080/api/transfers/optimal \
-H "Content-Type: application/json" \
-d '{
  "maxWeight": 15,
  "availableTransfers": [
  ]
}'
```

**Response:**
```json
{
  "selectedTransfers" : [ ],
  "totalCost" : 0,
  "totalWeight" : 0,
  "boxedTransfers" : [ ]

}
```

**CURL Request:**
```bash
curl -X POST http://localhost:8080/api/transfers/optimal \
-H "Content-Type: application/json" \
-d '{
  "maxWeight": 2,
  "availableTransfers": [
    {"weight": 5, "cost": 10},
    {"weight": 10, "cost": 20},
    {"weight": 3, "cost": 5},
    {"weight": 8, "cost": 15}
  ]
}'
```

**Response:**
```json
{
  "selectedTransfers" : [ ],
  "totalCost" : 0,
  "totalWeight" : 0,
  "boxedTransfers" : [ ]

}
```


### **Invalid API endpoint**

```bash
 curl -X POST http://localhost:8080/api/transfers/optiml \ 
-H "Content-Type: application/json" \
-d '{
  "maxWeight": 2,
  }'

```

**Response**
```json
{
  "timestamp" : "2025-01-30T17:19:57.083+00:00",
  "status" : 404,
  "error" : "Not Found",
  "path" : "/api/transfers/optiml"
}
```

### **Invalid Request Body**
```bash
 curl -X POST http://localhost:8080/api/transfers/optimal \
-H "Content-Type: application/json" \
-d '{
  "maxWeight": 2,
  }'                     
```
**Response**
```json
{
  "timestamp" : "2025-01-30T17:18:20.095+00:00",
  "status" : 400,
  "error" : "Bad Request",
  "path" : "/api/transfers/optimal"
}
```

## Transfers using boxes which have their maxWeight
**Request**
```bash
curl -X POST http://localhost:8080/api/transfers/boxes \
-H "Content-Type: application/json" \
-d '{
  "selectedTransfers" : [ ],
  "maxWeight": 45,
  "maxBoxWeight": 20,
  "availableTransfers": [
    {
      "weight": 15,
      "cost": 15
    },
    {
      "weight": 10,
      "cost": 10
    },
    {
      "weight": 9,
      "cost": 9
    },
    {
      "weight": 5,
      "cost": 5
    },
    {
      "weight": 4,
      "cost": 4
    },
    {
      "weight": 3,
      "cost": 3
    }
  ]
}'
```

**Response**
```json
{
  "totalWeight": 43,
  "totalCost": 43,
  "boxes": [
    [
      {
        "weight": 15,
        "cost": 15
      },
      {
        "weight": 5,
        "cost": 5
      }
    ],
    [
      {
        "weight": 10,
        "cost": 10
      },
      {
        "weight": 9,
        "cost": 9
      }
    ],
    [
      {
        "weight": 4,
        "cost": 4
      }
    ]
   ]
}
```

**Request**
```bash
curl -X POST http://localhost:8080/api/transfers/boxes \
-H "Content-Type: application/json" \
-d '{
  "maxWeight": 25,
  "maxBoxWeight": 10,
  "availableTransfers": [
    {
      "weight": 6,
      "cost": 6
    },
    {
      "weight": 6,
      "cost": 6
    },
    {
      "weight": 6,
      "cost": 6
    },
    {
      "weight": 6,
      "cost": 6
    },
    {
      "weight": 4,
      "cost": 4
    }
  ]
}'
```

**Response**
```json
{
  "selectedTransfers" : [ ],
  "totalCost" : 16,
  "totalWeight" : 16,
  "boxedTransfers" : [ 
    [ 
      {
        "weight" : 4,
        "cost" : 4
      }, 
      {
      "weight" : 6,
      "cost" : 6
      } 
    ], 
    [ 
      {
      "weight" : 6,
      "cost" : 6
      } 
    ], 
    [
    ] 
  ]
}
```

## Run Tests

**Run unit and integration tests using **
```bash
mvn test
```