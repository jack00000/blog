---
title: kali报错及解决方法
date: 2018-2-3 20:13:10
tags:
categories: skills   
---

- 下列签名无效 GPG错误
```
apt-key adv --key hkp://keys.gnupg.net --recv-keys 7D8D0BF6
```

- hash效验和不符
```
一条命令搞定它：sudo rm /var/lib/apt/lists/* -vf
```
- 然后你就可以继续：sudo apt-get update了。
![](http://oyj1fkfcr.bkt.clouddn.com/2018-02-06_095953.png)
