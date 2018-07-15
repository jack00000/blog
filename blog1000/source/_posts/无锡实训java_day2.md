---
title: 无锡实训java_day2
date: 2018-7-4 20:13:10
tags:
categories: 无锡实训java
---
## 大致内容
- java 基础:运算符 循环语句等->注意&&与&的区别（遇x则停和不停）
- 注意不同类型赋值的精度问题：结合位数记->byte 1 short 2 int 4 double 8   float 要加f
- 方法封装代码注意事项：exchange方法->注意局部变量
- 细讲ifloop  loop  循环的讲解
- 细讲 关联 与 依赖 的区别：依赖需要对象传入 关联是属性或方法有联系：比如A类方法有B类的属性
- 细讲static continue break
- 内存模型图（Student s=new Student(30,40)）的执行内部细节
- 类和对象 ：分析和创建类，public 类 构造函数 private 属性  以及 方法名 书写规范
- 寄居蟹方法：不会对类造成影响，可以随便移动。eg:math里面的部分运算方法
- static可以有局部变量，静态变量  不能有成员变量：成员变量在对象创建之前，
- 什么时候用static->eg:寄居蟹方法/模拟数据库
- this 的两种用法：无参构造函数调用有参构造函数，this.age=age赋值

### 重点：内存模型图：了解程序运行数据在 静态方法区 栈区 堆区 的 存储情况  （方法都是压栈）
- 已程序为例 main(){Student s=new Student(10,30,40);}  
- 1.main()方法入栈 碰到Student在硬盘当前目录找到Student 类并加载到 静态方法区  同时在堆区第一次初始化
- 2.静态方法区(目前有类的基本信息）碰到构造函数->压栈->取到参数->对堆中的属性赋值（第二次初始化） 然后方法弹出
- 3.最后在栈区保存一个s对象指向堆区的数据
- static在创建对象（new）时，就运行啦。（题外话）


### 类基本属性：变量 构造函数 方法   

### 关于this的两种用法
- 有无参构造函数调用有参构造函数
```java
class demo{
  int a;
  int b;
  demo(){
    this(100,100)
  }
  demo(int a,int b){
    this.a=a;
    this.b=b;
  }  
}
```

### static ：占啦生命周期的1/3  里面运行寄居蟹方法最为合适
- static块不能有new之后才产生的变量 eg:r
- 将变量 方法定义为static

```java
public class StaticDemo
{

	private int r;
	public static double pi=3.1415926;
    public static String ip;
// 什么样的方法前面可以加static
	static{
	// 读取配置文件
	ip="10.142.14.4";
	System.out.println("class is loading....");
	//r=20;

	}

	public StaticDemo(int r){
	this.r=r;
	//this.pi=pi;

	}


    public  int getR(){
	return r;
	}

	public static double add(int a,int b){

	return a+b+pi;
	}

    public static int ad(int a,int b){
	return a+b;
	}

	public static void main(String args[]){

		//int a;
    System.out.println(StaticDemo.pi);
	//StaticDemo s=new StaticDemo(10);
	//s.pi++;
    // StaticDemo s1=new StaticDemo(20);

	//System.out.println(s1.pi);

	}
}
```

### 关于for循环的练习：乘法口诀表
```java
public class DaoShanJiao {
    public static void main(String args[]) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + j * i + "\t");
            }
            System.out.println();
        }

        System.out.println();

       for(int i=1;i<10;i++){
           for(int k=1;k<i;k++){
               System.out.print("\t");
           }
           for(int j=i;j<10;j++){
               System.out.print(i+"*"+j+"="+i*j+"\t");
           }
           System.out.println();
       }
    }
}

```  
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180704212418.png)   

### 关于递归的练习 ：斐波拉西数列
```java
int[] a=new int[20];
        a[0]=1;
        for(int i=0;i<a.length-2;i++){
            a[i+2]=a[i+1]+a[i];
        }
        for(int i=0;i<a.length;i++){
            System.out.print(a[i]+" ");
        }
```
### 25匹马选3  一次赛5个（不可测速度，只能测快慢）->7次
- 1，分5组 5次  得到5个第一->再测->淘汰最后两组
- 2.存在top1所在的组的第二名>top2    存在top2所在的组的第二名>top3     
- 3.再测一次，决定2,3名

### 一个不错的例子：数组类构造函数与内部类构造函数的执行先后顺序
```java
class Book{
    public Book(String msg) {
        System.out.println(msg);
    }
}

public class Person {

    Book book1 = new Book("book1成员变量初始化");
    static Book book2 = new Book("static成员book2成员变量初始化");

    public Person(String msg) {
        System.out.println(msg);
    }

    Book book3 = new Book("book3成员变量初始化");
    static Book book4 = new Book("static成员book4成员变量初始化");

    public static void funStatic() {
        System.out.println("static修饰的funStatic方法");
    }

    public static void main(String[] args) {
        Person.funStatic();
        System.out.println("****************");
        Person p1 = new Person("p1初始化");
    }
    /**Output
     * static成员book2成员变量初始化
     * static成员book4成员变量初始化
     * static修饰的funStatic方法
     * ***************
     * book1成员变量初始化
     * book3成员变量初始化
     * p1初始化
     *///~
}
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180704205537.png)
