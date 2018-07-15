---
title: 部署web项目ssm框架的maven项目
date: 2018-6-2 15：00
tags:
categories:
---

## [完整教程](http://how2j.cn/k/deploy2linux/deploy2linux-maven-project/1662.html)
## 简要步骤
------
一个简单的web项目 主要功能上传显示图片及图片外链到其他网址。
在云服务部署此maven项目：
安装JAVA tomacat mysql
上传jar   数据库文件并引入。并在数据库指定图片路径下上传图片。
配置tomcat运行工程所在的目录。

------
### 放弃jar上传部署方式 采用war部署方式(只需要把war放在webapps下，Tomcat自动解压，部署多个项目：多个tomcat不同端口)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-06_150633.png)

### 1.[购买阿里云服务器](http://how2j.cn/k/deploy2linux/deploy2linux-buy/1593.html)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-05_184150.png)
### 2.[安装环境](http://how2j.cn/k/deploy2linux/deploy2linux-setup/1607.html) （how2j的是centerOs   我用的ubantu 不能用下面的方法）
#### 2.1 java环境  yum -y install java-1.8.0-openjdk.x86_64
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-05_185931.png)
#### 2.2 mysql安装
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-05_190414.png)
#### 2.3 tomcat安装
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-05_191126.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-05_191523.png)

### 3.本地生成jar/war并上传，本地生成数据库文件并上传。
#### 3.1 准备ftp管理文件传输 [ftp安装](http://how2j.cn/k/deploy2linux/deploy2linux-install/1600.html)
- 与ssh不同 需要创建用户和密码  每个用户对每个文件的访问权限不同 不能用ssh方式 登录ftp
#### 3.2 放弃how2j推荐的ftp软件  改用简单易懂的winsp客户端
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-06_153403.png)
#### 3.3 一级域名（fangjun.xyz）绑定服务器
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-07_105120.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-07_105322.png)
#### 3.4 创建二级域名(eg:xxx.fangjun.xyz)并绑定云服务器上的项目

#### 3.5 域名备案与网站备案（网站备案包括域名和空间，是一起的。）

其实，本质上来讲，网站备案和域名备案是指的同一件事情，那就是为你的网站申请ICP备案号，没有本质的区别。 备案是根据空间IP来的。因为域名要访问空间必须要解析指向到一个IP地址。就算是转发也要解析到转发服务器IP地址。所以备案是指的空间的备案，而解析指向到的这个空间的所有的域名要填写进去把域名备案。如果你只有域名，不去用，不解析到任何空间，那是不需要备案的。 总之，域名和空间是一体的。备案中都是不可缺少的。

#### 3.6 域名备案才能创建二级域名解析url  （控制台旁边有备案引导）
- 申请服务号
- 一步步来
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-07_235843.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-06-08_094922.png)
- 苦尽甘来
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180713162250.png)
