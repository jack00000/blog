---
title: springboot框架剖析
date: 2018-9-7 20:13:10
tags:
---

#  原理：约定优于配置的设计&&开发整合包
- springboot框架是对ssm/ssh 配置的简化

## 配置文件 application.yml
- 在application.yml 配置spring, mybatis/hibernate server
- jpa是标准接口 hibernate是其实现
```java
server:
  port: 8083
  servlet:
    context-path:
spring:
  datasource:
    username: root
    password: admin
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/dbgril
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

```


## 依赖组件：pom.xml
- 配置所需依赖&项目基本信息eg:groupId,artifactId..

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

  <groupId>com.how2java</groupId>
  <artifactId>springboot</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>springboot</name>
  <description>springboot</description>
  <packaging>war</packaging>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>

        </dependency>
        <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>3.8.1</version>
              <scope>test</scope>
        </dependency>
        <!-- servlet依赖. -->
        <dependency>
              <groupId>javax.servlet</groupId>
              <artifactId>javax.servlet-api</artifactId>

        </dependency>
              <dependency>
                     <groupId>javax.servlet</groupId>
                     <artifactId>jstl</artifactId>
              </dependency>
        <!-- tomcat的支持.-->
        <dependency>
               <groupId>org.apache.tomcat.embed</groupId>
               <artifactId>tomcat-embed-jasper</artifactId>

        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional> <!-- 这个需要为 true 热部署才有效 -->
        </dependency>

        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.1.1</version>
        </dependency>

        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.21</version>
        </dependency>
    </dependencies>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```
