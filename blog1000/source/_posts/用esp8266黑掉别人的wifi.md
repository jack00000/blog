---
title: 用esp8266黑掉别人的wifi
date: 2018-1-19 20:13:10
tags:
categories: skills   
---
<h3>1.准备工作</h3>
-  [esp8266开发板](https://item.taobao.com/item.htm?id=543816073624&price=21.9&original_price=25.9&sourceType=item&sourceType=item&suid=bf50bdf1-3872-4055-add5-3f738236a360&ut_sk=1.V3WygUQV7AgDAH6XnpZjAxxU_21646297_1516364512255.TaoPassword-QQ.1&un=0375a7e91b434ec8862a58ce32fa627c&share_crt_v=1&cpp=1&shareurl=true&spm=a313p.22.iq.90019558535&short_name=h.Z0Zi6P5&app=chrome&price=21.9&original_price=25.9&sourceType=item&sourceType=item&suid=bf50bdf1-3872-4055-add5-3f738236a360&ut_sk=1.V3WygUQV7AgDAH6XnpZjAxxU_21646297_1516364512255.TaoPassword-QQ.1&un=0375a7e91b434ec8862a58ce32fa627c&share_crt_v=1&cpp=1&shareurl=true&spm=a313p.22.iq.90019558535&short_name=h.Z0Zi6P5&app=chrome)
- [脚本烧录工具](https://github.com/nodemcu/nodemcu-flasher/tree/master/Win64/Release)

- [脚本](https://github.com/spacehuhn/esp8266_deauther/releases)

<h3>2.具体步骤</h3>
- 用usb将esp8266链接到电脑 正常情况下会显示如下：
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-19_204433.png)

- 配置中遇到问题：设备管理器无端口选项
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-19_190328.png)
- 解决方法：
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-19_204711.png)

- 烧录软件设置
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-19_204849.png)
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-19_204900.png)
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-19_201947.png)

<h3>3. 烧录完成后的使用（烧录后板子的灯不亮，刚开始以为没烧好。。。。。）</h3>
- 连接无线网pwned   密码为deauther

- [下载DeautherAPP](https://github.com/ExploiTR/DeAutherDroid/releases)   

- 先scan查找再选择wifi进行攻击
![](http://oxz3x2njl.bkt.clouddn.com/4A3EC7FCDFF7B2AAD0D2AC06DB69984D.png)
- 选择攻击方式
![](http://oxz3x2njl.bkt.clouddn.com/EDEB88414406238FF424E75341F7F5EE.png)
- beacon 生成多个wifi
![](http://oy5lsbw4v.bkt.clouddn.com/6FC50B416862161131EA0911A407E827.png)
![](http://oy5lsbw4v.bkt.clouddn.com/2018-01-19_212025.png)

- 游览器登陆192.168.4.1
![](http://oxz3x2njl.bkt.clouddn.com/2018-01-19_210117.png)
