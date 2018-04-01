---
title: springboot 学习
date: 2018-3-29 20:13:10
tags: java
categories:
---

<h3>完整教程：[how2j](http://how2j.cn/k/springboot/springboot-eclipse/1640.html)</h3>

<h3>备忘</h3>
- springboot 本质是maven项目
- 第一次生成springboot 耗时 15分钟
- 遇到问题 没找到jdk（由于目录名改啦）  重新指定 rebuilt 运行java类 成功
- 截图
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-29_125935.png)

- （找到项目工程目录）生成jar 报错 无法将“mvn”项识别为 cmdlet。。。 未解决
- 配置maven环境变量  [教程](http://xinzhi.wenda.so.com/a/1517891076619727)\
- 截图
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-29_154842.png)
- mvn install 成功生成jar
- 截图
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-29_155628.png)
- 运行jar  java -jar target/springboot-0.0.1-SNAPSHOT.jar  （在target里面）
- 截图 启动失败 端口8080被占用
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-29_160003.png)
- 截图 成功
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-29_155156.png)
- 通过这种方式，把此jar上传到服务器并运行，就可达到部署的效果了。 皮

- springboot  使用  jsp
- 自动调试 power save model  代码不会自动提示、
- 运行报错 （配置文件问题） 控制台没报错  (idea没有自动生成webapp 怀疑是这个问题)
- 截图
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-29_162306.png)
- 隔一天 再运行 成功 截图、
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-31_220906.png)

- 热部署 控制台 运行springboot时  修改 hellocontrol  控制台自动更新  （只需在pom.xml 配置一下）
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-30_093857.png)



-
