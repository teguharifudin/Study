![](https://www.teguharief.com/img/teguh-arief.png)

# Study

Using a DTO as the result of a query in Spring Boot.

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

Then run the app at http://localhost:8081/api

### GET /stocks

### GET /stocks/{id}

### POST /stocks/create
```
{
    "nama": "Tes 1",
    "stok": "100",
    "nomor": "1",
    "file": "./images/example.jpg",
    "usr": "Budi",
    "created": "2024-07-28",
    "rev": null,
    "updated": null
}
```

### PUT /stocks/update
```
{
    "id": 1,
    "nama": "Tes 1",
    "stok": "100",
    "nomor": "1",
    "file": "./images/example.jpg",
    "usr": "Budi",
    "created": "2024-07-28",
    "rev": Budi,
    "updated": "2024-07-29"
}
```

### DELETE /stocks/{id}

## Contributing

Please use the [issue tracker](https://github.com/teguharifudin/Study/issues) to report any bugs or file feature requests.