---
title: ssm框架剖析
date: 2018-8-21 20:13:10
tags:
---

# SSM=spring+springMVC+mybatis [demo]() [how2j](http://how2j.cn/k/ssm/ssm-tutorial/1137.html#step4514)
- 1.主要配置文件web.xml(maven项目自带) applicationContext.xml springMVC.xml
- 2.有些项目有如jdbc.properties 等.properties文件 这些文件是供xml使用的属性值文件。
- 3.方法与sql映射配置文件eg:Category.xml 可以由注解代替。

## web.xml 项目启动第一个加载的配置文件
- 1.配置加载spring配置文件applicationContext.xml 完成初始化工作(eg:配置mybatis数据库连接)
- 2.配置加载springMVC配置文件springMVC.xml 完成请求处理与转发

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:web="http://java.sun.com/xml/ns/javaee"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" version="2.5">

  <!-- spring的配置文件-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!-- spring mvc核心：分发servlet -->
  <servlet>
    <servlet-name>mvc-dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- spring mvc的配置文件 -->
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springMVC.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>mvc-dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>

</web-app>
```

## applicationContext.xml
- 1.配置扫描 将bean mapper service 生命周期纳入spring 管理
- 2.配置扫描 使 方法与sql映射配置文件eg:Category.xml  生效 (mybitais的sqlSession有关)
- 3.配置mybatis 完成数据库初始化工作
- 还可以配置
- 4.数据库连接池
- 5.对事务transaction配置
- 6.分页插件配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx" xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="
     http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
     http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
     http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
     http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
     http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
     http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <context:annotation-config />
    <context:component-scan base-package="com.fangjun.bean.service" />

    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName">
            <value>com.mysql.jdbc.Driver</value>
        </property>
        <property name="url">
            <value>jdbc:mysql://localhost:3306/how2java?characterEncoding=UTF-8</value>

        </property>
        <property name="username">
            <value>root</value>
        </property>
        <property name="password">
            <value>admin</value>
        </property>
    </bean>

    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="typeAliasesPackage" value="com.fangjun.bean.bean" />
        <property name="dataSource" ref="dataSource"/>
        <property name="mapperLocations" value="classpath:com/fangjun/bean/mapper/*.xml"/>
    </bean>

    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.fangjun.bean.mapper"/>
    </bean>

</beans>
```

## springMVC.xml
- 1.配置注解驱动 以使得访问路径与方法的匹配可以通过注解配置
- 2.配置静态文件加载支持 如html,css,js,images可以访问
- 3.视图定位
- 4.扫描Controller,并将其生命周期纳入Spring管理

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx" xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-3.0.xsd
        http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd">

    <context:annotation-config/>

    <context:component-scan base-package="com.fangjun.bean.controller">
        <context:include-filter type="annotation"
                                expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <mvc:annotation-driven />

    <mvc:default-servlet-handler />

    <!-- 视图定位 -->
    <bean
            class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass"
                  value="org.springframework.web.servlet.view.JstlView" />
        <property name="prefix" value="/WEB-INF/jsp/" />
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```

## pom.xml 以上3个配置没问题 再报错 多半包没导

```java
  ....
<properties>
  <spring.version>4.1.3.RELEASE</spring.version>
  <pagehelper.version>5.1.2-beta</pagehelper.version>
  <mysql.version>5.1.6</mysql.version>
  <mybatis.spring.version>1.2.3</mybatis.spring.version>
  <mybatis.version>3.1.1</mybatis.version>
  <junit.version>4.12</junit.version>
  <jstl.version>1.2</jstl.version>
  <jsqlparser.version>1.0</jsqlparser.version>
  <jackson.version>1.2.7</jackson.version>
  <servlet-api.version>3.1.0</servlet-api.version>
  <druid.version>1.0.18</druid.version>
  <log4j.version>1.2.16</log4j.version>
  <commons-logging.version>1.2</commons-logging.version>
  <commons-fileupload.version>1.2.1</commons-fileupload.version>
  <commons-io.version>1.3.2</commons-io.version>
  <commons-lang.version>2.6</commons-lang.version>
  <aopalliance.version>1.0</aopalliance.version>
  <mybatis-generator.version>1.3.5</mybatis-generator.version>
</properties>
  ....
