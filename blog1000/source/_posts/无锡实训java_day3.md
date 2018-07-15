---
title: 无锡实训java_day3
date: 2018-7-5 20:13:10
tags:
categories: 无锡实训java
---


## 大致内容
- 属性私有，方法公有好处体现在：方法中set get方法中可以加判断  
- static块里面没new就行。
- 类和类关系：依赖与天生关联 依赖关联  单向关联 双向关联
- 单例模式：限制构造方法private  此时还是可以通过类的内部方法调用从而新建对象，怎么办？ 定义一个static 类对象 设为null 为空时允许构造函数实例化对象，不为null 不可实例化。即可达到目的。if(single==null) single=new singleInstance()
- 继承与工厂模式：注意super()
- 简单工厂：根据指令 用子类构造函数新建对象赋给 父类对象  （工厂全部都是父类对象 构造函数是子类的）
- 继承的几个难点：
```txt
1.数据是如何通过子类和父类的构造方法完成数据封装
2.子类和父类从硬盘加载到静态方法区的顺序和构造方法调用的顺序
3.父类和子类都有共同的属性和方法的时候 如何进行 调用
4.父类类型指向子类对象的对方法的调用的影响
```

- 老师总结的注意事项
```txt
子类继承父类 就自动拥有了父类的公有方法和公有属性（protected ）
父类先加载 然后子类再加载
构造方法的调用顺序 子类的构造方法的第一行 不写的话 super();
super(参数) 实现数据的传递
先调用子类的构造方法 但是子类的构造方法的第一行调用父类的构造方法

如果子类对象在调用方法的时候，先找父类有没有 如果父类有的话 再找子类 如果找到了 运行
如果找不到 就运行父类的方法
如果父类和子类都有相同的方法 称之为 重写
如果想显式的调用 父类的方法 super.f();
```


### 属性私有，方法公有好处体现在：方法中set get方法中可以加判断  
```java
class A{
  private int age;
  public int setAge(int age){
    if(age>0) this.age=age;
  }
}
```
### 依赖(方法需要传入其他类对象)举例
```java
public class Zidan {
    private  int bleed;
    private  boolean live;
    public int damage=50;

    public Zidan(int bleed,boolean live){
        this.bleed=bleed;
        this.live=live;
    }

    public void zi2Tanke(Tanke t){
        this.bleed-=damage;
        t.decreseBleed(damage);
    }
    public int getBleed(){
        return this.bleed;
    }

    public boolean getLive(){
        if(this.bleed<=0)
            this.live=false;
        else
            this.live=true;


        return this.live;
    }

}

```
```java
public class Tanke {
    private int bleed;
    private boolean live;

    public Tanke(int bleed,boolean live){
        this.bleed=bleed;
        this.live=live;
    }

    public int getBleed(){
        return this.bleed;
    }
    public void decreseBleed(int damage){
        this.bleed-=damage;
    }

    public boolean getLive(){
        if(this.bleed<=0)
            this.live=false;
        else
            this.live=true;


        return this.live;
    }

}
```
```java
package test;

public class test {
    public static void main(String args[]){
        Zidan z=new Zidan(100,true);
        Tanke t=new Tanke(500,true);
        System.out.println(z.getBleed());
        System.out.println(z.getLive());
        System.out.println(t.getBleed());
        System.out.println(t.getLive());

        z.zi2Tanke(t);
        System.out.println();

        System.out.println(z.getBleed());
        System.out.println(z.getLive());
        System.out.println(t.getBleed());
        System.out.println(t.getLive());

    }
}
/*
 output 100
        true
        500
        true

        50
        true
        450
        true*/

```
### 为什么要关联？ 可以通过一个类的对象用其中其他类的引用（这个也在堆区）去访问其堆区的对象
### 天生关联：类中的属性有其他类对象的引用（eg：private Demo demo=new Demo(int id ,int age)；），已实例化。
- 此时，新建外部类对象会 在 堆区:1. 外部类 会第一步初始化（属性值为null）2.Demo类 第一次初始化并向堆中的对象赋值(第二次初始化) 再执行 外部类的赋值(第二次初始化)
- 此时，栈区只有一个外部类对象(暂且用a代替) a指向堆区的外部类对象
- 问题：谁指向堆区的Demo类对象？ 堆区的外部类中的属性demo
```java
class A{
  private int num;
  private Demo demo=new Demo(int id ,int age);
  public A(int num){
    this.num=num;
  }  
}
```
```java
class Demo{
  private int age;
  private int id;
  public Demo(int id,int age){
    this.age=age;
    this.id=id;
  }
}
```
```java
class test{
  public static void main(String args[]){
    A a=new A();
  }
}
```

