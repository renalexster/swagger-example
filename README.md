# swagger-example

Simple project using Apache Camel + Jboss Fuse 6.3, create a REST service using:

- Java 1.8
- camel-servlet
- camel-swagger

To run this project you need Jboss Fuse 6.3 on Karaf and install:

    features:install camel-swagger-java
    features:install camel-servlet
    features:install camel-swagger
    osgi:install war:mvn:io.hawt.swagger/hawtio-swagger-ui/1.4.redhat-621084/war