```

![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-30_113820.png)


## 问题记录
### 1.jsp 中文乱码声明与c标签使用

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
```
### 2.后台没有收到form表单提交的数据
- 代码出错/springMVC.xml配置文件出错
- 吐槽一下：肉眼看自己写的配置文件和教程没区别 结果就是配置文件错啦。

### 3.单独说一下文件上传 eg:图片
- 1.新建类接收img数据
- 2.在工程目录创建文件夹存储图片 并将文件名设置为 改字段所在表的唯一标示id 方便取用

- 3.通过session获取ControllerContext,再通过getRealPath定位存放分类图片的路径。
如果严格按照本教程的做法，使用idea中的tomcat部署的话，那么图片就会存放在:E:\project\tmall_ssm\target\tmall_ssm\img\category 这里
- 4. 根据分类id创建文件名
- 5. 如果/img/category目录不存在，则创建该目录，否则后续保存浏览器传过来图片，会提示无法保存
- 6. 通过UploadedImageFile 把浏览器传递过来的图片保存在上述指定的位置
- 7. 通过ImageUtil.change2jpg(file); 确保图片格式一定是jpg，而不仅仅是后缀名是jpg.
- 8. 客户端跳转到admin_category_list

```java
RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        System.out.println(c.getId());
        categoryService.add(c);
        System.out.println(c.getId());

        //1.根据上下文 新建文件夹  在target目录下
        File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        //2.新建文件 此时文件没东西
        File file = new File(imageFolder,c.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        System.out.println(uploadedImageFile);
        System.out.println(uploadedImageFile.getImage());
        System.out.println(file);
        //3.向文件放传过来的图片
        uploadedImageFile.getImage().transferTo(file);
        //4.用工具类 转换文件格式
        BufferedImage img = ImageUtil.change2jpg(file);
        //5.再次写入文件
        ImageIO.write(img, "jpg", file);

        return "redirect:/admin_category_list";
    }
```

### 中文乱码问题
- 1.web.xml+.jsp配置
- 2.一个后端字符编码设置 一个前端字符编码设置。
- 3.数据库编码utf8别忘啦

```xml
<!--中文过滤器-->
  <filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
      <param-name>encoding</param-name>
      <param-value>utf-8</param-value>
    </init-param>
  </filter>
  <filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
```

```java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
```

### 分页如何实现的 limit函数方式(每点一次页标都会改变 #{start}，#{count} 再次从数据库/session(一级缓存) sessionFactory(二级缓存)查询数据)
- 1.新建Page对象(1.开始页数strat 2.每页显示个数count 3.数据总个数total 4.参数param)
- 2.数据库limit函数 select * from category limit #{start}，#{count}
- 3.所以分页 需要传Page对象 因为需要参数1.开始页数strat(默认为表的第一项) 2.每页显示个数count(默认为所有)
- 4.返回的是 id从  #{start}到 #{start}+#{count}的数据集合(X) id从#{start}开始 以#{count}为一页的数据集合(x)
- limit函数:从查询的结果集仅提取一部分数据(v)。
- 5.如何使用
- 6.初始化Page对象时 count=defaultCount=5 service取得数据total传入Page
- 7.直接显示数据集合 只显示第一页
- 8.引入分页jsp 配合Page对象的共有方法 控制数据集合不同页之间的跳转。
- 9.每点一次页标都会改变 #{start}，#{count} 再次查询数据

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<script>
$(function(){
    $("ul.pagination li.disabled a").click(function(){
        return false;
    });
});

</script>

<nav>
  <ul class="pagination">
    <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
      <a  href="?start=0${page.param}" aria-label="Previous" >
        <span aria-hidden="true">«</span>
      </a>
    </li>

    <li <c:if test="${!page.hasPreviouse}">class="disabled"</c:if>>
      <a  href="?start=${page.start-page.count}${page.param}" aria-label="Previous" >
        <span aria-hidden="true">‹</span>
      </a>
    </li>

    <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
                <li <c:if test="${status.index*page.count==page.start}">class="disabled"</c:if>>
                <a
                href="?start=${status.index*page.count}${page.param}"
                <c:if test="${status.index*page.count==page.start}">class="current"</c:if>
                >${status.count}</a>
            </li>

    </c:forEach>

    <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
      <a href="?start=${page.start+page.count}${page.param}" aria-label="Next">
        <span aria-hidden="true">›</span>
      </a>
    </li>
    <li <c:if test="${!page.hasNext}">class="disabled"</c:if>>
      <a href="?start=${page.last}${page.param}" aria-label="Next">
        <span aria-hidden="true">»</span>
      </a>
    </li>
  </ul>