### 依赖关联：依赖总是和方法同时存在
- 在新建外部类A对象的时候 A类对象在堆区第一次初始化(属性值为空)  完成赋值后 将demo（也在堆区）指向在堆区初始化的Demo对象  并给堆区的对象赋值
- 此时，栈区只有一个外部类对象(暂且用a代替) a指向堆区的外部类对象
- 问题：谁指向堆区的Demo类对象？ 堆区的外部类中的属性demo
```java
class A{
  private int num;
  private Demo demo;
  public A(int name){
    this.num=num;
    this.demo=new Demo(int id,int age);//依赖关联
  }  
}
```
```java
class Demo{
  private int age;
  private int id;
  public Demo(int id,int age){
    this.age=age;
    this.id=id;

  }
}
```
```java
class test{
  public static void main(String args[]){
    A a=new A(666);
  }
}
```

### 双向关联：基于单向关联的扩展 注意：构造函数 和 this
- 简单实现：把天生关联做两次
- 一般实现：把依赖关联做两次
- 此时，内存结构图为：栈区有demo a两个对象   堆区有包含demo的A类对象和包含a的Demo类对象  栈区demo指向堆区Demo类 栈区a指向堆区的A类对象


```java
class A{
  private int num;
  private Demo demo;
  public A(int num){
    this.num=num;
    this.demo=new Demo(int id,int age);
  }  
}
```
```java
class Demo{
  private int age;
  private int id;
  private A a;
  public Demo(int id,int age){
    this.age=age;
    this.id=id;
    this.a=A(int num)

  }
}
```
```java
class test{
  public static void main(String args[]){
    A a=new A(666);
    Demo demo=new Demo(1,10);
  }
}
```
### 继承的几个难点的解答
- 1.数据是如何通过子类和父类的构造方法完成数据封装
- super()方法 往上传 可以调用父类无参构造函数 （父类没有会报错） 但没有创建对象

- 2.子类和父类从硬盘加载到静态方法区的顺序和构造方法调用的顺序
- 先加载父类再加载子类   构造方法调用的顺序  先父类后子类  

- 3.父类和子类都有共同的属性和方法的时候 如何进行 调用
- 先找父类 再找子类 如果子类方法存在则调用子类 不存在则调用父类

- 4.父类类型指向子类对象的对方法的调用的影响  Father father=new Son(1,"father",2,"son");
- 多态、动态链接，向上转型  结合工厂模式理解
- 会屏蔽子类的非重写方法
- 此时,father instanceof Son=true 即 father 也是 son 对象  
- 屏蔽子类的非重写方法  解决方法：Son(father)  转换

### 工厂模式(根据指令eg:id=1  来创建子类对象并赋给父类对象)来理解继承
```java
package org.whgc;

public class Cricle extends SShape {
	private int r=10;


	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("this is r="+this.r+"Բ");
	}
}
```
```java
package org.whgc;

public class Rectangle extends SShape {
	private int a=10;
	private int b=20;
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("a="+a+"b="+b+"的矩形" );;
	}
}
```
```java
package org.whgc;

public class SShape {

	public void draw() {

		System.out.println("this is shape");
	}

}
```
```java
package org.whgc;

public class Factory {

	public static SShape getInstance(int id) {
		SShape shape=null;
		if(id==1) shape=new Cricle();
		if(id==2) shape=new Rectangle();

		return shape;

	}

}
```
