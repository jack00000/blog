---
title: 无锡实训java_day10
date: 2018-7-17 20:13:10
tags: java
categories: 无锡实训java
---

## 大致内容
- js：能干啥？难点。
- js继承：原型链  与java继承不同
- fuction函数的三个角色(可以干多种工作)：一般函数，构造函数，对象
- 必须清楚fuction的角色 才能使用。
- js写表格 不用div table等标签。
- 网页的实现原理：div都是以对象的方式展现在内存上。对象的包含思想。
- 所有东西都包含在window对象里面。
- 解释执行，一行一行执行，如果是函数，则加载。script中
- 看到数据就分配空间。  var a=10;  script中
- 这个a在window对象中  可以window.a调用 (window可以省略)
- var 可以是任意类型。看你赋给它什么值。
- 4种数据封装
- 数据产生一个集合 然后转化成json格式传给你。
- 函数的实现以及闭包的思想。
- 定义函数 函数作为参数传递。
- 属性封装 方法用原型琏进行添加。
- 动态的生成网页。

### 创建对象的三种方法
- json格式
- 构造函数 需要new
- 构造函数 不需要new

```JavaScript
var s1={
  id:'0001',
  name: 'zhangsan',
  age:20
};
//用构造函数new一个对象
function stu1(id,name,age){
  this.id=id;
  this.name=name;
  this.age=age;
}
var s3=new stu1('0004','liuer',50);

//不用new 直接调用
function stuInstance(id,name,age){
  var m=new object();
  m.id=id;
  m.name=name;
  m.age=age;
  return m;
}
var m2=stuInstance('00009','www',60);  
```

### 重点：原型内存模型图 [原始教程](https://blog.csdn.net/u010425776/article/details/53617292)
- 新建一个对象都会默认有一个prototype 指向构造函数。输出该prototype会返回一个完整的构造函数，有啥用？鬼知道。
- 只要清除用原型创建方法就行。
- 这个不像java，有栈区，静态方法区，堆区。
- 所有函数初始化完成后都会创建一个函数对象和一个原型对象，并且通过prototype、construcor属性相互引用

### 重点：作用域琏内存模型图 [原始教程](https://blog.csdn.net/u010425776/article/details/53557942)
#### js作用域
- 作用域是指当前正在执行的代码能够访问到变量的范围;
- 每个函数都有各自的作用域，存储函数所有的局部变量(严格来说作用域存的是指针)
#### 变量对象
- 变量对象用于存储函数各自的局部变量
- 每个函数都有各自的变量对象，并且在函数执行时被创建
- 作用域中存储的其实是变量对象的引用，而变量对象才是存储函数局部变量的地。如图。


#### JS作用域的内存模型


### 属性封装 方法用原型琏进行添加。
- 原型是什么?为什么要使用原型？
- 为什么属性不?用原型琏进行添加？
- 要清楚数据的流动。(在内存是怎么传递的)
- 1.new 一个对象 会 prototype。

### 函数：四种方法对函数的调用

### 函数之间的继承
- son.prototype=father.prototype;

```JavaScript
<script>
//为什么用继承   实现继承
     function father(name){
  this.name=name;
}
//用原型添加father的方法
     father.prototype.getName=function(){
       return this.name;
     }

     fuction son(name,age){
       //让father成为Son的属性
       //父类依赖子类、执行
       father.call(this,name);
       //此时方法没有进来
  this.age=age;
}
   //让son的原型对象指向father的原型对象，此时son.prototype可以通过Father.prototype访问到 father的方法。
   son.prototype=father.prototype;
   //或者这样写  Object 把father的原型prototype拷贝一份。
   son.prototype=object(father.prototype);
   //用原型添加father的方法  26 28 顺序不能换 一换就把
     son.prototype.getAge=function(){
       return this.age;

     }
    var s=new Date()

//子类创建对象 完成啦对父类对象的使用。
//---------------去看内存结构图
</script>

```


## 实操

### 用js写表格

- 只要会新建标签createElement()，设置setAttribute() ,从任何形式数组取数据，给组件赋值赋值innerHTML。
- 重点说一下：从任何形式数组取数据 eg:从weather数组取数据。
- var container=document.createElemement("div");//创建标签
- container.setAttribute("class","container");//设置style

