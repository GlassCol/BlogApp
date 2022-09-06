# **BlogApp**

Basic blog app with CRUD functionality

To Start Front End App:

1. navigate into front-end directory
2. type "npm install" or "npm update" to install, update dependencies, respectively
3. type "npm start" or "npm run start" within a terminal or command prompt window
4. in a browser, type in the address bar localhost:3000 to view the application

To Start Backend App:

1. navigate into the server directory
2. install maven packages using an IDE or "mvn build" within a terminal or command prompt window - if maven has been set up in your systems PATH
3. configure the IDE's run configuration to point to main method - which is in the SpringApp class
4. create an "application-dev.properties" file within the "resources' directory or uncomment the existing defaults inside the application.properties file
5. add your local / systems' database connection url and credentials in the application/properties file
6. run the app using the IDE or by executing the jar file within a terminal window
7. in a browser, type in the address bar localhost:8080 or localhost:xxx, e.g.[your configured server.port=9011]

</br>
</br>

# **Api Resources**

</br>
</br>

## Posts Resources

</br>

| Route | Params | Accepted Method | Response Type |
|:-- | :--: | :--: | --: |
| /posts | none | GET |  json |

Response Body:

```
[
    { 
        "postId": "1",
        "title": "About the city",
        "body": "Body text",
        "photos": [
            {
                "id": "1",
                "imageUrl": "http://...",
                "description": "image decription",
                "postId": "1"
            }
        ],
        "comments": [
            {
                "id": "1",
                "body": "comments about the post",
                "postId": "1",
                "userId": "1"
            }
        ],
        user: {
            "userId": "1",
            "username": "alice"
        }
    },
        { 
        "postId": "2",
        "title": "About the city",
        "body": "Body text",
        "photos": [
            {
                "id": "1",
                "imageUrl": "http://...",
                "description": "image decription",
                "postId": "2"
            }
        ],
        "comments": [
            {
                "id": "1",
                "body": "comments about the post",
                "postId": "2,
                "userId": "1"
            }
        ],
        user: {
            "userId": "2",
            "username": "alice"
        }
    }
]
```

| Route | Params | Accepted Method | Response Type |
|:-- | :--: | :--: | --: |
| /posts | 1 | GET |  json |

Response Body:

```
{
    id: 1,
    title: "nesciunt",
    body: " nesciunt quas odio eum et est occaecr",
    updatedAt: "2022-09-06 10:14:38",
    createdAt: "2022-09-06 10:14:37",
    comments: [
        {
            id: 1,
            body: "nesciquas odio",
            parent: true,
            updatedAt: "2022-09-06 10:14:37",
            createdAt: "2022-09-06 10:14:37",
            post: 1
        },
    ],
    photos: [
        {
            id: 1,
            imageUrl: "https://picsum.photos/id/695/200/300",
            title: "nesciunt",
            imageType: "post",
            createdAt: "2022-09-06 10:14:37",
            updatedAt: "2022-09-06 10:14:37",
            post: 1
        }
    ],
    user: {
        id: 1,
        username: "Leane"
    }
}
```

| Route | Params | Accepted Method | Response Type |
|:-- | :--: | :--: | --: |
| /posts/users/ | 1 | GET |  json |

Response Body:

```
{
    id: 1,
    title: "nesciunt",
    body: " nesciunt quas odio eum et est occaecr",
    updatedAt: "2022-09-06 10:14:38",
    createdAt: "2022-09-06 10:14:37",
    comments: [
        {
            id: 1,
            body: "nesciquas odio",
            parent: true,
            updatedAt: "2022-09-06 10:14:37",
            createdAt: "2022-09-06 10:14:37",
            post: 1
        },
    ],
    photos: [
        {
            id: 1,
            imageUrl: "https://picsum.photos/id/695/200/300",
            title: "nesciunt",
            imageType: "post",
            createdAt: "2022-09-06 10:14:37",
            updatedAt: "2022-09-06 10:14:37",
            post: 1
        }
    ],
    user: {
        id: 1,
        username: "Leane"
    }
}
```

| Route | Params | Accepted Method | Response Type |
|:-- | :--: | :--: | --: |
| /posts | json | POST |  json |

Request Body:

```
{
    "title": "Test A", 
    "body": "Test Body A",
    "username": "Ervin",
    "photos": [
        {
            "imageUrl": "https://picsum.photos/id/330/200/300",
            "title": "nesciunt",
            "imageType": "post"
        }
    ]
}
```

Response Body:

```
{
    "id": 9,
    "title": "Test A",
    "body": "Test Body A",
    "updatedAt": "2022-09-06T15:36:59.3103926",
    "createdAt": "2022-09-06T15:36:59.3103926",
    "comments": null,
    "photos": null,
    "user": {
        "id": 3,
        "username": "Ervin"
    }
}
```

| Route | Params | Accepted Method | Response Type |
|:-- | :--: | :--: | --: |
| /posts | json | PUT |  json |

Request Body:

```
{
    "id": 6,
    "title": "Update A",
    "body": "Update Body A",
    "username": "Ervin"
}
```

Response Body:

```
{
    "id": 6,
    "title": "Update A",
    "body": "Update Body A",
    "updatedAt": "2022-09-06 15:56:43",
    "createdAt": "2022-09-06 15:56:43",
    "comments": null,
    "photos": null,
    "user": {
        "id": 3,
        "username": "Ervin"
    }
}
```

| Route | Params | Accepted Method | Response Type |
|:-- | :--: | :--: | --: |
| /posts | 1 | DELETE |  json |

Response Body:

```
{
    "code": 200,
    "data": null,
    "message": "Resource deleted successfully",
    "status": "OK"
}
```

</br>
</br>
</br>

## Categories Resources

</br>

| Route | Params | Accepted Method | Response Type |
|:-- | :--: | :--: | --: |
| /categories | none | GET |  json |

Response Body:

```
[
    {
        id: 7,
        label: "Anime",
        subCategories: [
        {
            id: 38,
            label: "Drama",
            category: 7
        },
    ],
    primary: true
    },
    {
        id: 21,
        label: "Auto",
        subCategories: [
            {
            id: 125,
            label: "Drinking",
            category: 21
    },
    ],
    primary: true
    }
]
```

| Route | Param | Accepted Method | Response Type |
|:-- | :--: | :--: | --: |
| /categories | 1 | GET |  json |

Response Body:

```
{
    id: 1,
    label: "Fitness",
    subCategories: [
        {
            id: 4,
            label: "Binging",
            category: 1
        },
        {
            id: 3,
            label: "Cooking",
            category: 1
        }
    ],
    primary: true
},
```
