---
title: 无锡实训java_day11
date: 2018-7-18 20:13:10
tags: java
categories: 无锡实训java
---

## 大致内容
- hover方法 / 组件.style.Border("")改变样式
- 移动js写表格的位置。设置div的id="table_postion";
- var table_postion=document.getElementById("table_postion");
- table_postion.appendChild(table);//table是最高级父标签
- 闭包：用内存模型图理解。
- web开发：web project  并不是maven project。
- 怎么配置web.xml
- 请求与响应模型。
- 1.php jsp seevelt  发到客户端的全部是静态的部分 以数据流 <html>
- 2.不管服务器怎么执行 最后以 数据流的方式把数据《html》+数据的方式  发到游览器端 html+js  
- 3.图片的加载  jsp=java+html。 jsp就是servelet

### 如何理解js内存模型
- java内存模型：你知道new 对象时内存发生的变化和方法压栈就行
- js内存模型：你要知道function何时被调用就行
- 如何解决闭包： 加(function(){})()； 自动运行

```JavaScript
<script type="text/javascript">
  function f1(){
    //局部变量
    var a=10;
    var b=function f2(){
      alert(a);
    }
    a=20;
    return b;//等于20
  }
  //先运行a=10 在运行a=20  再运行b这个函数。-》闭包
  //闭包：在函数里面写函数，用于获取函数的局部变量时 里面的function不运行。
  //闭包产生的原因：变量搞完，方法才使用。

  function f2(){
    var arr=new Array();
    for(var i=0;i<10;i++){
      arr[i]=function(){
        alert(i)
      }
    }
    return arr;//变量搞完之后 调用方法function。
  }
  var aa=f1();//执行f2  里面的function不运行。
  for(var j=0;j<aa.length;j++){
    aa[j]();
  }
  //如何让里面的function自动运行  加()
  arr[i]=(function(){
    alert(i)
  })(i);//传过来i 并自动运行。
</script>
```

### web开发  Linux上没有myeclipse  直接用自己熟悉的idea工具。
- Springboot框架
- 开发宿舍管理系统
- 页面跳转/获取客户端传来的表单from数据。
- 根据名字name取
