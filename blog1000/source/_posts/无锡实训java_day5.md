---
title: 无锡实训java_day5
date: 2018-7-7 20:13:10
tags:
categories: 无锡实训java
---

## 大致内容
- 接口和多态的设计模式解决啥问题？eg:植物大战僵尸  多个植物 多个僵尸 方法都是打，但有区别。这时候怎么办？
- 适配器模式：实现类一个 接口一个  适配器多个
- 比较常用的保存数据的集合：List Set Collection Stack Map 在内存中的存储方式不同 遍历方式也不同
- 表中如何体现：一对一 一对多
- 一对一：A类中 private Student stu；
- 一对多：A类中 Set<Student> stu =new hashCode<Student>();
- Stack：两个Stack容器实现撤销操作 思路：撤销时 当时操作对象由A栈出进如B栈  需要时在返回A栈。s.push() /s.pop()
- List的遍历(for循环 迭代器)  Set容器的遍历(迭代器)
- Collocation 是List Set 等容器的超级接口 应该也可以用迭代器
- 重点：Map容器key-value 键 值 Map<String Student> map=new HashMap<String,Student>();
- 容器就是对数据的封装，eg：map.entrySet(); 返回的实体对象 内有属性类型 String 和Student 还有get/set方法。
- Map容器的存取：map.containKey(s.getStuId);//判断有没有这个键
- map.remove(s);//删除一行记录
- map.get(s.getStuiId);/根据学生对象的id获取Item对象 也可以取其他数据
- 遍历map容器：将map容器的数据封装成Entry的集合存入Set容器 Set就可以用迭代器啦。
- 和ArrayList()不同 hashset() hashMap() 是无序  用treemap()排序



### 接口与多态 ：两个接口类 通过工厂实现具体实现类之间的交互操作。[完整源码](https://github.com/jack00000/wuxi_train)
- 定义植物接口Zhiwu 僵尸接口Jiangshi 通过工厂生产不同具体植物  定义具体具体植物实现类implement植物接口 重写hit(Jaingshi j)
- 实现类中 将传入的具体僵尸类接口对象(jaingshi j =Factory.getInstance(int id))
- 就实现啦具体植物对象打具体僵尸的方法。
- 简而言之，你要不择手段的把具体僵尸在堆里的数据传入具体植物类里面操作。


### 适配器模式：实现类一个 接口一个  适配器多个
- 解决啥问题？

```java
public class Adapter1 extends Adaptee implements Target {

	@Override
	public void sort1() {
		// TODO Auto-generated method stub
    this.print();
	}

	@Override
	public void sort2() {
		// TODO Auto-generated method stub

	}

}
```
```java
public class Adapter2 implements Target {
	private Adaptee adaptee;


	public Adapter2(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void sort1() {
		// TODO Auto-generated method stub
    adaptee.print();
	}

	@Override
	public void sort2() {
		// TODO Auto-generated method stub

	}

}
```

### 对象适配器模式
- 与适配器模式的不同在于，把继承改成关联，降低子类对父类的依赖程度。

### Stack：两个Stack容器实现撤销操作
```java
Stack<Shape> stack1=new Stack<Shape>();

		Stack<Shape> stack2=new Stack<Shape>();

		Shape s1=Factory.getInstance(1);
		Shape s2=Factory.getInstance(1);
		Shape s3=Factory.getInstance(1);

		stack1.push(s1);
		stack1.push(s2);
		stack1.push(s3);

		draw(stack1);
		Shape sp=stack1.pop();
		stack2.push(sp);

		stack1.push(stack2.pop());

		draw(stack1);
```

### 遍历map容器：将map容器的数据封装程实体类对象的集合存入Set容器
```java
Set<Entry<String,Student>> keyvalues=map.entrySet();

		Iterator<Entry<String,Student>> ite=keyvalues.iterator();

		while(ite.hasNext()) {

		Entry<String,Student> kv=ite.next();

		System.out.println(kv.getKey()+"-----------"+kv.getValue().getName());

		}
```

