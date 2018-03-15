---
title: JDBC
date: 2017-11-28 15：00
tags:
categories: how2j
---
<h3>完整教程：[how2j](http://how2j.cn/k/jdbc/jdbc-statement/387.html#nowhere)</h3>

<h3>备忘</h3>
- 建立与数据库的连接
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_181004.png)

- Statement是用于执行SQL语句的，比如增加，删除(statement有三个包有，mysql才是对的)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_183703.png)
- 增删 改
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_184614.png)

- 查询
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_191530.png)

- PreparedStatement:PreparedStatement有预编译机制，性能比Statement更快  insert不用拼接

![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_194141.png)

- 事务：有事务的前提下 在事务中的多个操作，要么都成功，要么都失败
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_200621.png)

- ORM:Object Relationship Database Mapping 对象和关系数据库的映射 简单说，一个对象，对应数据库里的一条记录

- DAO:DataAccess Object   数据库访问对象
hero类
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_203532.png)
heroDAO类
