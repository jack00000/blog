---
title: 无锡实训java_day4
date: 2018-7-6 20:13:10
tags:
categories: 无锡实训java
---


### 大致内容
- 所有新建的类(string类 int类 等)继承java.lang.Object  会拥有一些基础方法(eg:equals()  hashCode() 方法  区别对象)
- Student s=new Student(); Student s1=new Student(); s.hashCode()!=s1.hashCode() s!=s1(正真辨别真伪)
- 要使s=equals(s1)   Student类里面重写equal()
- 要使s.hashCode()=s1.hashCode() 重写hashCode()
- extends 不能接final修饰的类（特点：方法不能重写）
- 修饰常量 final static  （static  目的是只生成一份拷贝）
- 缺省的适配器模式

- 继承抽象类与继承一般父类区别（抽象类就是方法的精简版  具体完整功能由继承的类实现）
- 抽象类继承抽象类？允许有抽象方法  非抽象方法  把 继承抽象类扩展想一下就明白啦
- 非抽象类继承抽象类：不允许有抽象方法
- 目的：遗留方法等待实现

- 接口(定义规范用的) 与抽象类的区别：接口类里面只有方法名  比抽象类更精简。
- 一般类 extends 抽象类 implement 接口类  会发生什么？注意 抽象方法 非抽象方法 在不同类的存在情况。
- 在类中使用其他类常量调用(常量所在的包不同)：improt导入类  类.属性   String m=s.ip调用
- 另一种导入方法：improve static 包名.类.*  可直接  属性   eg：String m=ip;调用
- 接口(接口是一种规范)的多继承 ：一般类/抽象类 implement 接口1 ，接口2  

- DAO设计模式：模型层model  接口层IDAO（只有方法名）   实现层DAO(链接数据库实现接口层)  工厂层Factory(用来返回接口)
- 业务层IServiceDao(包含多个c/u/r/d) tool层(工具类)
- 接口类型可以指向子类对象   类比 父类指向子类   
- 何用？方便调用用子类方法。
- 原来如何调用(耦合度太高)vs 现在如何调用(用工厂实现 类比 继承与工厂模式 想 接口与工厂模式) 即通过接口调用实现类方法。
- List<Student> findStudentBySurname(Stringname); //list  容器存储多个对象 每个对象包含多个属性  。

- spring替代Factory层。hibernate代替实现层。
- 无参数的构造方法是默认的，但是如果你创造了一个带有参数的构造方法，那么无参的构造方法必须显式的写出来，否则会编译失败
- 无参构造方法是为了反射服务的：反射会调用无参构造方法 找到set get 方法 完成属性的调用。

- 代理模式(静态代理 动态代理 )
- 数据多啦用对象保存  对象多啦用 数组/集合保存  eg:List<Obeject>
- ArrayList(地址数组方式存储)  List(地址链表方式存储)
- ArrayList  模拟数据库
- 多个线程调用同一个对象 数据不安全问题  博客有写 站内搜。

- 集合的查询方法  ： while循环 ：迭代器(Iterator<Student>ite=list.iterator();)  for循环：for(Student s:list){}
- 泛型(规定容器里面放的东西)：List<Student> list=new ArrayList<Student>();
- 对象(包含数据)过来怎么排序  ?工具类 eg：StudentComprator   
- 深复制 浅复制 (复制list<>再排序  不要飘絮原来的list<>)
- Collections.sort(list_age,new StudentComparator())  对容器的对象排序
- 角色 权限 eg：addStudent() 拦截 可以在内部写逻辑 但不好
- 代理类解决 ：它和实现类同时实现同一个接口  eg:StudentDaoProxy 类  implement IStudentDao   在代理类中调用实现类方法之前 之后都可以设置限制。
- 让实现类成为它的关联对象  就可以改实现类的方法addStudent()

- Factory调用代理类 不是实现类

- 强异常try catch 弱异常 throws RuntimeException("权限不够")

