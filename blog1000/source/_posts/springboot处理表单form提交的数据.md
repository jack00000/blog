---
title: springboot处理表单form提交的数据
date: 2018-7-18 15：00
tags:   
categories: skills
---


### 宿舍管理系统前端一览
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180719162740.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180719162746.png)


### 在themeleaf+springboot中实现表单的数据传递。
- 新建实体类Greeting
- 新建Controller类GreetingController
- 新建html：greeting.html
- 新建html：result.html
- 知道GetMapping/PostMapping
- 当提交form时 GetMapping将对象传入form
- form将数据封装进对象th:object="${greeting}"
- th:action="@{/greeting}"将对象传入指定页面result.html
- result.html取出对象属性并显示
- 注意：引入<html xmlns:th="http://www.thymeleaf.org"> 不然 th报错。

```java
//使用默认构造方法
public class Greeting {
    private long id;
    private String content;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
```

```java
//三个Mapping
@Controller
public class GreetingController {

    //传入greeting对象实现封装
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }
    //将封装的对象转入result.html
    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
        return "result";
    }

}
```

```html
<!-- greeting.html -->
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Handling Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>Form</h1>
<!-- action:greeting 先通过GetMapping得到对象用于封装数据，在通过PostMapping传递数据 -->
<form action="#" th:action="@{/greeting}" th:object="${greeting}" method="post">
    <p>Id: <input type="text" th:field="*{id}" /></p>
    <p>Message: <input type="text" th:field="*{content}" /></p>
    <p><input type="submit" value="Submit" /> <input type="reset" value="Reset" /></p>
</form>
</body>
</html>
```

```html
<!-- result.html -->
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Getting Started: Handling Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>Result</h1>
<p th:text="'id: ' + ${greeting.id}" />
<p th:text="'content: ' + ${greeting.content}" />
<a href="/greeting">Submit another message</a>
</body>
</html>
```

![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180718200149.png)
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180718200158.png)
