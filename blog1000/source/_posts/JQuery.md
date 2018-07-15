---
title: JQuery
date: 2018-1-13 20:13:10
tags: 前端
categories: how2j
---

<h3>完整教程：[how2j](http://how2j.cn/k/jquery/jquery-tutorial/467.html#nowhere)</h3>

<h3>备忘</h3>
- JQuery是一个javascript的框架，是对javascript的一种封装。 通过JQuery可以非常方便的操作html的元素 。

- 简单例子1

![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-26_160936.png)

- 常用方法
- 取值 JQuery对象的val()方法
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-26_162932.png)
- 通过html() 获取元素内容,如果有子元素，保留标签
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-26_162738.png)
- 通过text() 获取元素内容,如果有子元素，不包含标签
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-26_162319.png)

### json字符串转换成json对象

```java
<script src="http://how2j.cn/study/jquery.min.js"></script>

<script>

var s1 = "{\"name\":\"盖伦\"";
var s2 = ",\"hp\":616}";
var s3 = s1+s2;

document.write("这是一个JSON格式的字符串:" + s3);
document.write("<br>");
var gareen = $.parseJSON(s3);

document.write("这是一个JSON对象: " + gareen);

</script>
```
