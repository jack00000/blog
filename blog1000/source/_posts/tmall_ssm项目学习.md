---
title: tmall_ssm项目学习
date: 2018-5-21 20:13:10
tags: java
categories: 实验项目
---

<h3>完整教程：[how2j](http://how2j.cn/k/tmall_ssm/tmall_ssm-1402/1402.html)</h3>

## 备忘

<h3>配置运行</h3>
- 坑1 tomcat的配置 用how2j提供的jdk （我用官网最新的报错） tmall_ssm
![](http://oyiuebawz.bkt.clouddn.com/2018-03-18_160629.png)
![](http://oyiuebawz.bkt.clouddn.com/2018-03-18_163815.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-18_170340.png)


- 坑2 后台配置运行 jre路径改为jdk路径 tmall_ssm admin
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-18_163958.png)
- 前台配置运行 tmall_ssm forehome
![](http://oyj1fkfcr.bkt.clouddn.com/2018-03-18_165451.png)
- [8080端口占用](http://how2j.cn/k/tomcat/tomcat-portfix/545.html)
- unable to ping server 1099 -> jdk版本与tomacat所需jdk版本不一致   [tomcat的配置错误姐姐方法](http://how2j.cn/k/tomcat/tomcat-faq/1132.html#step4480)
- maven项目构建不成功，除以上问题还有各种各样的花式错误，这次我idea，jdk，tomcat全部下载安装最新版 并配置环境变量。demo一次成功
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-22_210924.png)
- 新建maven项目 无resource java文件夹解决方法
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-22_225211.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-22_225827.png)
- autowired 报错等级  [解决方法](http://how2j.cn/k/idea/idea-autowired/1391.html)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-05-23_011259.png)

## 实操
### 1.数据库设计：
- 一共9张表 一进后台 商品得分类`分类表` 如吃穿住行  然后看属性`属性表` 如颜色 价格  再看具体产品`产品表`  下单时 一类产品对应一个订单项`订单项表`  多个订单项组成订单`订单表`   `属性值表`（有点困惑 ，放在一起没问题啊） 有买卖就有哦名家`评价表`  `产品图片表`（有点困惑 应该属于产品信息一类，而产品详情是属性和属性值）  最后一个就是用`用户表`啦。。。
- 表之间的关系 （用来设置主键 外键 注意 多对多绝对能拆成多对一对多之类的，不要有多对多出现） 盗张图
![](http://stepimagewm.how2j.cn/6041.png)
- 表的属性项   通常建表的注意事项：id属性必有 id int(11) not null auto_increment / 初始属性值default null / 主键约束 primary key（id） 外键约束 constraint  约束名 foreign key（cid）reference 外键坐在的表 （id）/ 结尾设置字符格式 engine=innodb default charset=utf8;
```Java
user categroy表对别的属性只有一对多
create table user(
  id int(11) not null auto_increment,
  name varchar2(255) default null,
  password varchar2(255) default null,
  primary key (id)
  )engine=innodb default charset=utf8;

create table category(
  id int(11) not nul auto_increment,
  name varchar2(225) default null,
  prinary key(id)
  )engine=innodb default charset=utf8;

create table proprety(
  id int(11) not null auto_increment,
  cid int(11) default null,
  name varchar2(225) default null,
  constraint fuck_property_categroy foreign key(cid) reference category(id)
  )  engine=innodb default charset=utf8;

以管窥豹。。。
```

### 2.[导入数据库文件](https://jack00000.github.io/2018/03/18/cmd%20%E6%93%8D%E4%BD%9Cmysql%E6%95%B0%E6%8D%AE%E5%BA%93/)

### 3.写后台 CURD +分页  [数据渲染到界面显示基本原理](https://jack00000.github.io/2018/03/18/%E5%90%8E%E5%8F%B0%E6%9F%A5%E8%AF%A2/)

#### 3.1 遇到直接问题  那些配置文件 知道作用 但不知道整个怎么写 无法新建自己写配置文件  自己写的 报错搞死人

#### 3.2 慢慢来 第一个配置文件  log4j.properties   需要懂的基本点
- log4j.rootLogger=ERROR, stdout   //error  意思是低于error级别不log  stdout  是输出到控制台  R则是滚动输出到文件
- log4j.logger.com.how2java.tmall=TRACE  // com.how2java是唯一标示名  不知道啥意思 结果是控制台每条信息 第一个就是trace
- 下面是控制台输出设置
- log4j.appender.stdout=org.apache.log4j.ConsoleAppender
- log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
- log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n  //文件格式
- 下面是文件基本信息如：文件名是example.log,文件最大100k, 最多滚动5个文件    输出设置
- log4j.appender.R=org.apache.log4j.RollingFileAppender
- log4j.appender.R.File=example.log  //文件在项目根目录文件夹下
- log4j.appender.R.MaxFileSize=100KB
- log4j.appender.R.MaxBackupIndex=5
- 下面是文件输出设置 （与控制台大同小异）
- log4j.appender.R.layout=org.apache.log4j.PatternLayout
- log4j.appender.R.layout.ConversionPattern=%p %t %c - %m%n
- [how2j的完整教程](http://how2j.cn/k/log4j/log4j-config/1082.html#step4162)  [自己学的时候写的](https://jack00000.github.io/2018/03/18/Log4j/)

#### 3.3 第二个配置文件 jdbc.properties   jdbc 主要信息 肯定为了使用方便才这样搞
- root  //账号
- admin //密码
- jdbc.driver=com.mysql.jdbc.Driver  //依赖包
- jdbc.url=jdbc:mysql://localhost:3306/tmall_ssm?useUnicode=true&characterEncoding=utf8  //记不住  tmall_ssm数据库名

#### 3.4 第三个配置文件 applicationContext.xml
- 1.文件头
```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
     http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
```
1、xmlns="http://www.springframework.org/schema/beans"
声明xml文件默认的命名空间，表示未使用其他命名空间的所有标签的默认命名空间。

2、xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
声明XML Schema 实例，声明后就可以使用 schemaLocation 属性了

3、xmlns:aop="http://www.springframework.org/schema/aop"
声明前缀为aop的命名空间，后面的URL用于标示命名空间的地址不会被解析器用于查找信息。其惟一的作用是赋予命名空间一个惟一的名称。当命名空间被定义在元素的开始标签中时，所有带有相同前缀的子元素都会与同一个命名空间相关联。

4、xsi:schemaLocation="
http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
这个从命名可以看出个大概，指定Schema的位置这个属性必须结合命名空间使用。这个属性有两个值，第一个值表示需要使用的命名空间。第二个值表示供命名空间使用的 XML schema 的位置

- 2.启动注解识别
```java
<context:annotation-config />
    <context:component-scan base-package="com.how2java.tmall.service" />

```

- 3.导入数据库配置文件
```java
<context:property——placeholder location="classpath:jdbc.properties"/>
```
- 4.  配置数据库连接池  //记不住+1
```spring
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
        <!-- 基本属性 url、user、password -->
        <property name="url" value="${jdbc.url}" />
        <property name="username" value="${jdbc.username}" />
        <property name="password" value="${jdbc.password}" />

        <!-- 配置初始化大小、最小、最大 -->
        <property name="initialSize" value="1" />
        <property name="minIdle" value="1" />
        <property name="maxActive" value="20" />

        <!-- 配置获取连接等待超时的时间 -->
        <property name="maxWait" value="60000" />

        <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
        <property name="timeBetweenEvictionRunsMillis" value="60000" />

        <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
        <property name="minEvictableIdleTimeMillis" value="300000" />

        <property name="validationQuery" value="SELECT 1" />
        <property name="testWhileIdle" value="true" />
        <property name="testOnBorrow" value="false" />
        <property name="testOnReturn" value="false" />

        <!-- 打开PSCache，并且指定每个连接上PSCache的大小 -->
        <property name="poolPreparedStatements" value="true" />
        <property name="maxPoolPreparedStatementPerConnectionSize"
                  value="20" />
    </bean>
```
- 5.Mybatis的SessionFactory配置  // 现在的目的是自己另外新建也能搞出来，想自己打配置文件现在不能
```xml
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="typeAliasesPackage" value="com.how2java.tmall.pojo" /> //包名换成自己的
        <property name="dataSource" ref="dataSource"/>
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
        <!--分页插件，目前先注释，后面重构的时候才会使用
        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor">
                    <property name="properties">
                        <value>
                        </value>
                    </property>
                </bean>
            </array>
        </property>
        -->
    </bean>
```

- 6.<!--Mybatis的Mapper文件识别-->
- ```xml
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.how2java.tmall.mapper"/>  //包名换
    </bean>

#### 3.5 第四个配置文件  pom.xml 配置相关jar包 自己看着改 <groupId>com.how2java.tmall</groupId>
  <artifactId>tmall_ssm</artifactId>



  ```








```
-
-
-
