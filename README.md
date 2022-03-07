# Java Enterprise Course - JAX-RS Laboration 2

A very simple JAX-RS application implementing the Payara server and a drop-and-create H2 database. Second assignment of
our Java Enterprise course at IT-Högskolan, Gothenburg.

---

## Deployment

1. `Download` [Payara Server (full)](https://www.payara.fish/downloads/payara-platform-community-edition/ "Payara Server Download")
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

---

## How to use

Use `Insomnia` to send requests to the
application: [Download here!](https://insomnia.rest/download "Insomnia Download")   
Navigate to: `http://localhost:8080/student-management-system/<endpoint>`

---

## Endpoints

### Student

`POST` `/api/v1/students` - `add` a `student object` to the database using JSON formatting:

```json
{
   "firstName": "John",
   "lastName": "Doe",
   "email": "john.doe@gmail.com",
   "phoneNumber": "1234567890"
}
```

`GET` `/api/v1/students` - `get` a list of `all students` in the database, returned as a list of JSON objects:

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

`GET` `/api/v1/students/{id}` - `get` a `student by ID`, using a `path parameter`. Returned as a JSON object:

```json
{
   "email": "john.doe@gmail.com",
   "firstName": "John",
   "id": 1,
   "lastName": "Doe",
   "phoneNumber": "1234567890"
}
```

`GET` `/api/v1/students/filter?lastname={name}` - `get` a list of `all students by last name`, using a `query parameter`

`PUT` `/api/v1/students/{id}` - `update` a whole `student object` already in the database using JSON formatting. Don't
forget to add all fields for the object!

`DELETE /api/v1/students/{id}` - `delete` a `student` from the database using a `path parameter`

---

### Teacher

`POST` `/api/v1/teachers` - `add` a `teacher object` to the database using JSON formatting:

```json
{
   "firstName": "John",
   "lastName": "Doe"
}
```

`GET` `/api/v1/teachers` - `get` a list of `all teachers` in the database, returned as a list of JSON objects:

```json
[
   {
      "firstName": "John",
      "id": 1,
      "lastName": "Doe"
   },
   {
      "firstName": "Jane",
      "id": 2,
      "lastName": "Doe"
   }
]
```

`GET` `/api/v1/teachers/{id}` - `get` a `teacher by ID`, using a `path parameter`. Returned as a JSON object:

```json
{
   "firstName": "John",
   "id": 1,
   "lastName": "Doe "
}
```

`GET` `/api/v1/teachers/filter?lastname={name}` - `get` a list of `all teachers by last name`, using a `query parameter`

`PUT` `/api/v1/teachers/{id}` - `update` a whole `teacher object` already in the database using JSON formatting. Don't
forget to add all fields for the object!

`DELETE` `/api/v1/teachers/{id}` - `delete` a `teacher` from the database using a `path parameter`

---

### Subject

`POST` `/api/v1/subjects` - `add` a `subject object` to the database using JSON formatting:

```json
{
   "name": "Java"
}
```

`GET` `/api/v1/subjects` - `get` a list of `all subjects` and their `students` and `teacher` in the database, returned
as a list of JSON objects:

```json
[
   {
      "id": 5,
      "name": "Java",
      "students": [
         {
            "email": "john@gmail.com",
            "firstName": "John",
            "id": 1,
            "lastName": "Doe",
            "phoneNumber": "0234567"
         },
         {
            "email": "jane@gmail.com",
            "firstName": "Jane",
            "id": 2,
            "lastName": "Doe",
            "phoneNumber": "0123456"
         }
      ],
      "teacher": {
         "firstName": "Martin",
         "id": 7,
         "lastName": "Blomberg"
      }
   },
   {
      "id": 6,
      "name": "MySQL",
      "students": [
         {
            "email": "james@gmail.com",
            "firstName": "James",
            "id": 3,
            "lastName": "Doe",
            "phoneNumber": "0345678"
         },
         {
            "email": "jenny@gmail.com",
            "firstName": "Jenny",
            "id": 4,
            "lastName": "Doe",
            "phoneNumber": "0456789"
         }
      ],
      "teacher": {
         "firstName": "Pontus",
         "id": 8,
         "lastName": "Redig"
      }
   }
]
```

`GET` `/api/v1/subjects/{id}` - `get` a `subject by ID`, using a `path parameter`. Returned as a JSON object:

```json
{
   "id": 5,
   "name": "Java",
   "students": [
      {
         "email": "john@gmail.com",
         "firstName": "John",
         "id": 1,
         "lastName": "Doe",
         "phoneNumber": "0234567"
      },
      {
         "email": "jane@gmail.com",
         "firstName": "Jane",
         "id": 2,
         "lastName": "Doe",
         "phoneNumber": "0123456"
      }
   ],
   "teacher": {
      "firstName": "Martin",
      "id": 7,
      "lastName": "Blomberg"
   }
}
```

`GET` `/api/v1/subjects/filter?name={name}` - `get` a list of `all subjects by name`, using a `query parameter`

`PUT` `/api/v1/subjects/{id}` - `update` a whole `subject object` already in the database using JSON formatting. Don't
forget to add all fields for the object!

`DELETE` `/api/v1/subjects/{id}` - `delete` a `subject` from the database using a `path parameter`

`PUT` `/api/v1/subjects/{id}/students/add?student-id={id}` - `add` a `student` to a `subject`, using a `query parameter`

`PUT` `/api/v1/subjects/{id}/teachers/add?teacher-id={id}` - `add` a `teacher` to a `subject`, using a `query parameter`

`PUT` `/api/v1/subjects/{id}/students/remove?student-id={id}` - `remove` a `student` from a `subject`, using
a `query parameter`

`PUT` `/api/v1/subjects/{id}/teachers/remove` - `remove` a `teacher` from a `subject`

---


Copyright (c) 2022 by Patrik Fallqvist Magnusson
