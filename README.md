Payara MicroProfile example Gradle Project.
==========================

Simple Payara Microprofile Application Example.


How To Run
-------------------

```
mvn install
java -jar payara-microprofile-*.jar --deploy target/ROOT.war --nocluster
```

Build war file.  And run Payara server.


```
$ curl -i http://localhost:8080/api/emps
```

Make UberJar
---------------

```
java -jar payara-microprofile-*.jar --deploy target/ROOT.war --outputuberjar app.jar
java -jar app.jar
...
Payara Micro URLs
...
```
