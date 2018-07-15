---
title: docker的安装与使用
date: 2018-7-7 20:13:10
tags:
categories:
---

## [docker非常详细的使用教程](http://www.runoob.com/docker/docker-container-usage.html)
## docker是什么？ docker是系统镜像的容器。
## 可以干嘛？ 在电脑安装docker 在docker里面安装centerOS 就可以模拟centerOS服务器  然后可以向云服务器一样部署项目。


### 安装 (我的电脑系统是国产的linux系统-deepin 推荐使用)
- [官网教程](https://wiki.deepin.org/index.php?title=Docker)
- 已确认每步可行
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180707094930.png)

## 使用
### 安装 镜像
- sudo docker pull ubuntu:16.04
- sudo docker pull centos

### 安装资源
- sudo docker pull nginx
- sudo docker pull mysql
- sudo docker pull tomcat
### 使用镜像
- sudo docker run -t -i ubuntu:16.04 /bin/bash
- 跟阿里云服务器一样使用
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180707100637.png)
