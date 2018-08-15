---
title: Java Spring Boot企业微信点餐系统的坑
date: 2018-7-28 20:13:10
tags:
---


## 遇坑集合
- 配置数据没设置UTF-8编码(记不住) 导致数据库乱码
- 没有设置spring-boot-starter-parent版本与教程一致  导致jpa.findOne()等方法报错
- 忘记写@Runner(SpringRunner.class)+@SpringBootTest注解导致测试类方法一直不成功
- 删除啦默认构造函数导致jpa默写方法失效报错No identifier specified for entity
- 还没搞清楚Date怎么自动记录当前时间并提交
- @Id 忘啦设置导致No identifier specified for entity
- @Service 忘啦 报错Error creating bean with name
- java.util.Date 测试报错could not execute statement  换成java.sql.Date 并在数据库端更改字符集为utf8 测试sava()成功

- 不等价(测试时遇到的)
```java
List<ProductInfo>productInfos=null;
//正常错、对
 Assert.assertNotEquals(0,productInfos.size());
 //测试一直对
 Aseert.assertNouNull(productInfos);

```

## 好的东西
- lombok插件 +@Date注解=get/set/toString()
- springboot框架注解
- 按ctrl+鼠标点方法 可以看到源代码
- 统一api接口返回的json格式。
