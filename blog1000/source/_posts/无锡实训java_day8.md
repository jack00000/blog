---
title: 无锡实训java_day8
date: 2018-7-11 20:13:10
tags:
categories: 无锡实训java
---

## 大致内容
- 如何让工厂模式同时创建其关联模式实现通用
- 解决创建一个对象时 把其关联对象也创建好。
- 代理对象解决事务管理
- 权限的控制
- 自定义注解
- 反射机制
- 两个文件 ：factory.Properties和ApplicationContext.xml。
- 通用：不出现任何类  通过字符串  在堆区的大Class对象找到方法，并调用。
- 工厂层 事务 代理 全部动态即通用设计。
- 动态工厂(动态生产实现类对象和代理类对象赋给实现类接口)
- 通过MyInnvactionHandler类 根据注解 选择不同处理方式handler，生成代理类对象时引入处理方式。
- 返回类型Object，需要具体类型自己换 eg:(IRegisterServiceDao)Factory.getProxyInstance("RegisterServiceDao");


### 反射
- 封装类的结构信息
- Student s=new Student();
- 1  硬盘-静态方法区
- 2  堆区创建封装啦这个类的信息的对象  继承信息 父类方法   大Class对象

### 3种得到大Class对象的方法
- Class c1=Student.class;//获得大Class对象
- Class c2=s.getClass();
- Class c3=Class.forName("com.whgc.model.Student")//最通用，不用创建对象

### 大Class封装啦所有信息(类的信息的对象  继承信息 父类方法  注释方法 只要是这个类improt的其他类 在大Class对象中都可以找到)
- Field(成员变量)  Constructor(构造方法)  Method(成员方法)

```java
PrivilegeAnnotation p=	m.getDeclaredAnnotation(PrivilegeAnnotation.class);
```        

```java
Class c3=Class.forName("com.whgc.model.Student")；
Method[] methods=c3.
```




### 大Class对象+两个文件 factory.properties  实现通用工厂



### 自定义注释(通过反射实现：字符串在大Class里面找)： 通过方法提供属性
```txt
- 注释在引用类的表现方式：类当方法用，方法当属性引用
- 默认注解
- @Retention(RetentionPolicy.RUNTIME)
- @Target(ElementType.METHOD)
```
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PrivilegeAnnotation {
         boolean isValidate();
         String[] roles();
}
```

```java
public class StudentDao implements IStudentTDao {
    @Override

