Payara MicroProfile example Gradle Project.
==========================

Simple Payara Microprofile Application Example.


How To Run
-------------------


```
./gradlew payara
```

Build war file.  And run Payara server.


```
$ curl -i http://localhost:8080/api/myapi/hello
HTTP/1.1 200 OK
Server: Payara Micro
Content-Type: application/json
Date: Fri, 19 May 2017 03:45:33 GMT
Content-Length: 21

{"msg":"Hello World"}
```

Make UberJar
---------------

```
./gradlew uberJar
```

Create a runnable jar file that contains webapp-war and payara server.

```
$ java -jar build/distributions/app.jar
...
Payara Micro URLs
...
```
