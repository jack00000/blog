---
title: I/O处理
date: 2017-11-29 15：00
tags:   skills javas
categories: how2j
---

<h3>完整教程：[how2j](http://how2j.cn/k/io/io-file/345.html)</h3>

<h3>备忘</h3>
- 创建文件对象
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-05_210901.png)

- 文件常用方法1
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-05_215351.png)
- 文件常用方法4
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_125613.png)

- 练习1：找出文件夹中最大/最小文件（不包含子文件夹）
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_132237.png)
- 练习2：找出文件夹中最大/最小文件（包含子文件夹）
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_135134.png)


- 文件输入/输出流
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_140244.png)

- 字节流：InputStream字节输入流   OutputStream字节输出流   用于以字节的形式读取和写入数据

- 字节流形式读取文件内容
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_141911.png)
- 以字节流的形式向文件写入数据
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_142633.png)

- 关闭流的方式：在finally中关闭  在try中关闭  f.close();

- 字符流：Reader字符输入流 Writer字符输出流 专门用于字符的形式读取和写入数据

- 使用字符流读取文件
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_143746.png)

- 缓存流：
- BufferedReader  缓存流读取数据 // 缓存流必须建立在一个存在的流的基础上
- PrintWrite 同理
![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_145103.png)

- flush   :有的时候，需要立即把数据写入到硬盘，而不是等缓存满了才写出去。 这时候就需要用到flush



- 数据流：DataInputStream 数据输入流  DataOutputStream 数据输出流

- 对象流： 对象流指的是可以直接把一个对象以流的形式传输给其他的介质，比如硬盘 一个对象以流的形式进行传输，叫做序列化。 该对象所对应的类，必须是实现Serializable接口

- Scanner读取字符串  Scanner s = new Scanner(System.in);  一行一行读

![](http://oyj1fkfcr.bkt.clouddn.com/2018-01-06_150005.png)
