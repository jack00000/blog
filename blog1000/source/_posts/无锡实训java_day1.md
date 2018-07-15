---
title: 无锡实训java_day1
date: 2018-7-3 20:13:10
tags:
categories: 无锡实训java
---
## 大致内容
- 发展方向
- java环境搭建
- java基础（容易忽视的）
- .java->.class->运行

### 发展方向
- cs模式
- bs模式
#### 客户端技术：html、css
- javascript : java设计模式-jQuery（侧重元素）vue.js(侧重数据)

#### 移动端：android+java xml json  ios object-c

#### 服务器端技术：web服务 邮件服务 组建服务
- jsp/servlet -（mvc）struct1.x struct2.x jsf
- 数据库支持技术： hibernate（冬眠：持久层）ibates
- 以依赖注入技术：Spring （反射层）
- 数据库：oracle mysql SqlServer （对表的创建  分页统计数据库的编程 存储过程 触发器 游标）




## 具体实例
- Demo.java

```java
//java程序都是1个或者N个class组成的
//程序都有一个入口函数
// 程序调用的规律：遵循栈的调用顺序
//一个JAVA文件 里面可以有 N个非public class
//只能有一个和文件名一样的public class

//一个类对应一个文件

class Demo1
{

	public static void method(){
	System.out.println("this is method1");
	}

	public static void main(String args[]){
     method();

	System.out.println("this is my first java");

	}
}

class  Demo2
{

	public static void main(String args[]){
	System.out.println("this is my second java");

	}
}
```

- Demo4.java

```java
public class Demo4
{

 public static void main(String args[]){

 // 静态的语言
 //整型  byte short int long
 byte month=1;
 int a=10;
 //多位的数据 是不能保存到 低位的变量里
 //month=a;
 //两个short类型相乘不是short 而是int

 // 浮点型 float  double
 float pi=3.14f;
 double pi1=3.1415926;

 // char
 char c='c';
 char d='陈';
 int n=(int)d;
 char k='\'';

 boolean flag=true;
 boolean flag1=false;
//&&  ||
boolean up=false;
boolean left=false;
boolean right=false;
boolean down =false;

if(up&&!left&&!right&&!down) System.out.println("up");
if(up&&left&&!right&&!down) System.out.println("up");
if(up&&left&&right&&!down) System.out.println("up");
if(up&&left&&right&&down) System.out.println("up");
if(!up&&!left&&!right&&!down) System.out.println("up");
if(!up&&!left&&!right&&down) System.out.println("up");
if(!up&&!left&&right&&down) System.out.println("up");
if(!up&&left&&right&&down) System.out.println("up");
//游戏中8个方向的控制





 System.out.println(n);




 }
}
```

## 练习 ：输入一个整数  输出 2的次幂 的表示  
- 思路：转换成二进制 再判断

```java
import java.util.Arrays;
//把十进制转换为二进制的位
public class ToBinBit
{
    public static void main(String[] args)
    {
        //1.假设现在有一个int为20，需要转换为二进制输出
        int number = 20;
        //2.需要一个长度为32的int数组来存储结果二进制
        int[] bit = new int[32];
        //3.循环，把原始数除以2取得余数，这个余数就是二进制数，原始的数等于商。
        //商如果不能再除以二，结束循环。
        int sum=0;

        for(int i = 0; number > 1; i++)
        {
            //取得除以2的余数
            int b = number % 2;
            //数字赋值为除以2的商
            number = number / 2;

            bit[i] = b;

            if( number < 2 )
            {
                //已经不能再把数除以2，就把上直接放到数组的下一位
                bit[i + 1] = number;
            }
        }

        //4.翻转数组
        for(int i = 0; i < bit.length / 2;i++)
        {
            int temp = bit[i];
            //第一个数的值设置为最后一个数的值
            //第二次的时候，i是1，把第二个数的值，赋值为倒数第二个
            bit[i] = bit[ bit.length - 1 - i ];
            bit[ bit.lenimport java.util.Arrays;
//把十进制转换为二进制的位
public class ToBinBit
{
    public static void main(String[] args)
    {
        //1.假设现在有一个int为20，需要转换为二进制输出
        int number = 20;
        //2.需要一个长度为32的int数组来存储结果二进制
        int[] bit = new int[32];
        //3.循环，把原始数除以2取得余数，这个余数就是二进制数，原始的数等于商。
        //商如果不能再除以二，结束循环。

        for(int i = 0; number > 1; i++)
        {
            //取得除以2的余数
            int b = number % 2;
            //数字赋值为除以2的商
            number = number / 2;

            bit[i] = b;

            if( number < 2 )
            {
                //已经不能再把数除以2，就把上直接放到数组的下一位
                bit[i + 1] = number;
            }
        }

        //4.翻转数组
        for(int i = 0; i < bit.length / 2;i++)
        {
            int temp = bit[i];
            //第一个数的值设置为最后一个数的值
            //第二次的时候，i是1，把第二个数的值，赋值为倒数第二个
            bit[i] = bit[ bit.length - 1 - i ];
            bit[ bit.length - 1 - i ] = temp;
        }

        System.out.println( Arrays.toString(bit) );

				//判断

    }
}gth - 1 - i ] = temp;
        }

        System.out.println( Arrays.toString(bit) );

        for(int i=0;i<bit.length;i++){
             if(bit[i]==1){
                 System.out.print(32-i);
             }
        }

        System.out.println(sum);
    }



}

```
