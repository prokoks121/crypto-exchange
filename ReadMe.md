# Crypto Exchange service

## How to run the project

After you clone the project, you need to build the project:

````
mvn clena install
````

Then you need to create a Docker image:

````
docker build -t crypto .  
````

Then you need to run Docker image:

````
dockr run -dp 8080:8080 crypto
````

When you have successfully started the project on docker, all endpoints will be exposed on port `8080`

## Endpoints

### Add new Order

Path: `/order`</br>
Request method: `POST`</br>
Request body:

````json
{
  "id": 1,
  "currencyPair": "BTCUSD",
  "type": "BUY",
  "price": 20150.00,
  "quantity": 0.3
}
 ````

Response status code:

- `201`
- `400`

Response body:

````json
{
  "id": 1,
  "createdDateTime": "2022-09-28T14:44:04.92656",
  "currencyPair": "BTCUSD",
  "type": "BUY",
  "price": 20150.00,
  "quantity": 0.3,
  "filledQuantity": 0.3,
  "status": "CLOSED",
  "trades": [
    {
      "id": 10,
      "buyOrderId": 1,
      "sellOrderId": 5,
      "timestamp": 1662638820000,
      "price": 20150.00,
      "quantity": 0.2
    },
    {
      "id": 11,
      "buyOrderID": 1,
      "sellOrderID": 6,
      "timestamp": 1662638820000,
      "price": 20150.00,
      "quantity": 0.1
    }
  ]
}
````

### Get Order Book

Path: `/orderbook`</br>
Request method: `GET`</br>

Response status code:

- `200`

Response body:

````json
{
  "buyOrders": [
    {
      "price": 2067.12,
      "quantity": 22
    },
    {
      "price": 2032.98,
      "quantity": 66
    }
  ],
  "sellOrders": [
    {
      "price": 2078.12,
      "quantity": 20
    },
    {
      "price": 2099.36,
      "quantity": 23
    }
  ]
}
````

### Get single order

Path: `/order/$id`</br>
Request method: `GET`</br>

Response status code:

- `200`

Response body:

````json
{
  "id": 1,
  "createdDateTime": "2022-09-28T14:44:04.92656",
  "currencyPair": "BTCUSD",
  "type": "BUY",
  "price": 20150.00,
  "quantity": 0.3,
  "filledQuantity": 0.3,
  "status": "CLOSED",
  "trades": [
    {
      "id": 10,
      "buyOrderId": 1,
      "sellOrderId": 5,
      "timestamp": 1662638820000,
      "price": 20150.00,
      "quantity": 0.2
    },
    {
      "id": 11,
      "buyOrderID": 1,
      "sellOrderID": 6,
      "timestamp": 1662638820000,
      "price": 20150.00,
      "quantity": 0.1
    }
  ]
}
````

### Delete all records

Path: `/order/all`</br>
Request method: `DELETE`</br>

Response status code:

- `200`


## Test data
You can find test data in [test-data](/test-data) folder