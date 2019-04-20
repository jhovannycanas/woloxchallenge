# Wolox Challenge

Solucion reto Wolox utilizando Spring Boot con Apache Camel.

## Autor Jhovanny Cañas


### Iniciar aplicación

Para iniciar la aplicación ubicarse dentro del directorio raiz, luego ejecutar el comando Maven:

    mvn spring-boot:run

### Requisitos
  Java SDK 1.8
  Maven 3.5

### Rutas

http://localhost:8080/rest/albums/
http://localhost:8080/rest/albums/{id}

http://localhost:8080/rest/albumuser/ post
http://localhost:8080/rest/albumuser/ put

http://localhost:8080/rest/comments/
http://localhost:8080/rest/comments/search?user={user}&id={id}
http://localhost:8080/rest/comments/4

http://localhost:8080/rest/photos/
http://localhost:8080/rest/photos/{id}

http://localhost:8080/rest/posts/
http://localhost:8080/rest/posts/{id}

http://localhost:8080/rest/users/
http://localhost:8080/rest/users/accesslevel/{idaccess}/album/{idalbum}
http://localhost:8080/rest/users/{id}

### Pruebas 

La documentacion se encuentra disponible en Swagger disponible en el siguiente enlace: http://localhost:8080/#

![Screenshot](https://github.com/jhovannycanas/woloxchallenge/blob/master/challengecanas/swagger_ui_wolox.png)
---

Para visualizar las diferentes rutas ofrecidas por el servicio, por favor consultar la documentación del servicio generada en <a href="https://swagger.io/">Swagger<a>.
