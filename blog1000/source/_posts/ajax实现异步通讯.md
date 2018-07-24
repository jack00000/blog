---
title: ajax实现异步通讯
date: 2018-7-24 20:13:10
tags: java
categories: 无锡实训java
---

## 大致内容
- 页面向服务器发送request，获取request时页面不刷新。用js实现页面数据更新。
- weather 天气数据显示
- 把对象转换成json对象  把它传到页面  用js显示数据  (以前是直接传对象||集合)

###

```java
//数据是对象的话
Student s=new Student(1,"zhangsan",88);
//用导入的包的函数 import net.sf.jsoon.JSONObject
JSONObject json=JSONObject.fromObject(s);
Sting message=json.toString();
System.out.println(s.toString());

//数据是集合  
//用导入的包的函数 import net.sf.json.JSONArray
List<Student> list =new ArrayList<Student>();
list.add(new Student(1,"zhangsan",88));
list.add(new Student(1,"zhangsan",88));
list.add(new Student(1,"zhangsan",88));
list.add(new Student(1,"zhangsan",88));
list.add(new Student(1,"zhangsan",88));
JSONArray array=JSONArray.fromObject(list);
System.out.println(list.toString());

//数据是map集合(包含上面两种数据)
//把数据合一  用put方法
Map map=new HashMap();
map.put("stu",s);
map.put("stus",list);

JSONObject jsonmap=JSONObject.fromObject(map);
System.out.println(map.toString());

```



### 实现往客户端输出&客户端接收

```java
//loginAction    这时候页面是不刷新的
PrintWriter out=response.getWriter()
out.println(message);
out.flush();
//
```

```java
//客户端游览器
//jquery实现  需要导包
<script>
   fuction send(){
     //在此实现数据获取和显示
     var path="LoginServlet.action";
     $.ajax({
       //调用Servlet取得数据
       url:path,
       //返回类型
       dataType: 'json',
       type:'get',
       success:fuction(data){
         //这里面创建页面
         alert(data.name);
         //获得放数据位置的div
         var content=document.getElementById("content");
         var message="<p>welcome"+data.name+"</p>"
         //将内容放进去
         content.innerHTML=message;
       }
     }


     );
   }

</script>
<div id="content">把js中的数据显示在这里</div>

//不能用submit（提交就完蛋啦）  用send 
<form action="logServlet" method="post">
<button onClick="send"></button>
</form>
