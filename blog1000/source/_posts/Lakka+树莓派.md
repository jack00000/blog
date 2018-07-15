---
title: 怀旧游戏平台Lakka
date: 2018-4-2 15：00
tags:
categories: skills
---

### 完整教程  [树莓派实验室](http://shumeipai.nxez.com/2016/06/20/lakka-let-raspberry-pi-turned-around-game-simulator.html)

### 基本步骤
- win32diskimage 烧录Lakka
- 启动树莓派 并联局域网加入游戏rom到roms (网络共享/winscp)

### 遇到问题
- [tf卡低级格式化工具 sdformatter](http://www.33lc.com/soft/35567.html)
- tf卡刷过linux 导致写入失败 提示：拒绝访问 ——》这个报错多半是tf卡的问题   不要快速格式化 要低级格式化(低格之前有两个盘 容量加起来还没1g)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-04-04_183742.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-04-04_223154.png)
### 成功提示
![](http://oyj1fkfcr.bkt.clouddn.com/2018-04-04_224450.png)
- 不知道为什么 本来低格剩下一个盘  成功它弹出另一个盘让你格式化 别格式化。。。。。。。
### 给树莓派通电 连接显示器
![](http://oyj1fkfcr.bkt.clouddn.com/BB350CDCA8D9EE5CF33311A4BA0A5E96.jpg)
### 给Lakka装游戏
- 打开Lakka的文件共享服务和ssh服务 设置（第二个图标）-> Lakka service
![](https://images2017.cnblogs.com/blog/35070/201801/35070-20180115040005615-352805446.jpg)
- 我的电脑 -》网络 -》局域网共享手段失败
- winsp 软件 （需获得树莓派IP）  类似ftp服务器客户端   输入树莓派IP  用户名 root 密码 root
- 连接后传入游戏压缩包到roms  注意：刷进去后 重启还要等一段时间 系统才打得开。
- 然后scan 扫描你的roms文件夹  完成（不完成也行） 到第一个图标 用核心打开的游戏 注意：有的核心不适应你选的游戏 NES第三个 我用着没问题
![](http://oyj1fkfcr.bkt.clouddn.com/C15130F3540FDFCC9E401852AB2BC1BB.jpg)
![](http://oyj1fkfcr.bkt.clouddn.com/C8078C95754B42462B63450A1B05A030.jpg)
![](http://oyj1fkfcr.bkt.clouddn.com/513FC60969F819620DC70C102EFB04D5.jpg)

- 游戏实例
![](http://oyj1fkfcr.bkt.clouddn.com/8D7BB16731FAEACFACF66E4ACA16D9A0.jpg)
![](http://oyj1fkfcr.bkt.clouddn.com/A648C266AD2E2EBDC18FDF11D1D7A3B7.jpg)
- 手柄推荐 hori
- Lakka 属于pc端  手柄连接时 要等一段时间 2号灯常亮则连接成功
![](http://oyj1fkfcr.bkt.clouddn.com/E799A3146ADEEF699EEF99F5FC3A3017.jpg)
