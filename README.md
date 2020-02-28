# Requisitos previos

Antes que nada debes instalar gradle, este proyecto usa gradle en lugar de maven a continuación tienes el link https://gradle.org/
 

# Caracteristicas!

  -Esta desarrollado en groovy http://groovy-lang.org/ la sintaxis no es muy distinta a java
  -Usa test unitarios escritos en junit y spock

### Tecnologìas usadas

* [Spring boot 2.1.3](https://spring.io/projects/spring-boot) - Spring based application with embeded tomcat server

* [Groovy 2.4.15](http://groovy-lang.org/) - Programming Language
* [Spock](http://spockframework.org/) - Testing framework 
* [Jacoco](https://www.jacoco.org/jacoco/trunk/doc/) - Code coverage framework validator
* [Swagger](https://swagger.io/) - Documentation 


## Uso

###Iniciar el API.

```sh
$ cd kairosApi
$ ./gradlew clean bootRun   
```
Podrás verificar el api en el siguiente url http://localhost:8080/api/v1/products/ toma en cuenta que deberás usar algún cliente 

###Documentación del API
Verifica la documentación del API en swagger, tambièn podrás utilizar el cliente web de swagger para probar los endpoints
```sh 
 http://localhost:8080/api/v1/swagger-ui.html
```
###Correr los test

```sh
$ ./gradlew clean 
$ ./gradlew clean test
```
Podrás visualizar el resultado de los mismos en cualquier browser en la siguiente dirección:

```sh
http://localhost:63342/kairosAPi/build/reports/tests/test/index.html
```
O en su defecto se encontrara el archivo html en kairosApi/build/reports/tests/test/index.html

Para revisar el coverage de los test
```sh
http://localhost:63342/kairosAPi/build/reports/tests/test/index.html
```
O en su defecto podrás encontrar el archivo html en kairosApi/build/jacocoHtml/index.html



###Actuator
Para revisar trasa metricas healtcheck revisa
```sh
http://localhost:8080/api/v1/actuator
```

License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
