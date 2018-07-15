---
title: 无锡实训java_day7
date: 2018-7-10 20:13:10
tags:
categories: 无锡实训java
---

## 大致内容
- 数据库的表之间的关系 一对多  一对一
- 表之间存在约束时 插入 删除是有顺序的。
- 宿舍-学生-卡  说明 一对多 ：一个宿舍多名学生，一个学生多张卡。
- 接下来实现多对多，需要三张表subject表/student表/##electSubject业务表。
- 多表查询语句/删除约束/新建约束
- drop dormitory是会报错   Student的外键正在引用
- 为减少耦合度   在表外add dormitory 为 Student外键。
- mysql -cmd  事务管理

```java
select a.*,b.*，c.* from student a left join SelectSubject  b on a.stuid=b.stuid
left  jion subject c on c.subjectid=b.subjectid where
begin tran
insert into dormitory values('A1003','4 persons');
rollback(出错时)/commit(正确时)

```

### 多表查询
- inner join /left join 区别

### 给表添加外键
- alter table card add constraint constraint_card foreign key(stuid) references student(stuid);
- 这句sql语句意思是：在card表中添加名为dormitoryid的外键约束。

### 删除表中的外键
- alter table card  drop foreign key constraint_card;
- 删除card表中的名为constraint_card的外键(表中的外键命名采用constraint_外键表名)

### 外键不删除，不能删除表

### 删除表中所有数据
- delete from tablename;

### 删除带有外键约束的表中的数据
- SET FOREIGN_KEY_CHECKS=0；
- delete from tablename;
- SET FOREIGN_KEY_CHECKS=1;

