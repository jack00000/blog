---
title: deppin安装mysql
date: 2018-7-10 20:13:10
tags:
categories:
---

### sudo apt-get install mysql-server
- 会提示输入密码

### sudo apt-get install mysql-client

### sudo apt-get install libmysqlclient-dev

### sudo netstat -tap | grep mysql
- 如果看到有mysql 的socket处于 listen 状态则表示安装成功。
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180710081207.png)

### mysql -u root -p linux//linux是我自己设置的密码
- show databases;
- use 数据库名；
- show tables；
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180710081646.png)
