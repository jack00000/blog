---
title: mysql数据库操作
date: 2017-11-29 15：00
tags:   skills java
categories: skills
---

<h3>完整教程：[how2j](http://how2j.cn/k/jdbc/jdbc-statement/387.html#nowhere)</h3>

<h3>备忘</h3>
- 连接数据库 导jar包 和代码如下
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_150902.png)
```java
public class TestJDBC {
    public static void main(String[] args) {

        //初始化驱动
        try {
            //驱动类com.mysql.jdbc.Driver
            //就在 mysql-connector-java-5.0.8-bin.jar中
            //如果忘记了第一个步骤的导包，就会抛出ClassNotFoundException
            Class.forName("com.mysql.jdbc.Driver");

            // 建立与数据库的Connection连接
            // 这里需要提供：
            // 数据库所处于的ip:127.0.0.1 (本机)
            // 数据库的端口号： 3306 （mysql专用端口号）
            // 数据库名称 how2java
            // 编码方式 UTF-8
            // 账号 root
            // 密码 admin
            Connection c = DriverManager
                   .getConnection(
                           "jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                           "root", "admin");

           System.out.println("连接成功，获取连接对象： " + c);

            System.out.println("数据库驱动加载成功 ！");

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
```

- 对数据库增删查改 Statement
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_151000.png)

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
...
Statement s = c.createStatement();

            // 准备sql语句
            // 注意： 字符串要用单引号'
            String sql = "insert into hero values(null,"+"'提莫'"+","+313.0f+","+50+")";
            s.execute(sql);

            System.out.println("执行插入语句成功");
```            

- 关闭连接：数据库的连接是有限资源，相关操作结束后，养成关闭数据库的好习惯
先关闭Statement
后关闭Connection
```java
Connection c = null;
        Statement s = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root",
                    "admin");

            s = c.createStatement();

            String sql = "insert into hero values(null," + "'提莫'" + "," + 313.0f + "," + 50 + ")";

            s.execute(sql);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // 数据库的连接时有限资源，相关操作结束后，养成关闭数据库的好习惯
            // 先关闭Statement
            if (s != null)
                try {
                    s.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            // 后关闭Connection
            if (c != null)
                try {
                    c.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

        }

```
