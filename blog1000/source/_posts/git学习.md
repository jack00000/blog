---
title: git学习
date: 2018-2-3 20:13:10
tags:
categories: skills   
---

- 当你在一台没有建立连接的电脑 git add . ->git commit -m->git push时
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-16_120917.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-16_121022.png)

- [git版本回溯](https://www.liaoxuefeng.com/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/0013743256916071d599b3aed534aaab22a0db6c4e07fd0000)
- mkdir 文件
- cd 文件
- pwd
- git init
- git commit
- git log
- git status /git status 文件名
- git diff  /git diff 文件名
- git reset --hard 序列号

---
- git本地与github建立连接  [完整教程](http://www.360doc.com/content/17/0520/10/43284313_655499212.shtml)
- 成功截图
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-16_142058.png)

- git push 报错（出现这种情况的原因是因为git仓库中已经有一部分代码，所以它不允许你直接把你的代码覆盖上去。第一种解决方法是强推
即利用强覆盖方式用你本地的代码替代git仓库内的内容git push -f）
![](http://oxz3x2njl.bkt.clouddn.com/2018-03-19_181538.png)
![](http://oxz3x2njl.bkt.clouddn.com/2018-03-19_181532.png)
