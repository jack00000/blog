---
title: JSON JavaScript 对象表示法
date: 2018-1-13 20:13:10
tags: java
categories: how2j
---


<h3>完整教程：[how2j](http://how2j.cn/k/json/json-tutorial/531.html)</h3>

<h3>备忘</h3>

- JavaScript 对象表示法（JavaScript Object Notation） 是一种存储数据的方式。

- 创建JSON对象
```js
var gareen = {"name":"盖伦","hp":616};
```
这样就创建了一个JSON 对象
JSON对象由 名称/值对组成 名称和值之间用冒号:隔开
名称必须用双引号" 包含起来
值可以是任意javascript数据类型，字符串，布尔，数字 ，数组甚至是对象
不同的名称/值对之间用 逗号 , 隔开

- 访问JSON对象
```js
document.write(gareen.name);
document.write(gareen.hp);
```

- JSON数组
```js
var heros=
[
    {"name":"盖伦","hp":616},
    {"name":"提莫","hp":313},
    {"name":"死哥","hp":432},
    {"name":"火女","hp":389}
]

document.write( "第4个英雄是:" +  heros[3].name);
```

- SON对象与JavaScript对象:JavaScript对象 分内置对象(Number,String,Array,Date,Math)和自定义对象 JSON就是自定义对象，只不过是以JSON这样的数据组织方式表达出来 所以不存在JSON对象与JavaScript对象的转换问题

- 字符串转为JSON对象
```js
var s1 = "{\"name\":\"盖伦\"";
var s2 = ",\"hp\":616}";
var s3 = s1+s2;

document.write("这是一个JSON格式的字符串:" + s3);
document.write("<br>");
var gareen = eval("("+s3+")");

document.write("这是一个JSON对象: " + gareen);
```  