![](http://oxz3x2njl.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711191238.png)

### 向有约束的表插入数据是有限制的
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


### java端设计类 对应表 实现多表查询和数据存放
- 一对多关系的表的查询 取得的数据 如何存放
- 多对多关系的表查询 取得的数据 如何存放
- 打印输出

### 类的属性设计

```java
class dormitory{
  private String dormitryid;
	private String dromitrytype;
	private Set<Student> stus=new HashSet<Student>();
}

class student{
  private String stuid;
	private String stuname;
	private int age;

  private Dormitry dorm;
	private Card card;
	private Set<Subject> subjects =new HashSet<Subject>();
	private Set<Card> card =new HashSet<Card>();
}
class card{
  private  String cardid;
	private String cardtype;
	private int money;
}
```
### 在dao中实现把student 数据放在dormitory对象的Set<Student>容器中   并返回dormitory对象
- 简而言之，将三张表链接后查询得到的结果statement.executeQuery()指向resultSet
- 注意：resultSet类似游标的东西，并不存储 数据。

```java
public Dormitry getDormitryById(String dormid) {
		// TODO Auto-generated method stub
		Dormitry dorm=null;
		 Session session=null;
		 session=SessionFactory.getSession();
		  session.beginTransaction();//begin tran
		  // insert update delete
		  //select count(*)  from table
		  String sql="select * from dormitry where dormitryid=?";
		  StringBuffer buffer=new StringBuffer();
		  buffer.append("select a.* ,b.* from dormitry a left join student b ");
		  buffer.append("on a.dormitryid=b.dormitryid where a.dormitryid=?");

			java.sql.PreparedStatement statement=session.getPreparedStatement(buffer.toString());

			try {
				statement.setString(1, dormid);
				java.sql.ResultSet resultSet=statement.executeQuery();
				boolean flag=true;
				 Set<Student> stus=null;
				while(resultSet.next()) {

					if(flag)
						{
						dorm=new Dormitry(resultSet.getString(1),resultSet.getString(2));
						stus=dorm.getStus();
						}
					 stus.add(new Student(resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5)));                     
           dorm.setStus(stus);
					flag=false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new org.whgc.util.MySqlException("sql异常 请和管理员联系"+e.getMessage());
			}
		 // java.sql.ResultSet result=session.getResultSet(sql)
		return dorm;
	}
```

### 注意：这个时候取出来的数据有dormitory的对象的所有属性值和Set<Student>  
- [完整源码](https://github.com/jack00000/wuxi_train)
- 因为是把dormitory和Student表链接后查询.
- ResultSet所指向的数据有这些字段.
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180712065910.png)

### Set<Student>只有Student对象 没有其他数据.
- 不能通过student.getDorm().getDormitryid()得到dormotory对象  有方法，但student的属性dormit对象是null.

![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180711221145.png)

### 问题来啦？把dormitory，student,card链接起来，查询数据sql太长怎么写？。
- java端sql语句写的方式.
- 每个字符串后面记得空一格 不然你的sql语句就有错误啦。

```java
StringBuffer buffer=new StringBuffer();
     buffer.append("select a.* ,b.*,c.* from dormitory a left join student b ");
     buffer.append("on a.dormitoryid=b.dormitoryid inner join card c on b.stuid=c.stuid ");
     buffer.append("where a.dormitoryid=?");
```     
- 查询到的数据字段有如下表

![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180712072255.png)

### 目前只是把resultset的前2个字段赋给相应的对象dormitory 后三个字段赋给Student对象再放入Set<Student>容器。
- 当我把第6个字段赋给Student对象报错？因为表有dormitoryid属性 ，类中没有

![](http://oxz3x2njl.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180712073205.png)

### 怎么把ResultSet指向的数据集合的cardid cardtype money字段取出来放在dormitory的属性Set<Student>容器的Student对象的属性Set<Card>容器中
- [完整源码](https://github.com/jack00000/wuxi_train/tree/master/duoBiaoChaXun_dormitory2student2card_one2duo2duo)

```java
class DormitoryDao{
  statement.setString(1, dormid);
  				java.sql.ResultSet resultSet=statement.executeQuery();
  				boolean flag=true;
  				 Set<Student> stus=null;
  				 Set<Card> scard=null;
  				while(resultSet.next()) {

  					if(flag)
  						{
  							//把dormitory属性赋给dormitory对象
  						dorm=new Dormitory(resultSet.getString(1),resultSet.getString(2));
  						//获得dormitory对象属性Set<Student> stu容器
  						stus=dorm.getStus();
  						}
  						//向stu容器添加Student对象属性
  					 stus.add(new Student(resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5)));
                       //用迭代器取出每一个Student对象并向其属性Set<Card> scard容器添加card的字段信息
  					Iterator<Student> iterator=stus.iterator();
  					while (iterator.hasNext()){
  						Student student=iterator.next();
  						scard=student.getScard();

  						scard.add(new Card(resultSet.getString(7),resultSet.getString(8),resultSet.getInt(9)));
  						student.setScard(scard);
  						//把Student对象放入dormitory对象的属性Set<Student>容器中
  						dorm.getStus().add(student);
  					}

  					dorm.setStus(stus);
  					flag=false;
  				}
}


class Test{
  main(){
    IDormitryDao iDormitryDao=Factory.getDormitryDaoInstance();
		Dormitory dormitory =iDormitryDao.getDormitryById("A001");
		System.out.println("寝室id"+dormitory.getDormitryid()+"寝室类型"+dormitory.getDromitrytype());
		Set<Student> stu= dormitory.getStus();

		Iterator<Student> iterator=stu.iterator();
		while (iterator.hasNext()){

			Student student=iterator.next();
			System.out.println("寝室"+dormitory.getDormitryid()+"的"+"学生id"+student.getStuid() +"学生名字"+student.getStuname()+"学生年龄"+student.getAge());
			//获得该学生对象的Set<Card>容器
			Set<Card> scard=student.getScard();
			Iterator<Card> iterator1=scard.iterator();
			while (iterator1.hasNext()){

				Card card=iterator1.next();
				System.out.println("学生"+student.getStuname()+"的"+"卡id"+card.getCardid()+"卡类型"+card.getCardtype()+"卡的钱"+card.getMoney());
			}
		}
	}
  }
}
```

![](http://oxz3x2njl.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180712204457.png)        

### 目前已完成：java端对 dormitor(一对多) student(一对多)card 表的多表查询

-  返回一个寝室的信息
- 该寝室的所有学生信息
- 该寝室所有学生的所有卡的信息

### 现在完成：subject(多对多)student表 的多表查询

- 数据库的表结构

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

### 终端多表查询

```txt
select a.*,b.* ,c.*from subject a inner join SelectSubject b on a.subjectid=b.subjectid inner join student c on b.stuid=c.stuid where a.subjectid="SB001";
```
- 查询结果
![](http://oxz3x2njl.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180712210510.png)

###  java端把数据取出来并存在相应容器Set<Subject>,Set<Student> 和属性中

- 说白啦，就是把一对多 反向做一次
- eg:设计SubjectDao弄到subject对象基本属性和容器属性，查subjectid得到全部。
- 设计StudentDao弄到subject对象基本属性和容器属性，查stuid得到全部。

![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180712223447.png)



### sql :insert update delete  事务型操作

- select count(*) from table;
- select count(*) as nums from table;//给取得数据起别名。
- select 返回一行（id是主键） 返回多行
- state.executeQuery()//不是executeUpdate()
- ResultSet 指针  没有数据对象  指向多表查询的数据。
- white（ResultSet.next）
- ResultSet.getString(3) 返回 查到的数据 第三列的东西








### 工厂模式 代理模式 写多表数据查询。