</nav>
```
![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-31_125919.png)

### 使用 pageHelper分页插件来实现。
- 1.不需要total()获取数据总数 list(Page page)函数 指定分页参数
- 2.PageHelper.offsetPage(page.getStart(),page.getCount()); 代替 limit #{start}，#{count} 指定分页参数。
- 3.int total = (int) new PageInfo<>(cs).getTotal(); 代替 total() 获取数据总数
- 4.其他不变
- 5.需要在applicationContext.xml配置

```xml
<!--Mybatis的SessionFactory配置-->
   <bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
       <property name="typeAliasesPackage" value="com.how2java.tmall.pojo" />
       <property name="dataSource" ref="dataSource"/>
       <property name="mapperLocations" value="classpath:mapper/*.xml"/>

       <property name="plugins">
           <array>
               <bean class="com.github.pagehelper.PageInterceptor">
                       <value>
                       </value>
                   </property>
               </bean>
           </array>
       </property>
   </bean>
```

### 自动生成bean mapper mapper.xml  简化开发
- 1.OverIsMergeablePlugin类+MybatisGenerator类+generatorConfig.xml
- 2.OverIsMergeablePlugin类：MybatisGenerator插件是Mybatis官方提供的，这个插件存在一个固有的Bug，即当第一次生成了CategoryMapper.xml之后，再次运行会导致CategoryMapper.xml生成重复内容，而影响正常的运行。
- 3.generatorConfig.xml(1.配置生成文件所在目录 2. 使用OverIsMergeablePlugin插件 3.指定链接数据库的账号和密码，既然是逆向工程，肯定要先链接到数据库才对啊)
- 4.需要额外的Mybatis Generator jar包（mybatis-generator-core-1.3.5.jar） maven项目直接在pom.xml中导入
- 5.MybatisGenerator会生成一个类叫做XXXXExample的。 它的作用是进行排序，条件查询的时候使用。

![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-31_140117.png)

### 生成后list()变为selectByExample(Example example) 必须传参数(eg：id desc )用于设置排序、设置根据id字段查询、、
- xxxxExamole类  辅助查询类

```java
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}
```
```java
@Override
    public List list(int id) {
        CategoryExample example =new CategoryExample();
        //设置根据id字段查询
        example.createCriteria().andIdEqualTo(id);
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }
```

### mybatis生成的项目 启动慢问题 一直不成功。At least one JAR was scanned for TLDs yet contained no TLDs
- 尝试1：改tomcat配置文件何去掉jsp中的注解  [教程](https://blog.csdn.net/suyu_yuan/article/details/51283039)
- 尝试2：改jre配置文件 [教程](https://blog.csdn.net/m0_37602175/article/details/80327422)
- No TLD files were found in resource path
- 什么是TLD?->标签库描述文件，如要在JSP页面中实现JSP标签，必须首先定义实现标签的类，然后在标签库描述文件（TLD）中将写好的类映射成jsp标签,最后在JSP文件中使用定义好的标签，就可以生成动态的JSP内容
- 等啦几天，最终自己好啦。what fuck？
![](http://oyj1fkfcr.bkt.clouddn.com/2018-09-04_121616.png)

### Maven打包报错 No sources to compile  等一下就好
- 网上说不是Maven项目造成的 扯淡

### linux重启tomcat

```java
进入Tomcat下的bin目录
cd /usr/local/tomcat/bin
使用Tomcat关闭命令
./shutdown.sh
找到tomact 进程
netstat –apn | grep 8080
使用检测出的进程号，直接杀死Tomcat进程
kill -9 7010  //7010代表进程号，每次是不一样的
然后继续查看Tomcat是否关闭
ps -ef|grep java
如果出现以下信息，则表示Tomcat已经关闭
root      7010     1  0 Apr19 ?        00:30:30 [java] <defunct>
最后，启动Tomcat
./startup.sh
```

### jar + nohup 永久运行  重启tomcat 对项目不影响。
### war部署 tomcat没有自动解压。一次可能没有 多重启几次tomcat。
### maven打包jar运行  报错 无主清单属性。
#### what ？
- 打包正常 jar运行  报错 无主清单属性
#### why?
- pom.xml程序入口没设置


### jar、war区别 [区别](https://blog.csdn.net/qq_36769100/article/details/78501733)
- jar 运行在java环境 要指定入口类 带main()的类 需要打包第三方jar放在同级目录
- war 运行在tomcat服务器 重启二次服务器自动解压

### war 自动解压后 记得 运行startup.bat (linux：bin下 ./shutdown.sh ./startup.sh)
- 1.不要在idea中运行tomcat 这样运行的是源项目 不是打包的war
- 2.查看启动日志(tail -300f /usr/local/tomcat7/logs/catalina.out) 与8080端口是否处于监听状态(netstat -anp|grep 8080) 判断 tomcat是否启动成功。
- 3.发现自己改啦server.xml 端口号设置为8888 而8888端口被占用啦 netstat -apn|grep 8888
- 4.所以要看启动日志。tomcat start并不代表就成功啦。。。


### win10下 mysql数据库 中文乱码问题 [靠谱教程](https://www.cnblogs.com/forget406/p/5475902.html) linux默认utf8
#### what？
- 1.我cmd创建数据库 CREATE DATABASE tmall_ssm DEFAULT CHARACTER SET utf8;
- 插入时，cmd查看数据库乱码，链接软件查看乱码
- 2.我cmd创建数据库 CREATE DATABASE tmall_ssm  (默认编码是lanti)
- 插入时，cmd查看数据库正常，链接软件查看乱码 what fuck?
- 3.我一个表一个表的创建就没乱码 直接source导入就出现乱码 whatfuck？
- 复制.sql文件的sql语句  到cmd运行 会因为条数太多 执行时报错 一小段一小段运行就可以。。。。
- 4.现在cmd显示正确  链接软件查看乱码  项目运行也是乱码 ？
#### why？
- 1.mysqld 服务端 后台运行 不可见的
- 2.mysql 客户端 mysql控制台访问
- 3.软件/jdbc远程访问 是通过 mysql 客户端 进而访问 mysqld 服务端
- 4.每个层字符集必须一致 否则乱码。

#### how?
- 查看字符集 show variables like 'character_set%';
- 修改my.ini文件 (在安装位置一级目录下) [mysql] default-character-set=utf8 [mysqld] character-set-server=utf8
- 重启mysql：win+r->  net stop mysql -> net start mysql
- 客户端直接导入.sql文件 软件客户端正常 项目jdbc数据正常/ source 导入 软件客户端正常 项目jdbc数据正常 控制台客户端乱码
- 解决方法：[mysql] default-character-set=utf8

- 1.[mysql] default-character-set 是客户端默认字符集。如果采用命令行作为客户端，此字符集必须和命令行默认字符集能够匹配，否则出现中文乱码。
- 2. [mysql]和[mysqld]数据字符集可以不同，因为MySQL服务器配置文件Index.xml如果有预编译编码，服务器是能够识别客户端数据编码并转换成服务器端编码储存在相应的表中。关键是要在Index.xml配置文件中存在此预编译字符集。
- 3.开始尝试解决sqlDevelopor远程登陆MySQL客户端出现中文乱码问题
- 4.必须明确一点，sqlDeveloper首先连接MySQL客户端，然后通过客户端再连接MySQL服务器。因此，必须保证sqlDeveloper和MySQL客户端字符编码集相同。

#### 数据管理系统字符集层次 (全部utf8 cmd乱码 软件客户端正常 项目jdbc数据正常)
　　MySQL数据库字符集具有多层次。在配置文件my.ini中仅定义default-character-set、default-set-server，然而MySQL服务器中却存在多层次的字符集结构。
　　character-set-client 客户端发送给服务器SQL语句字符集
　　character-set-connection sokect连接字符集
　　character-set-results 客户端通过SELECT语句显示数据库查询结果字符集
　　这三种字符集一般是与default-character-set字符集相同的。当然也可以自定义设置。

　　character-set-database 数据库储存的数据字符集
　　character-set-server 服务器字符集
　　character-set-system 系统字符集
　　这三种字符集一般是与default-set-server字符集相同的。当然也可以自定义设置。
　　character-set-filesystem 文件系统字符集
![](http://oyj1fkfcr.bkt.clouddn.com/2018-09-03_103132.png)
