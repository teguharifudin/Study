![](https://www.teguharief.com/img/teguh-arief.png)

# Study

Using Spring Data JPA to fetch a DTO as the result of map queries in Spring Boot with PostgreSQL.

## Installation

Install the app on terminal

```
git clone git@github.com:teguharifudin/Study.git
```
```
cd Study
```
```
mvn spring-boot:run
```

## Usage

Then run the app at http://localhost:8081/api/stocks

### GET /

### GET /{id}

### POST /create
```
{
    "nama": "Tes",
    "stok": "100",
    "nomor": "1",
    "attributes": "{\"size\": \"6 inch\", \"color\": \"blue\"}",
    "file": "./images/example.jpg",
    "usr": "Budi",
    "created": "2024-07-28",
    "rev": null,
    "updated": null
}
```

### PUT /update
```
{
    "id": 1,
    "nama": "Tes",
    "stok": "100",
    "nomor": "1",
    "attributes": "{\"size\": \"6 inch\", \"color\": \"blue\"}",
    "file": "./images/example.jpg",
    "usr": "Budi",
    "created": "2024-07-28",
    "rev": "Budi",
    "updated": "2024-07-29"
}
```

### DELETE /{id}

## Contributing

Please use the [issue tracker](https://github.com/teguharifudin/Study/issues) to report any bugs or file feature requests.
