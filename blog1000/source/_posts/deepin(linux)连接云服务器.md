---
title: deepin(linux)连接云服务器
date: 2018-7-20 20:13:10
tags: java
categories: 无锡实训java
---

### windows用的是WINSCP(GUI)和cecuret-ssh(终端)
- winscp方便上传文件
- cecuret-ssh方便部署命令语句的填写。

### deepin(linux) 没有WINSCP(GUI)和cecuret-ssh(终端) 怎么办？

#### ssh [deepin官网教程](https://wiki.deepin.org/index.php?title=SSH%E6%9C%8D%E5%8A%A1)
- sudo apt-get install openssh-client
- ssh root@47.95.236.184
- 之后操作一样
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180721110925.png)

#### winscp(替代工具)GFTP  [deepin官网教程](https://wiki.deepin.org/index.php?title=GFTP#.E4.BB.93.E5.BA.93.E5.9C.B0.E5.9D.80)
- sudo apt-get install gftp
- 终端输入gftp  即可使用
- 注意：协议是SSH2 其他协议 内容显示不出来
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180721111518.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180721112729.png)
