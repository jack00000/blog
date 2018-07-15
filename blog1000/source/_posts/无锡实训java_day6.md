---
title: 无锡实训java_day6
date: 2018-7-9 20:13:10
tags:
categories: 无锡实训java
---

## 大致内容
- 引入数据库
- 创建数据库和表
- 链接数据库
- 异常处理机制
- 什么是原子型操作
- 线程解决Servicedao 对原子型dao统一进行事务管理
- 为什么要用Servicedao  为什么在servicedao统一进行事务管理，为什么单个原子型dao要用代理类实现事务控制

- 注意：在lesson9向表user插入数据时
- 1.获得Connection对象conn=DriverManager.getConnection(url)
- 2.获得PreparedStatement 的对象 statement=conn.prepareStatement(sql);
- 3.statement.setString(1, userid);
		statement.setString(2, name);
		statement.setInt(3, age);
- 4.设置自动提交为false：conn.setAutoCommit(false);
- 5.执行更新：statement.executeUpdate();

### 问题来啦：在lesson10中老师加入session ，为什么加入session(事务)？
- session完成啦对connetion对象和statement对象的封装，方便调用。


### 创建数据库
- create database Student；

```java
create table users(
   id int(30) primary key,
   userid varchar(10) unique not null,
   usename varchar(20) not null,
   age int check(age>=0 and age<100),
   gj varchar(20)
);

INSERT INTO user ( id, userid,usename,age,gj )
                       VALUES
                       ( 1, '001','zhangsan',20,'china' );

select * from users;

create table log_student(
   id int(10) primary key,
   logname varchar(16) not null,
   logaction varchar(20) not null

);

insert into user (logname,logaction)values('zhangsan','log in');
select *from log_student;

```

![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180710105249.png)



### java链接数据库/事务/
- 基本类库
- 第三方jar
- 链接数据库
- 引入类可能发生异常，不要在main() throws exception

