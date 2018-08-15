---
title: deepin(linux)运行tomcat权限问题
date: 2018-8-2 20:13:10
tags:

---






# linux启动tomcat权限不足
touch: 无法创建’tomcat8/logs/catalina.out’: 权限不够
./tomcat7/bin/catalina.sh: 396: ./tomcat7/bin/catalina.sh: cannot create /usr/local/java/tomcat7/logs/catalina.out: Permission denied

# 解决方法
- sudo chown -R 你的用户名 tomcat整个目录
```java
eg:sudo chown -R fangjun tomcat8 (在tomcat8当前目录运行)
发现个奇怪的问题：官网下的tomcat第一次运行 说权限不够。第二次就行了。
从how2j下的tomcat 运行说权限不够，给他权限啦。还是报错。。。。
成功截图
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180729191004.png)

# linux 查看权限 ls -la
- chown 777 文件名 //所有人可读写。
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180729191934.png)