    //类当方法用，方法当属性引用
    @PrivilegeAnnotation(isValidate=true,roles = {"admin","teacher"})
    public void addStudent() {
      System.out.println("addStudent");
    }
```

### java框架：权限的控制 (反射机制 实现权限管理) 事务控制session

### ApplicationContext.xml
- 类似factory.prppretise  用于根据id创建对象。
- 区别：

```java
<bean id="userdao" class="org.whgc.UserDao"></bean>
//属性所关联的对象也搞啦
<bean id="registerDao" class="org.whgc.RegisterDao"></bean>
<property ></property>
```




### 用配置文件实现通用方法(动态代理)  事务控制，权限控制
- 工厂层 事务 代理 全部动态即通用设计。





## 实操
### 反射机制 [完整源码](https://github.com/jack00000/wuxi_train)
- 根本是通过字符串拼接在堆区的大class对象找到所需要的方法。
- 获取大class对象有三种方法
- newInstance()创建大Class对象实例
- getDeclaredMethods()获取其方法
- invoke()调用其方法
- 全程没有创建一个对象，却完成啦创建对象可以完成的所有操作，更改Class.forName()就可已完成对一个类对象的操作。达到通用的目的。

```java
/*
 * 反射:主要作用于访问一个类的私有字段和私有方法
 * 使用流程1(已知类的字段以及方法):
 *         A:获取对象Class
 *         B:实例化对象
 *         C:根据字段名反射私有字段  class.getDeclaredField(字段名)
 *         D:打开允许访问的开关 setAccessible(true);
 *         E:赋值(set): name.set(dog, "二哈");  访问(get):    name.get(dog)
 *         F:根据方法名反射私有方法cls.getDeclaredMethod("eat", String.class, String.class);
 *         G:打开开关
 *         H:访问私有方法  m2.invoke(dog, "阿拉斯加", "全家桶");
 * 使用流程2(不知道类的字段及方法):
 *         A:获取对象Class
 *         B:根据Class反射所有的字段名称以及类型(循环Field[]得出字段名以及字段类型)
 *         C:根据Class反射构造方法
 *         D:根据构造方法创建对象(object类型)
 *         E:根据B中得到的字段名称和类型分别定义Field并打开开关然后给私有字段赋值 Field name = cls.getDeclaredField("name");
 *         F:访问私有字段
 *         G:反射私有方法(也可以反射Method[]数组 循环得出方法名等,类似反射字段数组)
 *         H:打开开关并访问私有方法
 */
 class Test{
   main(){
     Class c1=Student.class;//获得大Class对象
       Student s=new Student(1,"王五");
       Class c2=s.getClass();
       try {
           Class c3=Class.forName("com.whgc.model.Student");
           //大Class可以创建对象
           try {
               Object o=c3.newInstance();
           } catch (InstantiationException e) {
               e.printStackTrace();
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           }
           //获取其中的method信息
           Method[] methods=c3.getMethods();
           for(Method m1:methods){
               System.out.println(m1.getName());
           }
           //获取其中的成员变量信息
           Field[] fields=c3.getFields();
           for(Field field:fields){
               System.out.println(field.getName());
           }

           //有趣的例子
           //id-----对象   StudentDao 属性cd ----CardDao  文件实现关联
           //文件配置  id=20
           s.setId(20);//不通用

           //通用方法
           //没有出现类
           //依赖注入
           //在堆区中，使用大Class对象通过属性得到方法并运行d
           //特点没有新建类 所有类都可以用
           //换类 改Class.forName("com..........")

           //通过字符串实现方法自动运行
           String fieldname1="id";//在大Class对象通过id找到get set方法 并运行
           String methodname1="set"+fieldname1.substring(0,1).toUpperCase()+fieldname1.substring(1);

           System.out.println(methodname1);
           Method m2=null;
           for(Method m1:methods){
               if(m1.getName().equals(methodname1)){
                   m2=m1;
                   break;
               }
           }

       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }

   }
   /*
     output:
           Picked up _JAVA_OPTIONS:   -Dawt.useSystemAAFontSettings=gasp
           java.lang.InstantiationException: com.whgc.model.Student
           setId
           at java.lang.Class.newInstance(Class.java:427)
           getName
           setName
           at com.whgc.test.Test.main(Test.java:19)
           getId
           Caused by: java.lang.NoSuchMethodException: com.whgc.model.Student.<init>()
           wait
           wait
           at java.lang.Class.getConstructor0(Class.java:3082)
           wait
           equals
           at java.lang.Class.newInstance(Class.java:412)
           toString
           ... 1 more
           hashCode
           getClass
           notify
           notifyAll
           setId

           Process finished with exit code 0
   */
```  

### 用反射实现通用工厂方法(需配合配置文件)
- 需要配置文件factory.proprieties(就是ssm中的ApplicationContext.xml)。
- 通过静态块把配置文件key value 传给 Properties对象 props
- 在factory.proprieties中key可以随便写，为什么？
- 传入字符串通过key获得value（类字符串）用于创建对象
- 通过key获得value（类字符串）用于创建对象,创建实现类dao对象并返回，全程没有出现某个具体类的创建，实现啦通用工厂。
```java
class Factory{
  //....
  void getInstance(String name){
    String value=props.getProperty(name);
    object=Class.forName(value).newInstance();
  }   
}
```  


```java
class Factory{
  //文件+键值实现通用工厂
    private static Properties props=new Properties();
    static {
        ResourceBundle rb=ResourceBundle.getBundle("factory");

        Enumeration enumeration=rb.getKeys();//类似迭代器
        while (enumeration.hasMoreElements()){
            String key=enumeration.nextElement().toString();
            String value=rb.getString(key);
            props.setProperty(key,value);
        }
    }
    public static Object getInstance(String name){
        Object object=null;

        String value=props.getProperty(name);
        System.out.println(props);
        System.out.println(name);
        try {
            object=Class.forName(value).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }
}

class Test1{
  main(){
		IStudentDao id=(IStudentDao)Factory.getInstance("studentdao");
		id.addStudent();
  }
}
/*
   factory.proprieties:
   studentdao=org.whgc.dao.StudentDao
   userdao=org.whgc.dao.UserDao

*/

/* output：
        Picked up _JAVA_OPTIONS:   -Dawt.useSystemAAFontSettings=gasp
       {studentdao=com.whgc.dao.StudentDao}
       studentdao
       addStudent

       Process finished with exit code 0
*/

```  

### 如何自定义注释 使用注释 注释何用 反射调用方法时如何加载注释。
- 注解与其他类在形式上不同
- 注解类上的默认注解是啥？
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PrivilegeAnnotation {
	boolean  isValidate();
	String[] roles();
}
```
- 如何在其他类方法中使用注解
- 注解类引入时当方法用，方法当属性用。
```java
class StudentDao{
  @Override
	@PrivilegeAnnotation(isValidate=true,roles= {"admin","teacher"})
	public void addStudent() {
		// TODO Auto-generated method stub
		System.out.println("addStudent");
	}
}
```
- 注解何用：权限控制 事务控制用session(封装啦Connection对象和statement对象)
- 反射调用方法时如何加载注释 实现权限控制。
- 加载注释，得到roles进行判断。

```java
class Test{
  main(){
     Object o = null;
     Class c = forName("com.whgc.dao.StudentDao");
     o=c.newInstance();
    Method[] methods = c.getDeclaredMethods();
                for (Method m1 : methods) {
                    //找有没有带注释的方法
                    PrivilegeAnnotation p = m1.getDeclaredAnnotation(PrivilegeAnnotation.class);
                    if (p != null) {
                        System.out.println(p);
                    } else {
                        try {
                            m1.invoke(o, null);
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        m1.invoke(o,null);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
  }
}

/*  output:
Picked up _JAVA_OPTIONS:   -Dawt.useSystemAAFontSettings=gasp
@com.whgc.util.PrivilegeAnnotation(isValidate=true, roles=[admin, teacher])
addStudent

Process finished with exit code 0*/
```

### 用反射实现通用代理 如何自定义异常 使用异常
- 如何自定义异常
- 使用自定义异常

```java
public class MySqlException extends RuntimeException {
	//private String message;

	public MySqlException(String message) {
		super(message);
		//this.message = message;
	}

}

throw newMyException("error");
```
### 解释一波ApplicationContext.xml和factory.properties的区别。
- factory文件只能通过实现类字符串找到其实现类。
- ApplicationContext文件能通过实现类字符串找到其实现类并找到与其关联的类。
- ApplicationContext文件要完成他的工作必须导第三方jar包。

```java
factory.properties：
studentdao=org.whgc.dao.StudentDao
userdao=org.whgc.dao.UserDao

ApplicationContext.xml：
<Beans>
<bean id="userdao" class="org.whgc.dao.UserDao"></bean>

<bean id="registerdao" class="org.whgc.servicedao.RegisterServiceDao">
//<property name="iud" ref="userdao"></property>
<property name="U" ref="userdao"></property>
</bean>
</Beans>

```

### ApplicationContext.xml的使用与factory.properties不同。
```java
private static Properties props=new Properties();
static {
    ResourceBundle rb=ResourceBundle.getBundle("factory");

    Enumeration enumeration=rb.getKeys();//类似迭代器
    while (enumeration.hasMoreElements()){
        String key=enumeration.nextElement().toString();
        String value=rb.getString(key);
        props.setProperty(key,value);
    }
}

String value=props.getProperty(name)

/*---------------------------*/
ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        IUserDao iud=(IUserDao) context.getBean("userdao");
        iud.print();
        //
        IRegisterServiceDao isd=(IRegisterServiceDao)context.getBean("RegisterServiceDao");

        isd.Register();

        /* output:
                userdao 's print Method is invoked.......
                登录

                Process finished with exit code 0
        */        
```        

![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180715141455.png)   

### 现在来实现动态代理(所有类的可以使用的生产代理类的方法) 并用MyInnvactionHandler类加权限控制
- 有啥用？写一个=写啦无数个，效率高。
- 顺便温习下通用工厂 动态代理还是用工厂实现
- 如此，我们的工厂就两个通用方法：通用生产实现类对象的方法 & 通用生成实现类代理类的方法。
- 如果用的是ApplicationContext.xml文件的话，不用static块啦。
- MyInnvactionHandler类用于对代理类加处理方式eg:权限控制。
- iud->u  和RegisterServiceDao中的getU()有关？

```java
Object object=null;
//创建实现类
Object target=getInstance(name);
//将实现类对象传入代理类
MyInnvactionHandler handler=new MyInnvactionHandler(target);
//生产代理类并将实体类对象传给代理类，建立关联，方法属性：类加载器 接口类型  处理方式
object=java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(),
    target.getClass().getInterfaces(),handler);

return object;
```

```java
class MyInnvactionHandler{
  //实现类
    private Object target;
    //通过构造函数传进来 代理与实现关联
    //工厂创建代理类
    public MyInnvactionHandler(Object target) {
        this.target = target;
    }
    public boolean isValidate(String[] roles,String role){
        boolean flag=false;
        return flag;
    }

    //实现代理对象调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=null;
        //根据method获得target实现类的method
        Method m1=target.getClass().getDeclaredMethod(method.getName(),method.getParameterTypes());//方法名 参数类型

        //加权限  找方法的注释
        PrivilegeAnnotation privilegeAnnotation=m1.getDeclaredAnnotation(PrivilegeAnnotation.class);

        Session session=SessionFactory.getSession();
        session.beginTransaction();
        result=method.invoke(target,args);

        //为空则不需要权限控制
        //取注释
        if(privilegeAnnotation==null){
            result=method.invoke(target,args);
        }else {
            boolean flag=privilegeAnnotation.isValidate();
            if(!flag){
                result=method.invoke(target,args);
            }else {
                String role=Application.getU().getRole();
                boolean flag1=privilegeAnnotation.isValidate();

                if(flag){
                    result=method.invoke(target,args);

                }else {
                    throw  new MySqlException("权限不够");
                }

            }
        }
        return null;
    }
}
```

```java
public class Factory {

	private static Properties   props=new Properties();

	static {

		ResourceBundle rb=ResourceBundle.getBundle("factory");
		//rb.

	    Enumeration<String> enumers= rb.getKeys();
	    while(enumers.hasMoreElements()) {

	    	String key=enumers.nextElement();
	    	String value=rb.getString(key);
	    	props.setProperty(key, value);
			System.out.println(key+value);

	    }


	}

    //dao 提供不带参数的构造方法
	public static Object getInstance(String name) {
		Object object=null;
		String value=props.getProperty(name);
		System.out.println(value);
		try {
			object=Class.forName(value).newInstance();
			System.out.println(object.getClass().getName());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;

	}

	//通用事务方法 实现权限控制
	//创建动态代理。创建代理类
	public static Object getProxyInstance(String name){
		Object object=null;
		//创建实现类
		Object target=getInstance(name);
		//将实现类对象传入代理类
		MyInnvactionHandler handler=new MyInnvactionHandler(target);
		//类加载器 接口类型  处理方式
		object=java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),handler);

		return object;
	}

}

class Test3{
  main(){
    User u=new User("admin");
       Application.setU(u);
       IRegisterServiceDao ird=(IRegisterServiceDao)Factory.getProxyInstance("RegisterServiceDao");
       ird.Register();

  }
}
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180715145546.png)
