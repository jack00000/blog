---
title: page与request与session与application的区别
date: 2018-8-2 20:13:10
tags:

---

# page与request与session与application的区别
page,request,session以及application这4个对象的范围是逐个增加的：
page作用于只在一个jsp页面；
request只在一个请求的范围内；
session 是在浏览器窗口的范围内；
application则是在整个服务器的运行过程中。


# application 存数据 取数据(通常放登录信息 eg:显示当前用户..)

```java
@Autowired
private  ServletContext application;
application.setAttribute("username",user.getName());
//jsp/themeleaf
	<a th:text="${application.username}"></a>
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180807090548.png)

# session /request 存数据 取数据(通常放用户相关信息 eg:显示当前用户..)
- application与session/request 取得对象的方式不同(X) 存取都是set/getAttribute;
- 一个是声明对象  一个是参数传入(X)
- session/request/application放在()里面声明和外面没差。


```java
public String login2main(User user, Model m, HttpServletRequest request, HttpSession session,ServletContext application) {
        User user1 = userMapper.getByname(user.getName());

        if (user1.getName() != null&&user.equals(user)) {
            request.setAttribute("username2",user.getName());
            session.setAttribute("username1",user.getName());
            application.setAttribute("username",user.getName());
            return "fristPage";

        } else {
            return "login";
        }
//jsp/themeleaf
<a th:text="${session.username}"></a>
```
