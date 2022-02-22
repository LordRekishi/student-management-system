# Java Enterprise Course - JAX-RS Laboration 1

A very simple JAX-RS application implementing the Payara server and a drop-and-create H2 database. First assignment of
our Java Enterprise course at IT-Högskolan, Gothenburg.

## Deployment

1. `Download` [Payara Server (full)](https://www.payara.fish/downloads/payara-platformcommunity-edition/ "Payara Server Download")
2. `Unzip the Payara download` to a folder where you have admin access
3. `Clone` this project to your local computer:
   1. Open a `terminal` in the folder you want the project to end up
   2. Run `git clone https://github.com/LordRekishi/student-management-system.git`
4. `Package` the project using `Maven`:
   1. Open a `terminal` in the project folder
   2. Run `./mvnw clean package`
5. `Start` the application on `Payara Server`
   1. Open a `terminal` in the `payara/bin` directory where you'll find the `asadmin` file
   2. Run `asadmin deploy <path to  war file directory>/student-management-system.war`

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