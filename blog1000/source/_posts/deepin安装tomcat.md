---
title: deepin安装tomcat
date: 2018-7-16 20:13:10
tags: java
categories:
---

## [原始教程](https://blog.csdn.net/guanripeng/article/details/79632924)

### [下载](https://tomcat.apache.org/download-80.cgi) 并解压

```java
tar -xzvf  apache-tomcat-8.5.32.tar.gz -C/home/fangjun/Documents/tomcat
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716224600.png)
### 启动服务
- 进入解压目录的bin文件夹，运行命令./startup.sh
- 如果显示tomcat start 则说明安装成功
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716225249.png)

## deepin配置tomcat环境  [原始教程](https://blog.csdn.net/jie873440996/article/details/22795945)
- deepin(linux)配置环境变量都在etc/profile里面。
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180716232629.png)

- 更新profile： source etc/profile
- 获取路径：echo $CATALINA_HOME
