---
title: JPanel与JFrame的区别  
date: 2017-12-3 20:13:10
tags: java
categories: 实验项目
---

- Jpanel不是顶级窗口，不能直接输出。它必须放在象JFrame这样的顶级窗口上才能输出。

- JcontentPane实际上就是一个JPanel。Jframe中会默认new一个JPanel，塞入JFrame中。

- JPanel可以放在JFrame中，但是反过来就是不行的！效果上没什么特大的区别~！

- JFrame用来做主页面框架，JPanel只是普通页面

- JPanel可以放在JFrame中，反之不行.

- JFrame   可以看成,最底级容器,可以包括其他上级容器包括JPanel

- JFrame只是一个界面，也就是个框架，要想把控件放在该界面中，必须把控件放在JPanel中，然后再把JPanel放在JFrame中，JPanel作为一个容器使用。

![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-27_095000.png)
