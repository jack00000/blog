---
title: Hadoop大数据技术
date: 2018-9-8 20:13:10
tags: java
categories: 大数据
---

# 大数据不是某个专业或一门编程语言，实际上它是一系列技术的组合运用。 [视频教程](https://www.imooc.com/video/7644) [知乎学习大数据路线](https://www.zhihu.com/question/35942305)\
- 大数据 = 编程技巧 + 数据结构和算法 + 分析能力 + 数据库技能 + 数学 + 机器学习 + NLP + OS + 密码学 + 并行编程
- 主要分为 7 个阶段：入门知识 → Java 基础 → Scala 基础 → Hadoop 技术模块 → Hadoop 项目实战 → Spark 技术模块 → 大数据项目实战。

# 安装hadoop
## 1.在linux云服务器配置java环境
## 2.下载hadoop 并修改hadoop四个配置文件
## 怎么用：eg:大量购物数据输入->map编程->reduce编程->推荐你感兴趣的商品。

## HDFS 分布式文件
- 块:固定大小的文件存续的逻辑单元 hadoop 默认64MB
- namecode：管理节点 存放文件元数据 1.文件->数据块的映射表 2.数据块->数据节点datanode的映射表
- 客户端通过访问namenode的元数据 得知文件存在哪个datanode
- datacode：数据节点 数据块的集合
- datanode->心跳检测->namenode
- 二级namenode 元数据备份到Secondary namenode 防止namenode挂掉 导致无法访问元数据。
- 写入过程:1.文件拆分成块 2.访问namenode得到空闲块 3.写入相应的datanode 4，流水线复制 5.更新元数据
![](http://oyj1fkfcr.bkt.clouddn.com/2018-09-09_160549.png)

### 数据管理策略
- 每个数据块多个副本 分布在不同机架(datanode的集合)的datanode中
- 机架/或节点挂啦 其他一样访问

### HDFS 特点
- 三个副本 数据冗余 硬件容错、
- 一读多写 写入之后没法修改
- 适合存储大文件


## MapReduce :Master-worker构架
- eg:1000副牌 少啦一张
- 分100个人做
- 先map会得到 100个人算的数据
- 不同牌给不同reduce再次汇总 得出最终结果

### 运行流畅
- job->多个task(map任务+reduce任务)
- jobTracker：1.作业调度 2.分配任务 3.检测jobTracker状态
- taskTracker: 1.汇报状态

![](http://oyj1fkfcr.bkt.clouddn.com/2018-09-09_162640.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-09-09_162624.png)

### 容错机制
- 1.重新执行 4次依旧报错 ->推测执行
- 2.推测执行 多弄几个taskTracker 一个执行完就关掉其他的


## 实例：单词计数
- 输入一段文本 输出每个单词的个数。

# hadoop 2.0 多啦YRAN 分布式文件管理