### 要使s=equals(s1) Student类里面重写父类Object的equal() 方法
- 此时，s.equals(s1)=true;
```java
public boolean equals(Object obj){
  boolean flag=false;
  Student s=null;
  if(obj instanceof Student) s=(Student)obj;
  flag=this.id==s.getId()?true:false;
  return flag;
}
```
### 要使s.hashCode()=s1.hashCode() 重写hashCode()
```java
@Override
public int hashCode() {
	// TODO Auto-generated method stub
	return this.id;
}
```

### 对象(包含数据)过来怎么排序  ?工具类 eg：StudentComprator  
```java
public class StudentComprator implement Comparator<Student>{

}
```
### 静态代理从方法层进行拦截 最细致。
### 用代理类实现权限控制:
- 简而言之（内存模型图 解释很清晰）：将堆区实现类对象通过工厂赋给接口类对象，然后将这个接口类对象赋给代理类里面的定义的 接口类对象  此时 代理类对象中的 接口属性指向堆区实现类对象，则可以在代理类中操作实现类的方法。
- 总结一点，要实现一个类操控另一个类，想方设法实现关联（上面是间接关联） 直接在A类中的构造函数传入B类的对象并赋给类的B对象属性 则是直接关联。
- 思路：实现类 implement 接口 -> 代理类 implement 同一个接口->  让代理类关联接口类（实现类也行 但工厂模式Factory.getInstance()返回的是接口类对象 一般通过接口对象调用实现类方法 ）  从而通过代理类的 实现类对象引用访问接口类并对实现类的方法进行限制。
- 思考：在工厂中把实现类对象赋给接口对象的好处？

```java
public class StudentDaoProxy implements IStudentDao {

	private IStudentDao sd;

	public StudentDaoProxy(IStudentDao sd) {
		super();
		this.sd = sd;
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		//sd.getAllStudentOrderByAge(id);
		return null;
	}

	@Override
	public void addStudent(Student s) {
		// TODO Auto-generated method stub
		System.out.println("你在调用方法之前就可以判断你要的规则");
		User u=Application.getUser();
		if(u==null||!u.getRole().equals("admin"))
			throw new RuntimeException("权限不够");


		//if(1!=1) return;
		sd.addStudent(s);

		System.out.println("你在调用方法之后就可以判断你要的规则");

	}

	@Override
	public void delStudentById(Student s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStudent(Student s) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Student> findStudentBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findStudentByAges(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getALLStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAllStudentOrderByAge() {
		// TODO Auto-generated method stub
		return null;
	}

}
```
```java
public class StudentDao implements IStudentDao {

	private static List<Student> list=new ArrayList<Student>();
  //静态块模拟数据库
	static {
		list.add(new Student(1,"lisi",24));
		list.add(new Student(2,"lisi",22));
		list.add(new Student(3,"lisi",25));

	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void addStudent(Student s) {
		// TODO Auto-generated method stub

         list.add(s);
	}

	@Override
	public void delStudentById(Student s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStudent(Student s) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Student> findStudentBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Student> findStudentByAges(int start,int end){
		List<Student> list=new ArrayList<Student>();


		//list.su

		return list;
	}

	@Override
	public List<Student> getALLStudents() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public List<Student> getAllStudentOrderByAge() {
		// TODO Auto-generated method stub

		List<Student> list_age=new ArrayList<Student>();
		list_age.addAll(list);

		Collections.sort(list_age, new StudentComprator());

		return list_age;
	}
}
```
```java
public class Factory {

	public static  IStudentDao getStudentDaoInstance() {

		return new StudentDao();
	}
    public static IStudentDao getStudentDaoProxyInstance() {

    	return new StudentDaoProxy(Factory.getStudentDaoInstance());
    }
}
/*值得注意的是 在Factory类中return StudentDao 返回类型是接口
- 且 new StudentDaoProxy(Factory.getStudentDaoInstance()); 方法传入的对象是接口类对象  
- Factory返回的都是接口类对象(父类对象) 结合 继承+工厂模式理解。*/
```
```java
public static  IStudentDao getStudentDaoInstance() {

  return new StudentDao();
}
  public static IStudentDao getStudentDaoProxyInstance() {

    return new StudentDaoProxy(Factory.getStudentDaoInstance());
  }
```
```java
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//StudentDao sd=new StudentDao();
		IStudentDao id=Factory.getStudentDaoProxyInstance();

		id.addStudent(new Student(5,"liuer",60));
		//List<Student> list=id.findStudentByAges(10, 15);

		Scanner scan=new Scanner(System.in);

		//scan.
		//id.
    /*output:
    你在调用方法之前就可以判断你要的规则

    你在调用方法之后就可以判断你要的规则*/
	}
```

