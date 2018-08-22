---
title: spring
date: 2018-1-20 20:13:10
tags:
categories: how2j
---

<h3>完整教程：[how2j](http://how2j.cn/k/spring/spring-ioc-di/87.html#nowhere)</h3>

<h3>备忘</h3>

- IOC 反转控制 是Spring的基础，Inversion Of Control .简单说就是创建对象由以前的程序员自己new 构造方法来调用，变成了交由Spring创建对象
- Spring容器作为超级大工厂，负责创建、管理所有的Java对象，这些Java对象被称为Bean。

- DI 依赖注入 Dependency Inject. 简单地说就是拿到的对象的属性，已经被注入好相关值了，直接使用即可。
- Spring容器管理容器中Bean之间的依赖关系，Spring使用一种被称为"依赖注入"的方式来管理Bean之间的依赖关系。

- spring给对象注入属性
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-26_191800.png)

- spring给对象注入其他对象
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-26_193305.png)

- spring给对象注入其他对象  注解方式
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-26_194235.png)

- 对Bean全部用注解表示
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-26_195052.png)

- AOP 即 Aspect Oriented Program 面向切面编程 。
- 面向切面编程的思想里面，把功能分为核心业务功能，和周边功能。
- 核心业务，比如登陆，增加数据，删除数据都叫核心业务
- 周边功能，比如性能统计，日志，事务管理等等
- 周边功能在Spring的面向切面编程AOP思想里，即被定义为切面
- 面向切面编程AOP的思想里面，核心业务功能和切面功能分别独立进行开发 ，然后把切面功能和核心业务功能 "编织" 在一起，这就叫AOP。
