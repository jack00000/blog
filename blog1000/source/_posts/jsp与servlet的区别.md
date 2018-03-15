---
title: jsp与servlet的区别
date: 2017-11-29 15：00
tags:   skills java
categories: skills
---


- JSP在本质上就是SERVLET,但是两者的创建方式不一样.Servlet完全是JAVA程序代码构成擅长于流程控制和事务处理而通过Servlet
来生成动态网页;JSP由HTML代码和JSP标签构成，可以方便地编写动态网页
因此在实际应用中采用Servlet来控制业务流程,而采用JSP来生成动态网页.在struts框架中,JSP位于MVC设计模式的视图层,而Servlet位于控制层.

- JSP是Servlet技术的扩展，本质上就是Servlet的简易方式。JSP编译后是“类servlet”。Servlet和JSP最主要的不同点在于，Servlet的应用逻辑是在Java文件中，并且完全从表示层中的HTML里分离开来。而JSP的情况是Java和HTML可以组合成一个扩展名为.jsp的文件。JSP侧重于视图，Servlet主要用于控制逻辑。
