---
title: VIM vs GUI文本编辑器
date: 2018-1-27 15：00
tags:
categories: skills
---





### vim 无法从外部复制
#### 常用命令
---
- esc  编辑模式/命令项模式->命令模式
- a A  命令模式->编辑模式
- ：   命令模式->命令项模式
---

### GUI文本编辑器
---
- 改系统文件的权限问题  不能直接右键文件以管理员打开 要先以管理员打开文件所在文件夹
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180512210753.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180512210236.png)

### deepin(linux) 配置java环境
- 在终端用文本编辑器打开/etc/profile
```java
gedit etc/profile
```
- 在profile文件末尾加入：
```java
export JAVA_HOME=jdk安装路径
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716232629.png)
