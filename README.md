# One Piece API (Java Spring Boot)

Welcome to One Piece Spring! This is a project aimed at creating a web application for fans of the popular manga and anime series, One Piece.

## Installation

1. Clone the repository: `git clone https://github.com/marceljsh/onepiece-spring.git`
2. Enjoy!

## Contributing

Contributions are welcome! If you'd like to contribute to the project, please follow these steps:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature`
3. Make your changes and commit them: `git commit -m 'Add your changes'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a pull request.

## Endpoints

#### Default endpoints

<details>
<summary>
  <code>GET</code>
  <code><b>/</b></code>
</summary>

##### Parameters

> None

##### Responses

> | http code | content-type       | response                      |
> | --------- | ------------------ | ----------------------------- |
> | `200`     | `application/json` | `{"message":"one piece api"}` |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" --data @post.json http://localhost:8080/
> ```

</details>

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/ping</b>
  </code>
</summary>

##### Parameters

> None

##### Responses

> | http code | content-type       | response                          |
> | --------- | ------------------ | --------------------------------- |
> | `200`     | `application/json` | `{"message":"host is reachable"}` |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" --data @post.json http://localhost:8080/ping
> ```

</details>

---

#### Affiliation

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/affiliations/{id}</b>
  </code>
</summary>

Gets a specific Affiliation by its ID

##### Parameters

> | name              | type     | data type    | description                         |
> | ----------------- | -------- | ------------ | ----------------------------------- |
> | `id`              | required | integer      | The specific affiliation numeric id |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `application/json`         | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/3
> ```

</details>

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/affiliations?keyword={keyword}&page={page}&size={size}</b>
  </code>
</summary>

Gets Affiliations where by default page is 1 and size is 10

##### Parameters

> None

##### Query Parameters

> | name              | type     | data type    | description                   |
> | ----------------- | -------- | ------------ | ----------------------------- |
> | `keyword`         | optional | string       | search keyword                |
> | `page`            | optional | integer      | page index                    |
> | `size`            | optional | string       | max number of items in a page |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/affiliations?keyword=pirates&page=2&size=5
> ```

</details>

<details>
<summary>
  <code>POST</code>
  <code>
    <b>/api/v1/affiliations</b>
  </code>
</summary>

Creates a new Affiliation

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" http://localhost:8080/api/v1/affiliations
> ```

</details>

<details>
<summary>
  <code>PUT</code>
  <code>
    <b>/api/v1/affiliations/{id}</b>
  </code>
</summary>

Updates an existing Affiliation

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `400`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/affiliations/69
> ```

</details>

<details>
<summary>
  <code>DELETE</code>
  <code>
    <b>/api/v1/affiliations/{id}</b>
  </code>
</summary>

Deletes an existing Affiliation 

##### Parameters

> None

##### Responses

> | http code | content-type               | response                                         |
> | --------- | -------------------------- | ------------------------------------------------ |
> | `200`     | `application/json`         | `{"message", "affiliation deleted successfully"}` |

##### Example cURL

> ```javascript
>  curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/v1/affiliations/{id}
> ```

</details>

---

#### Devil Fruit Type

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/df-types/{id}</b>
  </code>
</summary>

Gets a specific Devil Fruit Type by its ID

##### Parameters

> | name              | type     | data type    | description                              |
> | ----------------- | -------- | ------------ | ---------------------------------------- |
> | `id`              | required | integer      | The specific devil fruit type numeric id |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `application/json`         | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/df-types/2
> ```

</details>

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/df-types?keyword={keyword}&page={page}&size={size}</b>
  </code>
</summary>

Gets Devil Fruit Types where by default page is 1 and size is 10

##### Parameters

> None

##### Query Parameters

> | name              | type     | data type    | description                   |
> | ----------------- | -------- | ------------ | ----------------------------- |
> | `keyword`         | optional | string       | search keyword                |
> | `page`            | optional | integer      | page index                    |
> | `size`            | optional | string       | max number of items in a page |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/df-types?keyword=zoan&page=2&size=2
> ```

</details>

<details>
<summary>
  <code>POST</code>
  <code>
    <b>/api/v1/df-types</b>
  </code>
</summary>

Creates a new Devil Fruit Type

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" http://localhost:8080/api/v1/df-types
> ```

</details>

<details>
<summary>
  <code>PUT</code>
  <code>
    <b>/api/v1/df-types/{id}</b>
  </code>
