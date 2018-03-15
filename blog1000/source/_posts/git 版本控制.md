---
title: GIT 版本控制 hexo blog
date: 2017-8-15 20:13:10
tags: CODE 2
---
<h3>完整教程：[廖雪峰](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/0013743858312764dca7ad6d0754f76aa562e3789478044000)</h3>
- mkdir 文件名
- cd 文件名
- pwd   //显示当前路径
- git init

- 修改
- git add .
- git commit -m "version"
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-05_194256.png)
- 版本回退
- git log  //所有提交的commit 版本
- git status //当前状态
- git diff //查看改的地方
- git reset --hard HEAD~3  //往上跳3个
- git reset --hard 6361683 // 版本序列号前几位

![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-05_194307.png)
- commit 是增加/删除文件的改变 只改变文件内容，不改变文件多少-》no change to commit-》fuck？
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-05_202932.png)

- 常用命令
- cd ..
