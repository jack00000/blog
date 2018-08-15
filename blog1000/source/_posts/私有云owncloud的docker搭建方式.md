---
title: 私有云owncloud的docker搭建方式
date: 2018-8-2 20:13:10
tags:

---


# [靠谱教程](https://www.moerats.com/archives/420/)
## 报错
- 端口80被占用
- 解决方法：netstat -anp 4|grep 80
- 发现80端口被httpd占用
- kill 6487

![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180804122224.png)

## 命名重复 nc
- docker rm nc

![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180804121756.png)

## 在试啦20多篇坑爹教程后 LZ终于成功啦！！！！！！！！！！！！！！！！

![](http://pcf4bb3fk.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180804123814.png)