</summary>

Updates an existing Devil Fruit Type

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `400`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/df-types/69
> ```

</details>

<details>
<summary>
  <code>DELETE</code>
  <code>
    <b>/api/v1/df-types/{id}</b>
  </code>
</summary>

Deletes an existing Devil Fruit Type

##### Parameters

> None

##### Responses

> | http code | content-type               | response                                              |
> | --------- | -------------------------- | ----------------------------------------------------- |
> | `200`     | `application/json`         | `{"message", "devil fruit type deleted successfully"}` |

##### Example cURL

> ```javascript
>  curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/v1/df-types/{id}
> ```

</details>

---

#### Occupation

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/occupations/{id}</b>
  </code>
</summary>

Gets a specific Occupation by its ID

##### Parameters

> | name              | type     | data type    | description                        |
> | ----------------- | -------- | ------------ | ---------------------------------- |
> | `id`              | required | integer      | The specific occupation numeric id |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `application/json`         | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/occupations/2
> ```

</details>

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/occupations?keyword={keyword}&page={page}&size={size}</b>
  </code>
</summary>

Gets Occupations where by default page is 1 and size is 10

##### Parameters

> None

##### Query Parameters

> | name              | type     | data type    | description                   |
> | ----------------- | -------- | ------------ | ----------------------------- |
> | `keyword`         | optional | string       | search keyword                |
> | `page`            | optional | integer      | page index                    |
> | `size`            | optional | string       | max number of items in a page |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/occupations?keyword=pirates&page=2&size=5
> ```

</details>

<details>
<summary>
  <code>POST</code>
  <code>
    <b>/api/v1/occupations</b>
  </code>
</summary>

Creates a new Occupation

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" http://localhost:8080/api/v1/occupations
> ```

</details>

<details>
<summary>
  <code>PUT</code>
  <code>
    <b>/api/v1/occupations/{id}</b>
  </code>
</summary>