```java
Class.forName(com.mysql.jdbc.Driver);//引入jar 加载相关类库
Connection conn=  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Student?characterEncoding=YTF-8","root","linux");
System.out.println("链接成功")；

```
![](http://oxz3x2njl.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180710200352.png)

### 异常处理机制:强异常捕获，弱异常抛出。
- 强异常捕获，弱异常抛出。解决问题：调用方式 里面处理的异常你你看不到
- throws Expetion() //向下传递
- throw 生成一个炸弹抛出
- try catch   //处理
- 强异常：代码编译不通过：eg：Socket socket=new Socket("localhost",8080);
- 强异常：IOExpetion UnKnownHostExpetion  必须处理，不能传递
- 弱异常：代码编译可以通过 eg:int a=3/0;

- 异常栈：用内存模型图来理解。

- RuntimeExpetion
- exception 举例

```java
Socket socket=new Socket("localhost",8080);
Connection conn=  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Student?characterEncoding=YTF-8","root","linux");
System.out.println("链接成功")；

String sql="insert into users(uesrid,username,age)values(?,?,?)";

PreparedStatement statement=conn.PreparedStatement(sql);
statement.setString(1,uesrid);
statement.setString(2,usename);
statement.setInt(3,age);

statement.executeUpdate();
System.out.println("插入成功")；
```

### 重点： 事务机制：多条记录curd出错 保持数据不变/管理conn




### user表与log_student表在事务中有关联 修改表会在log_student表插入记录


## 实操

### 用工厂生成conn对像

### 实现类不是原子型操作 提交失败后回滚
- sesion是对connection对象和PreparedStatement对象的封装 ，方便调用。
- service是对多个原子型操作的封装。

```java
addUser(User u){
  Session session=SessionFactory.getSession();
  //Session session1=SessionFactory.getSession();
  session.beginTranction();
  String sql="insert into user(username)values)('')"
  PreparedStatement statement=session.getPreparedStatement(sql);
  try{
    statement.setString(1,user.getUserid());
    statement.setString(2,user.getUserid());
    statement.setString(3,user.getUserid());
  }catch{
    e.prinStackTrace();
    session.rollbackTransaction();//出错时回滚
  }finally{
    session.closeSession();//关闭事务
  }
}

rollbackTransaction(){
  try {
     conn.rollback();
   } catch (SQLException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();

   }
}

closeSession(){

if(state!=null)
  try {
    state.close();
  } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();

  }
if(conn!=null) {
  try {
    conn.close();
  } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();

  }

}
```

### 以上流程存在问题：对user的操作 和 堆log_student是分开的  他们有事务关联   ServiceDao解决此问题。

### ServiceDao  /IService ：按照业务分类原子型dao
- 对两个表进行操作

### 事务控制问题：两个dao 都是 同一个session  实现对两个表的操作 同时失败 同时成功。
- 解决方法：在线程中保存session

```java
class MyThread{
  Thread thread=Thread.currentThread();

  System.out.println(thread.getname() );
}

```

```java
class MyThread extends Thread{
  public void run() {
  		// TODO Auto-generated method stub
  		Thread t=Thread.currentThread();
  		System.out.println(t.getName());
  	}
}
```

```java
class SessionFactory{
  ...
  private static threadLocal<Session> threadLocal=new threadLocal<Session>();
  public static Session
     }
//
```

### 在原子dao不提交，不关闭  在servicedao统一进行提交(事务管理)  
- ServiceDao 对多个表进行操作  uesrdao对单个表进行操作
- 单独的dao都没有提交事务，所以在servicedao实现对事务的统一管理
- 要单独进行原子型操作，用代理类。
- 所以在原子dao不提交，不关闭  在servicedao统一进行提交(事务管理)
- 不写userDao事务控制，用代理对象实现以下方法
- 解决方法：session.commitTransaction()/rollbackTransaction()/closeSession() 写在代理对象。
- 因为如果你在usedao logstudentdao写事务的话，你写ServiceDAO的时候会造成影响，
- 比如，userdao处理事务退出啦 logstudentdao处理事务没退出。


- 问题举例

```java
//伪代码1：对user进行dao操作
int a=2/0;
//伪代码2：对logstudent进行dao操作
session.commitTransaction();

```


### 实操：登录并实现插入记录到logstudent  [完整源码](https://github.com/jack00000/wuxi_train)
- 目的：知道为什么要用Servicedao  为什么在servicedao统一进行事务管理，为什么单个原子型dao要用代理类实现事务控制
- 同时成功 同时失败

- 你要懂代理模式，session Service 原子型操作 线程 工厂模式 自定义异常 throw 异常
- 单例模式确保session是一致的。(老师实现的不是单利模式，是通过工厂判断 从而始终生产一个对象)
- 保证conn statement 只有一个

```java
class sessionFactory{
  public static Session getSession() {
		Session session=null;
		session=threadloacl.get();
		if(session==null) {
		session=new Session(SessionFactory.getConnection());
		threadloacl.set(session);
		}

}
```

### 目前已将两个表的commit操作放入线程并放在啦RegisterServicDao实现
- 目前原子型操作(要用代理类)没写
- 已完成 不报错时 同时提交  报错时 同时回滚。

```java
public void RegisterUser(User u, LogStudent logstu) {


       Thread t=new Thread(){
           @Override
           public void run() {
               int i=1;
               while (i<2){
                   Session sessionForUesr=SessionFactory.getSession();
                   Session sessionForLog=SessionFactory.getSession();
                   if(sessionForLog==sessionForUesr){
                       System.out.println("两者的session一样");
                   }else {
                       System.out.println("两者的session不一样");
                   }
                   try{
                       UserDao ud=UserDaoFactory.getUseDao();
                       sessionForUesr=ud.addUser(u);
                       System.out.println("提交user的信息");
                       sessionForUesr.commitTransaction();
                       System.out.println("users表插入成功");
                       System.out.println();

                       LogStudentDao lgstu1=LogStudentDaoFactory.getLogStudentDao();
                       sessionForLog=lgstu1.addLogStudent(logstu);
                       System.out.println("提交log的信息");
                       sessionForLog.commitTransaction();
                       System.out.println("log表插入成功");

                   }catch (Exception e){

                       sessionForLog.rollbackTransaction();
                       sessionForUesr.rollbackTransaction();
                       System.out.println("插入失败，已 回滚");

                   }

                   i+=1;
               }
           }
       };
       t.start();


   }
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180710225848.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711071336.png)
### 把数据改成一个错，一个对，运行结果。
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711072841.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711081319.png)

- 用代理类实现原子性操作（先放着）


### 上面的是用addUsers(User u)返回session对象进行操作  忽略啦session是单例模式。
- void addUser(User u)再做一遍[源码](https://github.com/jack00000/wuxi_train)

```java
try{


									 Session session=SessionFactory.getSession();
									 IUserDao user=UserDaoFactory.getUseDao();
									 ILogStudentDao log=LogStudentDaoFactory.getLogStudentDao();
									 user.addUser(u);
									 log.addLogStudent(logstu);
									 session.commitTransaction();
									 System.out.println("插入成功");

							 }catch (Exception e){

									 sessionForLog.rollbackTransaction();
									 sessionForUesr.rollbackTransaction();
									 System.out.println("插入失败，已 回滚");

							 }
```					
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711132556.png)





- 说白啦就是对一个表dao操作的叠加，理解下图  再以扩散思维理解 事务Service
![](http://oy5lsbw4v.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180710200352.png)


### 所有的事务控制不在Servicedao中，而由代理去实现。
