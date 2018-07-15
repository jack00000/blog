---
title: shiro安全框架学习
date: 2018-6-10 20:13:10
tags:
---

## [完整教程how2j](http://how2j.cn/k/shiro/shiro-plan/1732.html)

## 备忘

### 1.权限系统设计基础RBAC : Role-Based Access Control，基于角色的访问控制/Resource-Based Access Control，基于资源的访问控制
### 2.表的设计
- 基于 ORAC 概念， 就会存在3 张基础表： 用户，角色，权限， 以及 2 张中间表来建立 用户与角色的多对多关系，角色与权限的多对多关系
```mysql
DROP DATABASE IF EXISTS shiro;
CREATE DATABASE shiro DEFAULT CHARACTER SET utf8;
USE shiro;

drop table if exists user;
drop table if exists role;
drop table if exists permission;
drop table if exists user_role;
drop table if exists role_permission;

create table user (
  id bigint auto_increment,
  name varchar(100),
  password varchar(100),
  constraint pk_users primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table role (
  id bigint auto_increment,
  name varchar(100),
  constraint pk_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table permission (
  id bigint auto_increment,
  name varchar(100),
  constraint pk_permissions primary key(id)
) charset=utf8 ENGINE=InnoDB;

create table user_role (
  uid bigint,
  rid bigint,
  constraint pk_users_roles primary key(uid, rid)
) charset=utf8 ENGINE=InnoDB;

create table role_permission (
  rid bigint,
  pid bigint,
  constraint pk_roles_permissions primary key(rid, pid)
) charset=utf8 ENGINE=InnoDB;
```
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-08_214017.png)

## 实操
#### 1.md5加密    123变成202CB962AC59075B964B07152D234B70
```java
String encodePassword=new Md5Hash(password).oString();
```
#### 2.加盐后= 多次md5（password+随机数（盐））123变成。。。。。。。。。。。。
```java
String password = "123";
String salt = new SecureRandomNumberGenerator().nextBytes().toString();//生成随机数
int times = 2;
String algorithmName = "md5";
String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();//算法名 密码 盐 运算次数
```

### 3.简单的登录验证

- 建表 用户 角色 权限 角色-权限 用户-角色
- DAO列出用户 列出该用户所有角色 列出该用户所用权限
- 得到外界输入的用户名 密码 传入Realm 验证并授权
- Realm通过配置文件定位 方法类
- 该方法类通过dao操作 在权限表 insert添加数据 权限名 （数据库用户授权表面上不同）

![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-10_161726.png)

### 4.验证登录+盐 [idea导入eclipse项目 ](http://how2j.cn/k/idea/idea-eclipse-web-project/1576.html)
- 若导入后 发现源文件目录没有。回到工程目录通过生成的pom.xml导入项目就行
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-11_114917.png)
- 在用户表添加属性 alter table user add (salt varchar(100) )
- 我们肯定是对 注册填入的密码加盐，将加盐后的密码存入usr表属性password中，看运算的结果是否相等。
- 这样一来，数据没有明文密码。提高啦安全性。
![](http://oy5lsbw4v.bkt.clouddn.com/2018-06-10_194628.png)



### 5.网页端

### 6.权限管理一套