Updates an existing Occupation

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `400`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/occupations/69
> ```

</details>

<details>
<summary>
  <code>DELETE</code>
  <code>
    <b>/api/v1/occupations/{id}</b>
  </code>
</summary>

Deletes an existing Occupation

##### Parameters

> None

##### Responses

> | http code | content-type               | response                                              |
> | --------- | -------------------------- | ----------------------------------------------------- |
> | `200`     | `application/json`         | `{"message", ""occupation deleted successfully"}` |

##### Example cURL

> ```javascript
>  curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/v1/occupations/{id}
> ```

</details>

---

#### Region

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/regions/{id}</b>
  </code>
</summary>

Gets a specific Region by its ID

##### Parameters

> | name              | type     | data type    | description                    |
> | ----------------- | -------- | ------------ | ------------------------------ |
> | `id`              | required | integer      | The specific region numeric id |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `application/json`         | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/regions/2
> ```

</details>

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/regions?keyword={keyword}&page={page}&size={size}</b>
  </code>
</summary>

Gets Regions where by default page is 1 and size is 10

##### Parameters

> None

##### Query Parameters

> | name              | type     | data type    | description                   |
> | ----------------- | -------- | ------------ | ----------------------------- |
> | `keyword`         | optional | string       | search keyword                |
> | `page`            | optional | integer      | page index                    |
> | `size`            | optional | string       | max number of items in a page |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/regions?keyword=pirates&page=2&size=5
> ```

</details>

<details>
<summary>
  <code>POST</code>
  <code>
    <b>/api/v1/regions</b>
  </code>
</summary>

Creates a new Region

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" http://localhost:8080/api/v1/regions
> ```

</details>

<details>
<summary>
  <code>PUT</code>
  <code>
    <b>/api/v1/regions/{id}</b>
  </code>
</summary>

Updates an existing Region

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `400`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/regions/69
> ```

</details>

<details>
<summary>
  <code>DELETE</code>
  <code>
    <b>/api/v1/regions/{id}</b>
  </code>
</summary>

Deletes an existing Region

##### Parameters

> None

##### Responses

> | http code | content-type               | response                                              |
> | --------- | -------------------------- | ----------------------------------------------------- |
> | `200`     | `application/json`         | `{"message", {"region deleted successfully"}` |

##### Example cURL

> ```javascript
>  curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/v1/regions/{id}
> ```

</details>

---

#### Devil Fruit

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/devil-fruits/{id}</b>
  </code>
</summary>

Gets a specific Devil Fruit by its ID

##### Parameters

> | name              | type     | data type    | description                         |
> | ----------------- | -------- | ------------ | ----------------------------------- |
> | `id`              | required | integer      | The specific devil fruit numeric id |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `application/json`         | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/devil-fruits/2
> ```

</details>

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/devil-fruits?keyword={keyword}&page={page}&size={size}</b>
  </code>
</summary>

Gets Devil Fruits where by default page is 1 and size is 10

##### Parameters

> None

##### Query Parameters

> | name              | type     | data type    | description                   |
> | ----------------- | -------- | ------------ | ----------------------------- |
> | `keyword`         | optional | string       | search keyword                |
> | `page`            | optional | integer      | page index                    |
> | `size`            | optional | string       | max number of items in a page |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/devil-fruits?keyword=pirates&page=2&size=5
> ```

</details>

<details>
<summary>
  <code>POST</code>
  <code>
    <b>/api/v1/devil-fruits</b>
  </code>
</summary>

Creates a new Devil Fruit

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" http://localhost:8080/api/v1/devil-fruits
> ```

</details>

<details>
<summary>
  <code>PUT</code>
  <code>
    <b>/api/v1/devil-fruits/{id}</b>
  </code>
</summary>

Updates an existing Devil Fruit

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `400`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/devil-fruits/69
> ```

</details>

<details>
<summary>
  <code>DELETE</code>
  <code>
    <b>/api/v1/devil-fruits/{id}</b>
  </code>
</summary>

Deletes an existing Devil Fruit

##### Parameters

> None

##### Responses

> | http code | content-type               | response                                              |
> | --------- | -------------------------- | ----------------------------------------------------- |
> | `200`     | `application/json`         | `{"message", {"devil fruit deleted successfully"}` |

##### Example cURL

> ```javascript
>  curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/v1/devil-fruits/{id}
> ```

</details>

---

#### Figure

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/figures/{id}</b>
  </code>
</summary>

Gets a specific Figure by its ID

##### Parameters

> | name              | type     | data type    | description                    |
> | ----------------- | -------- | ------------ | ------------------------------ |
> | `id`              | required | integer      | The specific figure numeric id |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `application/json`         | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/figures/2
> ```

</details>

<details>
<summary>
  <code>GET</code>
  <code>
    <b>/api/v1/figures?keyword={keyword}&page={page}&size={size}</b>
  </code>
</summary>

Gets Figures where by default page is 1 and size is 10

##### Parameters

> None

##### Query Parameters

> | name              | type     | data type    | description                   |
> | ----------------- | -------- | ------------ | ----------------------------- |
> | `keyword`         | optional | string       | search keyword                |
> | `page`            | optional | integer      | page index                    |
> | `size`            | optional | string       | max number of items in a page |

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/figures?keyword=pirates&page=2&size=5
> ```

</details>

<details>
<summary>
  <code>POST</code>
  <code>
    <b>/api/v1/figures</b>
  </code>
</summary>

Creates a new Figure

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X POST -H "Content-Type: application/json" http://localhost:8080/api/v1/figures
> ```

</details>

<details>
<summary>
  <code>PUT</code>
  <code>
    <b>/api/v1/figures/{id}</b>
  </code>
</summary>

Updates an existing Figure

##### Parameters

> None

##### Responses

> | http code | content-type               | response    |
> | --------- | -------------------------- | ----------- |
> | `200`     | `text/plain;charset=UTF-8` | JSON string |
> | `400`     | `text/plain;charset=UTF-8` | JSON string |
> | `500`     | `text/plain;charset=UTF-8` | JSON string |

##### Example cURL

> ```javascript
>  curl -X GET -H "Content-Type: application/json" http://localhost:8080/api/v1/figures/69
> ```

</details>

<details>
<summary>
  <code>DELETE</code>
  <code>
    <b>/api/v1/figures/{id}</b>
  </code>
</summary>

Deletes an existing Figure

##### Parameters

> None

##### Responses

> | http code | content-type               | response                                              |
> | --------- | -------------------------- | ----------------------------------------------------- |
> | `200`     | `application/json`         | `{"message", {"figure deleted successfully"}` |

##### Example cURL

> ```javascript
>  curl -X DELETE -H "Content-Type: application/json" http://localhost:8080/api/v1/figures/{id}
> ```

</details>

---
