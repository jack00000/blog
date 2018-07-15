---
title: tmall_ssm项目学习_1
date: 2018-5-22 20:13:10
tags: java
categories: 实验项目
---

#### 3.7 第五个配置文件 springMVC.xml   1,3,4,5 都记不住 可以直接复制
- 1.解释在第一篇
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd">
 ```

 - 2.指定controller 注解识别    applicationContext.xml指定啦service的注解识别  有所不同
 ```xml
 <context:annotation-config/>

    <context:component-scan base-package="com.how2java.tmall.controller">
        <context:include-filter type="annotation"
                                expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <mvc:annotation-driven />
```

- 3.开通静态资源的访问
    <mvc:default-servlet-handler />

- 4. 视图定位
```xml
    <bean
            class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass"
                  value="org.springframework.web.servlet.view.JstlView" />
        <property name="prefix" value="/WEB-INF/jsp/" />
        <property name="suffix" value=".jsp" />
    </bean>
```
- 5. 对上传文件的解析
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"/>
</beans>

#### 3.8 第六个配置文件  web.xml
- 1.第一篇有解释
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
         version="2.5">
 ```
 - 2.spring的配置文件   和applicatinContext.xml 有联系
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

- 3.中文过滤器--> 可以直接复制
  <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

- 4.spring mvc核心：分发servlet -->
  <servlet>
    <servlet-name>mvc-dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
- 5.spring mvc的配置文件 --> 和自己连接  有点
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springMVC.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>mvc-dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>

#### 第七个配置文件  CategoryMapper.xml
- 规范之类的东西 直接复制
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
```


- 2.namespace指明类  id 为什么是list  resultType  返回类型是category对象？？？  应该是与categoryMapperImpl.java 文件有关
```xml
<mapper namespace="com.how2java.tmall.mapper.CategoryMapper">
  <select id="list" resultType="Category">
    select * from   category order by id desc
  </select>
</mapper>
```

#### 其他文件简单不说 阶段性截图  org.springframework.beans.factory.BeanCreationException   好像缺包
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-23_014834.png)
- 凌晨3点进度截图 tomcat 部署正常  网页404.。。。
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-23_024502.png)
