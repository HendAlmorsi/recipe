# recipe

## Run: 
`docker-compose build && docker-compose up`

## Credientials:
#### ADMIN
* Email:`'test7@test.com'`
* Password: `1234`

#### USER
* Email:`'test5@test.com'`
* Password: `1234`

## Test Scenario
[a link](https://github.com/HendAlmorsi/recipe/blob/main/testScenarios)

## Postman 
[a link](https://github.com/HendAlmorsi/recipe/blob/main/Recipe.postman_collection.json)

## MySql intellj connection

![alt text](https://github.com/HendAlmorsi/recipe/blob/main/Screenshot%202021-12-18%20at%2019.56.09.png)

## Application

1) Call `/register` to register new user with USER ROLE (Anyone can access this path)
2) Only admin can call `/users`
3) Both admin and user can call `/recipes`

#### /register

body:

```
{
    "firstName": "first",
    "lastName": "last",
    "password": "1234",
    "matchingPassword": "1234",
    "email": "test@test.com"
}
```

#### /users

1) Get all: `GET /users` 
2) Get user: `GET /users/{id}`
3) Update user: `PUT /users/{id}`
email can not be updated after creation

```
{
    "firstName": "first",
    "lastName": "name",
    "email": "test@test.com",
    "roles": [
        "USER"
    ],
    "recipes": []
}
```

4) Delete user: `DELETE /users/{id}`


#### /recipes

1) Get all: `GET /recipes`
2) Get recipe: `GET /recipes/{id}`
3) Add recipe: `POST /recipes`

```
{
    "isVegetarian": true,
    "numberOfPeople": 2,
    "ingredients": [{"name": "apple", "count": 2}],
    "instruction": "blabla"
}
```

4) Update recipe: `PUT /recipes/{id}`

```
{
    "isVegetarian": true,
    "numberOfPeople": 2,
    "ingredients": [{"name": "apple", "count": 2}, {"name": "orange", "count": 2}],
    "instruction": "blablo"
}
```

5) Delete recipe: `DELETE /recipes/{id}`