```JavaScript
<script>
//body也是对象。
  var studs=[{id:'0000001',name:'李星',age:20},
             {id:'0000002',name:'李星1',age:30},
             {id:'0000003',name:'李星2',age:40}];

  var tableheads=["学号","姓名","年龄","操作"];
  var studattribution=["学号","姓名","年龄","操作"];
  function init(){
    //所有东西都是对象  有可以得到  没有可以创建。
    var body=document.body;

    var container=document.createElemement("div");
    container.setAttribute("class","container");
    //添加带class="container"的div
    body.appendChild(container);
    var row=document.createElemement("div")
    row.setAttribute("class","row");
    container.appendChild(row);

    var col=document.createElemement("div")
    col.setAttribute("class","col-md-12");
    row.appendChild(col);
    //-------------weather
    return col;

  }

  function inittable(col){
    var table=document.v=createElement("table");
    table.setAttribute("table ");
    var tr_head=document.createElement("tr");
    table.appendChild(tr_head);
    //创建表头
    for(var i=0;i<tableheads.length;i++){
      var th=document.createElement("th");
      th.setAttribute("class","text-center");
      th.innerHTML=tableheads[i];
      table.appendChild(th);
    }

    //生产td 表项
    for(var j=0;j<studs.length;j++){
      var tr=document.createElement("tr");
      for(var k=0;k<studs.length;j++){
        var td=document.createElement("td");
        td.setAttribute("class","text-center");
        td.innerHTML=studs[j][studattribution[k]];
        tr.appendChild(td);
      }
      //写删除按钮
      var td_last=document.createElement("td");
       td_last.setAttribute("class","text-center");
      var button=document.createElement("button");
      button.setAttribute("class","btn bg-warning");
      button.innerHTML="删除";
      button.onclick=function(event){
          var target=event.target;
          var t=target.parentNode.parentNode;
         // alert(t);
          table.removeChild(t);
        //  alert(t);


      };
      td_last.appendChild(button);
      tr.appendChild(td_last);
      table.appendChild(tr);


    }
  }
  window.onload=function(){
    inittable(init());
  }

</script>
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180717222553.png)

### 重点说一下：从任何形式数组取数据 eg:从weather数组取数据。
- 顺便看一下js的其他数组  js没有集合，或者说使用数组实现的集合。
- 取weather数组数据的思路：取到数组最外部对象，然后一步步深入
- weather数组里面有date,message,status,city,count,data，yesterday，forecast对象 (称为一级属性)
- data属性里面有n个二级属性 ，yesterday属性里面有n个二级属性，forecast对象是对象数组，每个二级对象含有n的三级属性。
- weather[8][1].date 就获取到啦forecast的第一个对象的date属性值。

```JavaScript
<script>
   //js的数组   js没有集合  所以数组很重要
   var stu=[];
   var stud=new Array();
   var nums=[1,2,3,4,5,6];
   //全部赋值
   //如何实现 item 每一项 index 索引 (item,index)也行
   nums.forEach(function(item,index,array){
     alert(index);
   });
   //对象数组
   var ss=[{id:1,name:'zhangsan',age:20},{id:1,name:'zhangsan',age:20},{id:1,name:'zhangsan',age:20}]
   //数组遍历
   for(var i=0;i<ss.lenghtl;i++){
     document.write(ss[i].name+" ");
   }

   //-----------------------
   var message='{id:1,name:'zhangsan',age:20}';
   var message1='{"id":1,"name":'zhangsan',"age":20}';
   alert(message)；
   //转化为json数据格式
   var m1=JSON.parse(message);

   var mobjct=eval('('+message+')');


   //-------------weather 网上给的是字符串  赋值给weather weather就是Json对象。
   var weather={"date":"20180717",
   "message":"Success !",
   "status":200,
   "city":"北京",
   "count":2,
   "data":
   {"shidu":"95%","pm25":9.0,"pm10":13.0,"quality":"优","wendu":"22","ganmao":"各类人群可自由活动",
   "yesterday":
   {"date":"16日星期一","sunrise":"04:58","high":"高温 30.0℃","low":"低温 24.0℃","sunset":"19:42","aqi":49.0,"fx":"南风","fl":"<3级","type":"雷阵雨","notice":"带好雨具，别在树下躲雨"},
   "forecast":
   [{"date":"17日星期二","sunrise":"04:59","high":"高温 27.0℃","low":"低温 23.0℃","sunset":"19:41","aqi":74.0,"fx":"西风","fl":"<3级","type":"中雨","notice":"记得随身携带雨伞哦"},
   {"date":"18日星期三","sunrise":"04:59","high":"高温 32.0℃","low":"低温 24.0℃","sunset":"19:41","aqi":97.0,"fx":"南风","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},
   {"date":"19日星期四","sunrise":"05:00","high":"高温 33.0℃","low":"低温 26.0℃","sunset":"19:40","aqi":101.0,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},
   {"date":"20日星期五","sunrise":"05:01","high":"高温 34.0℃","low":"低温 27.0℃","sunset":"19:39","aqi":113.0,"fx":"南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},
   {"date":"21日星期六","sunrise":"05:02","high":"高温 34.0℃","low":"低温 27.0℃","sunset":"19:39","aqi":112.0,"fx":"南风","fl":"<3级","type":"晴","notice":"愿你拥有比阳光明媚的心情"}]}};
//-------------weather
//取weather数据的数据
//设计一级 二级菜单 格式
//如何设计菜单的数据


</script>
```

### 将字符串转化为json对象

```JavaScript
<script>

    var stus='[{"id":"lf","age":20},{"id":"zs","age":40}]';
    alert(stus);
   var stuarray=JSON.parse(stus);
 //   stuarray=eval('('+stus+')');

    stuarray.forEach(function(item,index,array){
        alert(index);

    });
   // alert(stuarray);
    for(var i=0;i<stuarray.length;i++){
      //  document.write(stuarray[i].id+"<br>");

    }

</script>
```

### 闭包+原型链+继承
