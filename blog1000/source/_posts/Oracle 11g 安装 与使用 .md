---
title: Oracle 11g 安装 与使用
date: 2018-4-27 15：00
tags:
categories: skills
---

## [下载地址](http://www.oracle.com/technetwork/database/enterprise-edition/downloads/112010-win64soft-094461.html?ssSourceSiteId=otncn)   [服务器安装](https://jingyan.baidu.com/article/363872eccfb9266e4aa16f5d.html) [客户端安装](https://jingyan.baidu.com/article/14bd256e6fc34fbb6c26127e.html)

## 备忘

### 注意 下载两个文件 然后一起解压 -》得到安装文件   客户端也在里面   非GUI界面。。。
### 不同于mysql  oracle 有三个控制   OEM /SQL PLUS/SQL DEVELOPER
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-17_151125.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-17_151913.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-17_160941.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-17_160547.png)

### 使用OEM 企业管理器  在游览器打开 [网址](https://localhost:1158/em/console/logon/logon)  输入默认用户名 system 与密码
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-17_165742.png)
### 使用sql plus==Mysql命令行
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-17_171314.png)

### 使用 PL/SQL DEVELOPER与Mysql-fontGUI程序使用差别太大
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_102525.png)
#### 1.新建数据库   （多个表空间组成数据库 一次只能连接一个数据库  切换 点 file new instance）
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_120939.png)
#### 2.建表空间 建表  打开sql windows、
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-07_163957.png)

- 新建表空间
```Text
create tablespace booksales datafile '/opt/oracle/db01/app/oracle/oradata/OSSORCL/booksales.dbf'
size 10M
autoextend on next 100M
maxsize 500M
logging online permanent;  --一直在线
```

- 向表空间添加数据文件
```Text
alter tablespace booksales add datafile '/opt/oracle/db01/app/oracle/oradata/OSSORCL/venn02.dbf'
size 10M
autoextend on next 100M
maxsize 500M ;
```
- 删除表空间及数据文件
```Text
drop tablespace venn including contents and datafiles;
```
- 建表并指定表空间
```Text
create table customers(
  id number primary key,
  name varchar2(30) not null,
  phone number not null,
  email varchar2(30),
  address varchar2(30),
  code varchar2(30)

) tablespace booksales;

create table publishers(
  id number primary key,
  name varchar2(30),
  phone number,
  contaxt varchar2(30)

) tablespace booksales;

create table books(
  id number primary key,
  title varchar2(30),
  cost number,
  retail number

) tablespace booksales;

create table orders(
  order_id number primary key,
  custom_id number primary key,
  orderdate varchar2(30),
  shipdata varchar2(30)

) tablespace booksales;

create table orderitem(
  order_id number primary key,
  item_id number,
  ISBN varchar2(30)
) tablespace booksales;

create table promotion(
  gift_id number primary key,
  name varchar2(30),
  minretail number,
  maxretail number

) tablespace booksales;


```
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_135552.png)

- 查询表空间的表  select table_name ,tablespace_name from dba_tables where tablespace_name= 'BOOKSALES'
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_165551.png)

#### 3.CURD
- 插入
```Text
insert into customers (id,name,phone,email,address,code) values(1,'方俊',450,200,45,3,300);
commit;
```
- 删除
```Text
delete from customers where id = 1
```
- 修改
```Text
update customers set phone = 1112323246 where id = 1;
```
- 查询
```TEXT
 select * from customers
```
- 创建视图
```text
create view herosimple as (
    select id, name from hero
)
select * from herosimple;
```

- 关联查询
```Text
select e.first_name,d.department_name from hr.employees e
left join hr.departments d
on e.department_id = d.department_id
```

#### 4.插入数据
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_171258.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_170511.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_170846.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_170625.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_170309.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_171118.png)

#### 5.创建视图
- eg：创建一个视图customer_gift   获得 name ISBN custom_id
```Text
create view customer_gift as(
     select name,ISBN,custom_id from customers cross join orderitem
  )
select * from customer_gift;
```
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-18_173756.png)


####  6.创建角色并为角色授权  有三种角色  connect resouse dba
##### 创建用户 并赋予管理员等权限
- create user test identified by test;
- grant connect, resource to test;
- revoke connect, resource from test;
##### 创建角色并赋予查找customers表的权限
- create role fang;
- grant select on customes to fang;
- drop role fang;
