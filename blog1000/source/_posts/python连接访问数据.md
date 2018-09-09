---
title: 廖雪峰python教程学习
date: 2018-9-7 20:13:10
tags: 廖雪峰python教程学习
categories: python
---

## [完整教程](https://www.liaoxuefeng.com/wiki/0014316089557264a6b348958f449949df42a6d3a2e542c000/0014320107391860b39da6901ed41a296e574ed37104752000)

## 备忘
- 下个pycharm吧 命令行一运行带模块的脚本 花式报错  还有一件神奇的事，pycharm内置命令行可以运行成功 cmd不行。。。。WTF
- pip下载包时注意是pip还是pip3 都安吧，懒得找你的项目是用的那个。

- python访问数据库  安装依赖pip install mysql-connector  新建连接实例并sql操作
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_121114.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_114647.png)

- 简单GUI程序 代码命令行运行没对过 下个pycharm ide 一下就对啦   带模块的 命令行不能运行？？？
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_135605.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_135206.png)

- TCP可靠协议   socket端口通信  从新浪爬点数据 (文件名对导包的影响)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_141426.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_153811.png)

- UDP不可靠协议
- ![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_162927.png)
- ![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_162939.png)

### web开发  [廖雪峰完整教程](https://www.liaoxuefeng.com/wiki/0014316089557264a6b348958f449949df42a6d3a2e542c000/001432012393132788f71e0edad4676a3f76ac7776f3a16000)
- WSGI接口：Web Server Gateway Interface   实现web显示
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_163732.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_163719.png)

#### python web开发框架flask 的使用

- 一个URL可以对应GET和POST请求，当然还有PUT、DELETE等请求，但是我们通常只考虑最常见的GET和POST请求。
- 同一个URL/signin分别有GET和POST两种请求，映射到两个处理函数中。
- Flask通过Python的装饰器在内部自动地把URL和函数给关联起来
- Flask自带的Server在端口5000上监听：
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_171714.png)
![](http://oxz3x2njl.bkt.clouddn.com/2018-05-26_172325.png)


#### python MVC   图片来自廖雪峰网站
- 这里model=dict
![](https://cdn.liaoxuefeng.com/cdn/files/attachments/001400339839622665127663fb840b5870864895b103c2f000)
- 改写上一个脚本  把里面的html代码 用专门的html文件存放 成功页面显示你的登录名  而不是输出一成不变的信息
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_180924.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-26_181605.png)


#### 解释一波self (类实例)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-31_211410.png)
