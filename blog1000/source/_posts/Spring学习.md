---
title: spring(ApplicationContext.xml) 就是创建各种对象的工厂。
date: 2018-7-23 20:13:10
tags: java
categories: 无锡实训java
---


#  大致内容
- 只要给id 就能根据id 所指的class对象创建出来的工厂。
- 可以把相关联的对象也创建出来(这就是依赖注入。。。)
- ApplicationContext.xml  lesson14  bean  可以创建对象  方便action里面的操作
- 要web服务器 加载时 创建出来  对象。
- 所有对象都实现
- 所有的action能不能用spring进行创建？
- 通过id 能创建对象   并创建其关联对象
- spring 对web的支持。
- web.xml   建立监听器
- 以前手动通过文件创建对象    spring 则帮你创建并能实现关联。
- spring 就是一个创建对象的工厂。
- 发现问题：web一般是用代理模式，有没有一种方法：用到代理对象，今后直接在ApplicationContext.xml里面配置？
- 需要修改工厂的代理对象方法。
- 代理对象中获取session  web层对象？
- 用ThreadLocal
- spring在加载lisener是把request对象放在啦ThreadLocal
- 什么时候开始用spring->从action开始用的  比LoginServet还要早。
- 在任何地方获得request 就可以获得session
- 实现类配置时不引用代理类  直接引用实现类。

```java
ApplicationContext context =
Object o=context.getBean("id")
```
### spring 创建代理对象。(以前是工厂获得代理对象)
```java
//代理对象的配置。
<bean id="UserProxy" class=".....factory" factory-method="getProxyInstance">
<constructor-arg ref="UserDao"></bean>


//把UserDao换成UserProxy
<bean id="UserDao" class=".....UserDao" >
<property name="id"  ref="UserProxy"></property></bean>


```


### 在任何地方获得request 就可以获得session
- 两种情况：
- 以前是Application类来实现    代理实现权限控制。
```java
import org.springframework
HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).kdhf
HttpServlet httpsession=request.getSesion();
```

```java
Uer u=(User)httpsession.getAttributes("user");
if(u==null){
  result=method.invoke(target,args);
}
//第一次调用 代理对象
String role=u.getRole();
```

### 总结一下
- spring 创建对象并创建关联对象-> bean 标签  通过id获取其指向类的对象。
- spring 创建代理对象
- spring 在全局获得session  并用代理实现权限控制。

```java
//创建一般对象及其关联 对象
<bean id="IUserDao" class=".....IUserDao" >
<property name="id"  ref="UserDao"></property></bean>

//代理对象创建方法(因为以前是工厂获得代理对象)
<bean id="UserProxy" class=".....factory" factory-method="getProxyInstance">
<constructor-arg ref="UserDao"></bean>
//把UserDao换成UserProxy
<bean id="IUserDao" class=".....IUserDao" >
<property name="id"  ref="UserProxy"></property></bean>


```

```java
//全局获得request的session对象
import org.springframework
HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).kdhf；
HttpServlet httpsession=request.getSesion();

Uer u=(User)httpsession.getAttributes("user");
if(u==null){
  result=method.invoke(target,args);
}
//第一次调用 代理对象
String role=u.getRole();

```

### 等源码发下来在理一遍。

### 设计步骤
-
- 1.搭建环境：util包里面的东西。jar包 。 sessionFactory.java ....  。ApplicationContext.xml。 log4j。



### 异常捕获问题。
- 在web如何显示异常
- 把异常抛给tomcat服务器，
- exception.jsp
- 在web.xml里面配置error Page  把java。lang.Exception和jsp引入

```java
<h1>服务器出现异常 原因如下</h1>

<%=exception.getMessage() %>
```

### 控制器拦截样式表(请求、对字符处理)
- 新建EncodingFilte implement Filte ;
- 在web.xml里面配置filter。
- 拦截器就是对页面跳转的限制。

### session
- 如果dao操作得到的session不为空  session.serAttribute()放进去
-

### 完整全家
- 过滤器 字符 servlet
- 对异常统一处理 web 端
