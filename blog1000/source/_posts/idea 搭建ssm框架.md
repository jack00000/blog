---
title: idea 搭建ssm框架  vs  springboot框架
date: 2018-5-22 20:13:10
tags: java
categories: 实验项目
---

## [完整教程](http://how2j.cn/k/idea/idea-maven-idea-ssm-create/1397.html#nowhere)
- 最终结果
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-23_112754.png)

## 备忘
- idea报错  GBK

## 不知为何， 自己新建maven项目 搭建ssm总是报错  目的是搞起天猫整站  目的达到就行
- jdk 与 tomcat 不兼容
- jdk 与 maven 版本不匹配
- gbk 编码格式错误 （我从成功的项目文件一个个复制，可以运行成功 但web页面报错 3天啦  在搞就浪费时间啦）
- spring 无法注入某个类  。。。。
- 还有由此衍生无数新错误，为了有学下去的信心，直奔目的，建站。ssm框架先用现成的

![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-24_180210.png)


## springboot 简单100倍好不好。。。
### 最终结果
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-28_112922.png)

### 思路图
- 直接dao注解方式
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-28_113901.png)
- 间接dao注解方式
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-28_115151.png)

### 配置文件详解1  application.properties
```xml
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

### 配置文件详解2  pom.xml
```xml
<project  一堆规范>
  项目基本信息
  <groupId>com.how2java</groupId>
  <artifactId>springboot</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <name>springboot</name>
  <description>springboot</description>
  <packaging>war</packaging>

  项目唯一标识
  <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
  </parent>

  依赖集合
  <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        ...
        ...
  </dependencies>

  项目构建 不太懂
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
## 实操
- 新建springboot没有webapp  自己新建 然后指定改文件夹为web根目录  [教程](http://www.cnblogs.com/sxdcgaq8080/p/7676294.html)
