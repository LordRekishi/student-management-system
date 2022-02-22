# Java Enterprise Course - JAX-RS Laboration 1

A very simple JAX-RS application implementing the Payara server and a drop-and-create H2 database. First assignment of
our Java Enterprise course at IT-Högskolan, Gothenburg.

## Deployment

1. `Download` [Payara Server (full)](https://www.payara.fish/downloads/payara-platformcommunity-edition/ "Payara Server Download")
2. `Unzip the Payara download` to a folder where you have admin access
3. `Fork or clone` this repository
4. `Open project in an IDE` (IntelliJ, etc)
5. Add Payara server as your `run configuration`
    - Add the `build 'student-management-system:war' artifact` task
6. `Run` the Payara Server

## How to use

Use `Insomnia` to send requests to the application: [Download here!](https://insomnia.rest/download "Insomnia Download")

### Endpoints

`POST /api/v1/students` - `add` a `student object` to the database using JSON formatting:

```json
{
  "firstName": "John",
  "lastName": "Doe ",
  "email": "john.doe@gmail.com",
  "phoneNumber": "1234567890"
}
```

`GET /api/v1/students` - `get` a list of `all students` in the database, returned as a list of JSON objects:

```json
[
  {
    "email": "john.doe@gmail.com",
    "firstName": "John",
    "id": 1,
    "lastName": "Doe",
    "phoneNumber": "1234567890"
  },
  {
    "email": "jane.doe@gmail.com",
    "firstName": "Jane",
    "id": 2,
    "lastName": "Doe",
    "phoneNumber": "0987654321"
  }
]
```

`GET /api/v1/students/{id}` - `get` a `student by ID`, using a `path parameter`. Returned as a JSON object:

```json
{
  "email": "john.doe@gmail.com",
  "firstName": "John",
  "id": 1,
  "lastName": "Doe ",
  "phoneNumber": "1234567890"
}
```

`GET /api/v1/students/filter?lastname={name}` - `get` a list of `all students by last name`, using a `query parameter`.

`PUT /api/v1/students` - `update` a whole `student object` already in the database using JSON formatting. Don't forget
to add all fields for the object.

`DELETE /api/v1/students/{id}` - `delete` a `student` from the database using a `path parameter`.

Copyright (c) 2022 by Patrik Fallqvist Magnusson