![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180706161601.png)



### 设计Usr类 实现登录 存储对象到Aplication  调用Student类的方法  设置相应权限  能调用方法的限制
- eg:Usr.role="admin" 则可以调用addStudent() 实现增加对象。
- 代码太多就不全贴啦：总之，记住：1.用工厂生产子类对象 2.静态方法可以这样电泳Application.setUser();/Factory.getInstance()
- 代理类通过关联实现类（这是目的，实际关联的是接口类） 从而对其方法进行限制  关联是间接关联(因为代理类实际关联的是接口类 )
- 完整项目放在 [github](https://github.com/jack00000/wuxi_train)

```java
package whgc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import whgc.DAO.StudentDao;
import whgc.Factory.Factory;
import whgc.IDAO.IStudentDao;
import whgc.IServiceDao.IRegisterDao;
import whgc.model.Student;
import whgc.model.User;
import whgc.util.Application;

public class Test {


	public static void main(String[] args) {

		//输出原来学生信息
		System.out.println("输出原来所有学生信息");
		IStudentDao iso=Factory.getStudentDaoInstance();
		List<Student>list=iso.getAllStudentOrderByAge();
		for(Student s:list){
			System.out.println("学生的年龄"+s.getAge()+"  "+"学生的姓名"+s.getName()+" "+"学生id"+s.getId());
		}
        System.out.println();

		System.out.println("请输入用户名和密码以及具有的权限");
		User u=new User();
		Scanner scan=new Scanner(System.in);
		String role=scan.next();
		String password=scan.next();
		String permision=scan.next();
		u.setRole(role);
		u.setPassword(password);
		u.setPremision(permision);

		//相当于把输入数据存在数据库(这里是放在一个类的属性user里面的)
		Application a=new Application();
		a.setUser(u);

		System.out.println("输出Application里的user"+Application.getUser());
		//获取登录接口实例，调用实现类方法
		IRegisterDao ir=Factory.getRegisterInstance();
		if(ir.Register()){
			Student s=new Student(4,"fangjun",21);
			IStudentDao iso1=Factory.getStudentDaoProxyInstance();
			iso1.addStudent(s);


            IStudentDao ios2=Factory.getStudentDaoInstance();
			List<Student>list2= ios2.getList();
			for(Student student:list2){
				System.out.println("学生的年龄"+student.getAge()+"  "+"学生的姓名"+student.getName()+" "+"学生id"+student.getId());
			}



		}else {
			System.out.println("登录失败");
		}

	}

}

/*output：
输出原来所有学生信息
学生的年龄22  学生的姓名lisi 学生id2
学生的年龄24  学生的姓名lisi 学生id1
学生的年龄25  学生的姓名lisi 学生id3

请输入用户名和密码以及具有的权限
admin
123456
add
输出Application里的userwhgc.model.User@1d44bcfa
输出Application的user的所有信息: admin  123456  add
登录成功
你拥有的权限是add
你可以对Student对象进行的操作add
学生的年龄24  学生的姓名lisi 学生id1
学生的年龄22  学生的姓名lisi 学生id2
学生的年龄25  学生的姓名lisi 学生id3
学生的年龄21  学生的姓名fangjun 学生id4

Process finished with exit code 0 */

```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180707110305.png)
