---
title: 无锡实训java_day9
date: 2018-7-12 20:13:10
tags:
categories: 无锡实训java
---

## 大致内容
- html(head body)
- 表格table：行和列合并。
- 表单from：与用户交互，填充信息。
- 会用样式 eg: 设计.containner样式    有需要用的  直接 class="containner"
- bootstrap的网格布局Grid  元素自适应屏幕  移动端优先。
- 本体引入bootsrap/线上引入bootstrap
- 菜鸟工具 ：[bottstrap可视化](http://www.runoob.com/try/bootstrap/layoutit/)
  html编辑工具  直接拖。。。
- barckt编辑器
- 表的样式 直接<table class="table/table-striped/"

```html

<link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery文件.js"/>
<script src="js/bootstrap文件.js"/>

//线上引入
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
<link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>

```

### [栅格布局](http://how2j.cn/k/boostrap/boostrap-layerout/778.html#nowhere)

```html
<div class="container">
  <div class="row">
     <div class="col-xs-6">一半</div>
     <div class="col-xs-6">一半</div>
  </div>
  <div class="row">
     <div class="col-xs-4">1/3</div>
     <div class="col-xs-4">1/3</div>
     <div class="col-xs-4">1/3</div>
  </div>
```

### [bottstrap可视化](http://www.runoob.com/try/bootstrap/layoutit/)
- 1.先设置栅格布局
- 2.然后放元素。
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716160550.png)

### 如何快速搞出一个像样的静态网站
- 把bootstrap官网的example复制下来自己改。
- 下载位置及显示效果如下图
- 注意：如果要转移html文件，不要忘啦将其所用到的css，js文件一起转移。
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716170946.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716171418.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716194311.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716171610.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716195557.png)

### 如何搞一个像样的动态网站
- 表单 表格你要熟。
- 怎么从数据库取数据并显示在web端。
- 后台curd
- java开发框架 分层你要熟。
