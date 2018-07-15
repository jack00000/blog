---
title: cmd操作mysql数据库
date: 2017-11-29 15：00
tags:   skills java
categories: skills
---

<h3>完整教程：[how2j](http://how2j.cn/k/mysql/mysql-innodb/1064.html)
<h3>1.在mysql安装目录bin下打开cmd  shift+右键</h3>
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-18_104324.png)

<h3>2.具体操作</h3>
- 登陆
- - mysql -u root -p
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-18_092846.png)
- 新建数据库 新建表
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-18_092910.png)
- 新建具体表项  select* from table;//查询表项
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_094412.png)
- 修改密码
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_100700.png)
- 导入数据库 .sql文件 先建数据库 然后 use 数据库名 ； source 路径（不需要；）
路径最好是某盘的下面，不然会出稀奇古怪的错误
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-28_234633.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-31_142930.png)

- 多表查询
- inner join /left join 区别
### 给表添加外键 alter table card add constraint constraint_card foreign key(stuid) references student(stuid);
- 这句sql语句意思是：在card表中添加名为dormitoryid的外键约束。
### 删除表中的外键 alter table card  drop foreign key constraint_card;
- 删除card表中的名为constraint_card的外键(表中的外键命名采用constraint_外键表名)
### 外键不删除，不能删除表

### 删除表中所有数据 delete from tablename;

### 删除带有外键约束的表中的数据
- SET FOREIGN_KEY_CHECKS=0；
- delete from tablename;
- SET FOREIGN_KEY_CHECKS=1;
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711191238.png)

### 向有约束的表插入数据是有限制的 在无锡实训7有介绍。
- 一对多关系 如：宿舍-学生  肯定先有宿舍信息再有学生。
- 先插宿舍，再插学生。
```java
create table dormitory(
dormitoryid varchar(10) primary key,
drcnitorytype varchar(10) not null

);

create table student(
stuid varchar(20) primary key,
stuname varchar(20) not null,
stuage int not null,
dormitoryid varchar(10)
);

create table card(
cardid varchar(10) primary key,
cardtype varchar(10) not null,
money  int default 0,
stuid varchar(20)
);
//在card表声明一个student外键
alter table card add constraint constraint_card foreign key(stuid) references student(stuid);
//在student表中声明一个dormitory外键 叫constraint_student （中文：约束学生）
alter table student add constraint constraint_student foreign key(dormitoryid) references
 dormitory(dormitoryid);

```
### 插入数据
- insert into dormitory values('A001','4persons');
- insert into student values('S001','wangshicong',28,'A001');//相当于把Student放在A001房间。
- insert into card values('C001','ic_card',800,'S001');//相当于把卡放在学生S001中。
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711194842.png)

### 多表查询
- 将三张表等值连接后查询
```java
select a.*,b.*,c.*from dormitory a inner join student b on a.dormitoryid=b.dormitoryid inner join card c on b.stuid=c.stuid;
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711195905.png)
- left join与inner join与right join
- 好奇怪：都一样  难道在一对多中无区别？
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711200304.png)

### 多对多关系的表 如何创建约束 如何进行多表查询
- 多对多关系：subject-student//student就用上面创的，注意：里面有个外键约束。
- 通过业务表SelectSubject建立约束
```java
create table student(
stuid varchar(20) primary key,
stuname varchar(20) not null,
stuage int not null,
dormitoryid varchar(10)
);

create table subject(
subjectid varchar(20) primary key,
subjectname varchar(20) not null,
subjecttype varchar(10) not null

);

create table SelectSubject(
id int(5) primary key,
subjectid varchar(10) ,
stuid varchar(20)

);
//建立外键约束
alter table SelectSubject add constraint constraint_fk1 foreign key(subjectid) references subject(subjectid);
alter table SelectSubject add constraint constraint_fk2 foreign key(stuid) references student(stuid);
```
### 插入数据 先插student 再插subject 最后插SelectSuject
- insert into dormitory values('A002','6persons');
- insert student values('S002','wangjinglin',35,'A002');
- insert subject values('SB001','math','like');
- insert into SelectSubject values(1,'SB001','S001');

### 多表查询-多对多关系表
- 三张表left链接后查询
```java
select a.*,b.*,c.*from subject a left join SelectSubject b on a.subjectid=b.subjectid left join student c on b.stuid=c.stuid;
```
- 一条记录 left right inner  无差别  多条记录有差别
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711202854.png)