### 身份验证算法
- 十七位数字本体码和一位校验码组成 两个表 （根据位数与第一个表按照位置相乘）%11得到的数安照第二个表查询所对应项
- 然后 判断与最后一位是否相等。
- S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
- Ai:表示第i位置上的身份证号码数字值(0~9)
- Wi:7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 （表示第i位置上的加权因子）
- Y = mod(S, 11)
- Y: 0 1 2 3 4 5 6 7 8 9 10
- 校验码: 1 0 X 9 8 7 6 5 4 3 2
- 注意：字符‘2’强制转int('2')=52  int('2'-'2')=2
```java
String idString="420704199607284250";
      char[] idChar=idString.toCharArray();
      System.out.println("身份证号"+idString);

      int[] quanX={7 ,9 ,10 ,5 ,8 ,4 ,2 ,1, 6 ,3 ,7 ,9 ,10, 5, 8 ,4 ,2};
      int num=0;
      for(int i=0;i<idString.length()-2;i++){
//            num+=((int) idChar[i])*(quanX[i]);
          int b=(int)(idChar[i]-'0');//"2”转换int是52；
          int a=quanX[i];
          num=num+a*b;
//            num+=Math.pow(quanX[i],(int)idChar[i]);
//            System.out.println(a);
//            System.out.println(b);
//            System.out.println(num);
      }
      System.out.println("加权总和"+num);
      int endMod=mod(num,11);
      System.out.println("11取模后的结果"+endMod);
      char[] reseltComform={'1','0','X' ,'9' ,'8' ,'7', '6' ,'5','4' ,'3' ,'2'};
      System.out.println("身份证最后一位为"+idChar[17]);
      if(reseltComform[endMod-1]==idChar[17])
          System.out.println("验证正确");
      else
          System.out.println("验证失败");
          /*output:身份证号420704199607284250
                   加权总和343
                   11取模后的结果2
                  身份证最后一位为0
                  验证正确*/
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180709225704.png)

### 简单购物车：打印出购物车中所有商品的价格，数量，总价  所有商品总价。[完整源码](https://github.com/jack00000/wuxi_train/tree/master/shopingCart)
- 首先定义实体类Product 再定义一个类Item封装num与Product(【因为num不是Product的属性，所以这样办)
- 定义容器Map<String,Item> map=new hashMap<String,Product>();
- 买一个东西放一个item对象
- 最后把所有数据全部取出来，进行算数运算，打印输出

- 简而言之，就是对Map容器的存取操作(先封装成Entry在存在Set容器中，再用迭代器查询)
- 迭代器取数据多种方法
- kv.getKey()/kv.getValue()/kv.getValue().getProduct().getPrice()是产品的价格属性/kv.getValue()是Item对象


```java
float num=0f;

        Map<String,Item>map=new HashMap<String,Item>();
        Product p1=new Product("001","icecream",5.0f);
        Product p2=new Product("002","apple",10.0f);
        Product p3=new Product("003","cookie",15.0f);
        Product p4=new Product("004","water",20.0f);
        Product p5=new Product("005","books",25.0f);

       Item item1=new Item(10,p1);
       Item item2=new Item(20,p2);
       Item item3=new Item(30,p3);
       Item item4=new Item(40,p4);
       Item item5=new Item(50,p5);

       System.out.println("把数据放入map");
       map.put(p1.getPid(),item1);
       map.put(p2.getPid(),item2);
       map.put(p3.getPid(),item3);
       map.put(p4.getPid(),item4);
       map.put(p5.getPid(),item5);

       System.out.println("输出篮子里面的信息");
        Set<Map.Entry<String,Item>> keyvalues=map.entrySet();
        Iterator<Map.Entry<String,Item>> ite=keyvalues.iterator();
        while (ite.hasNext()){
            Map.Entry<String,Item> kv=ite.next();
            System.out.println("key="+kv.getKey()+" "+"name="+kv.getValue().getProduct().getName()+" "+
                    "价格"+kv.getValue().getProduct().getPrice()+"num="+kv.getValue().getNum());

        }

        System.out.println("打印订单");
        System.out.println("欢迎光临");
        System.out.println("---------------------------------------------");
        Iterator<Map.Entry<String,Item>> ite1=keyvalues.iterator();
        while (ite1.hasNext()){
            Map.Entry<String ,Item> kv=ite1.next();
            System.out.println(kv.getValue().getProduct().getName()+"的总价："+" "
                    +kv.getValue().getNum()+ "*"+kv.getValue().getProduct().getPrice()+" ="
                    +kv.getValue().getNum()*kv.getValue().getProduct().getPrice());
            num+=kv.getValue().getProduct().getPrice()*kv.getValue().getNum();
        }
        System.out.println("总账单价格");
        System.out.println(num);
```
![](http://oxz3x2njl.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180709183357.png)
- 想方设法直接从从map里面的item对象对其num进行修改，失败。
- 换种思路，取出它的num 新建Product 新建Item  最后map.put(item.getProduct().getPid(),item)覆盖到原来的
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180709212222.png)
