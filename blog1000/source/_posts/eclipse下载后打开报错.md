---
title: eclipse 下载打开报错
date: 2017-11-29 15：00
tags:   skills java
categories: 报错处理
---
<h3>1.报错图示</h3>
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-09_135434.png)
<h3>2.问题所在,/h3>
- eclipse版本与jdk版本位数不一致
-  Eclipse.ini文件配置信息出错。
- Eclipse安装目录有特殊字符（例如：#，！，@等）。
- 你所使用的Eclipse版本可能太新导致JVM不支持。
<h3>2.解决方法</h3>
- 推荐在[官网下载](http://www.eclipse.org/downloads/) Eclipse OXYGEN
- 添加
```txt
-vm
C:\Program Files\Java\jdk1.7.0_71\bin\javaw.exe
```
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-09_150328.png)
