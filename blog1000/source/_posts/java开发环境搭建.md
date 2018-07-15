---
title: java开发环境搭建
date: 2017-11-29 15：00
tags:   skills java
categories: 环境搭建
---
<h3>1.下载jdk</h3>
想要成功配置Java的环境变量，那肯定就要安装JDK，才能开始配置的。[官网下载](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html),一路next。
<h3>2.jdk成功标志</h3>
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-09_141950.png)
<h3>3.配置系统变量JAVA_HOME</h3>
选择【新建系统变量】--弹出“新建系统变量”对话框，在“变量名”文本框输入“JAVA_HOME”,在“变量值”文本框输入JDK的安装路径（也就是步骤5的文件夹路径），单击“确定”按钮，如图：

<h3>4.配置系统变量Path</h3>
在“系统变量”选项区域中查看PATH变量，如果不存在，则新建变量 PATH，否则选中该变量，单击“编辑”按钮，在“变量值”文本框的起始位置添加“%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;”或者是直接“%JAVA_HOME%\bin;”，单击确定按钮，

<h3>5.配置系统变量CLASSPATH </h3>
在“系统变量”选项区域中查看CLASSPATH 变量，如果不存在，则新建变量CLASSPATH，否则选中该变量，单击“编辑”按钮，在“变量值”文本框的起始位置添加“.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;”。
<h3>6.系统变量成功标志</h3>
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-09_142012.png)
