
# E-Procurement

Sebuah restAPI untuk berkomunikasi dengan aplikasi lain melalui protokol HTTP




## Features

- Vendors Management
- Products Management
- Category Management
- Transaction
- Search


## API Reference (vendors)

#### Get all items

```http
  GET /api/v1/vendors
```

#### Example output

```json
{
    "statusCode": 200,
    "message": "successfully fetch data",
    "data": [
        {
            "id": "a2572fe7-e9f9-4290-bdc3-99d43dd1523c",
            "name": "pt.jaya abadi"
        },
        {
            "id": "c0df36fc-d93d-462a-a415-edfa1fac4210",
            "name": "pt.sumber kencana"
        },
        {
            "id": "97587d34-38a6-4cce-a5ab-445dbf8eccf0",
            "name": "pt.harapan baru"
        },
        {
            "id": "2fc4bc51-2be7-429e-bdf7-dd1e1bfe2199",
            "name": "pt.indofood"
        },
        {
            "id": "c6086406-d9ab-461b-8a93-6fcea50b122d",
            "name": "pt.nestle"
        }
    ],
    "paging": {
        "totalPage": 1,
        "totalElement": 5,
        "page": 1,
        "size": 10,
        "hasNext": false,
        "hasPrevious": false
    }
}
```

#### Get item by Id

```http
  GET /api/v1/vendors/${id}
```

#### Example output

```json
  {
    "statusCode": 200,
    "message": "successfully fetch data",
    "data": {
        "vendorName": "pt.jaya abadi",
        "vendorProductResponses": [
            {
                "nameProduct": "Bakso",
                "price": 100000
            }
        ]
    },
    "paging": null
}
```


#### Create New Vendor

```http
  POST /api/v1/vendors
```

#### Example output

```json
 {
    "statusCode": 201,
    "message": "successfully save data",
    "data": {
        "vendorName": "pt.jaya abadi",
        "vendorProductResponses": [
            {
                "nameProduct": "Bakso",
                "price": 100000
            }
        ]
    },
    "paging": null
}
```

#### Update a Vendor

```http
  PUT /api/v1/vendors
```

#### Example output

```json
 {
    "statusCode": 200,
    "message": "successfully update data",
    "data": {
        "id": "c6086406-d9ab-461b-8a93-6fcea50b122d",
        "name": "pt.kucing"
    },
    "paging": null
}
```

#### Delete a Vendor

```http
  DELETE /api/v1/vendors/{id}
```

#### Example output

```json
 {
    "statusCode": 200,
    "message": "successfully delete",
    "data": null,
    "paging": null
}
```








## All API Reference

#### Product API

| Method    | URL      | Description                |
| :-------- | :------- | :------------------------- |
| `POST` | `api/v1/products` | create data |
| `GET` | `api/v1/products` | get all data |
| `GET` | `api/v1/products/{id}` | get data by id |
| `PUT` | `api/v1/products` | update data |
| `DELETE` | `api/v1/products/{id}` | delete data by id |


#### Transaction API

| Method    | URL      | Description                |
| :-------- | :------- | :------------------------- |
| `POST` | `api/v1/transactions` | create data |
| `GET` | `api/v1/transactions` | get all data |
| `GET` | `api/v1/transactions/{id}` | get data by id |


#### Category API

| Method    | URL      | Description                |
| :-------- | :------- | :------------------------- |
| `POST` | `api/v1/categorys` | create data |
| `GET` | `api/v1/categorys` | get all data |
| `GET` | `api/v1/categorys/{id}` | get data by id |



## Contributing

#### Rifky Aditya (TL)
- Make ERD for mapping table entity
- Make flow for team member
- Make Validation Feature
- Make TransactionSpesification
- Make Spesification and Error Handler
- Make ConstantValue
- Make CommonResponse and Paging
- Set up project for team
- As Maintener for git

#### Muhammad Shabri Rabbani
- Make Create Feature for Vendor
- Make Read Feature for Vendor
- Make Update Feature for Vendor
- Make Delete Feature for Vendor
- Make README.MD

#### Ahmad Ilham
- Make Create Feature for Transaction 
- Make Read Feature for Transaction
- Make Update Feature for Transaction
- Make Delete Feature for Transaction

#### Jogi Arif Guruh Sitinjak
- Make Create Feature for Product and Category
- Make Read Feature for Product and Category
- Make Update Feature for Product and Category
- Make Delete Feature for Product and Category
