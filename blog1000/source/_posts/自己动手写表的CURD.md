---
title: 表的CURD操作
date: 2018-7-19 20:13:10
tags: java
categories: 无锡实训java
---


## 自己动手写表的CURD (springboot+themeleaf环境) [源码](https://github.com/jack00000/wuxi_train/tree/master/themeleaf)
- Category(int id,String name)表名及属性
- Mybatis通用分页插件PageHelper
- Properties类的作用
- 理解PageHelperConfig类如何用properties类来实现分页。
- 新建实体类Category
- 新建CategoryMapper类 将方法映射到具体的sql查询语句。
- 新建CategoryController类，根据游览器传过来的url 执行mapper的不同方法。
- 已成功完成，如果你的不行，可能原因：
- 1.注释没写，页面跳转，数据库出错。。。。。。。。
- 重点：页面如何获得控制层取到的数据集合->传入页面的model层 使用键值对，m.addAttribute("page",page).
- [另一种获取表单的方法，大同小异](https://jack00000.github.io/2018/07/19/springboot%E5%A4%84%E7%90%86%E8%A1%A8%E5%8D%95form%E6%8F%90%E4%BA%A4%E7%9A%84%E6%95%B0%E6%8D%AE/)

```Java
Properties类提供了几个主要的方法：

1． getProperty ( String key)，用指定的键在此属性列表中搜索属性。也就是通过参数 key ，得到 key 所对应的 value。

2． load ( InputStream inStream)，从输入流中读取属性列表（键和元素对）。通过对指定的文件（比如说上面的 test.properties 文件）进行装载来获取该文件中的所有键 - 值对。以供 getProperty ( String key) 来搜索。

3． setProperty ( String key, String value) ，调用 Hashtable 的方法 put 。他通过调用基类的put方法来设置 键 - 值对。

4． store ( OutputStream out, String comments)，以适合使用 load 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流。与 load 方法相反，该方法将键 - 值对写入到指定的文件中去。

5． clear ()，清除所有装载的 键 - 值对。该方法在基类中提供。
```
### PageHelperConfig类
- PageHelper Mybatis通用分页插件
- 将包含键值信息的properties对象赋给怕个Helper对象

```java
@Configuration
public class PageHelperConfig {
     //PageHelper分页插件 知道咋用就行  
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
```

### CategoryMapper类
- 注解省啦很多事

```java
@Mapper
public interface CategoryMapper {
    //通过注解让方法关联sql
    @Select("select * from category_ ")
    List<Category> findAll();

    @Insert(" insert into category_ ( name ) values (#{name}) ")
    public int save(Category category);

    @Delete(" delete from category_ where id= #{id} ")
    public void delete(int id);

    @Select("select * from category_ where id= #{id} ")
    public Category get(int id);

    @Update("update category_ set name=#{name} where id=#{id} ")
    public int update(Category category);

}
```

### CategoryController类

```java
@Controller
public class CategoryController {
    @Autowired CategoryMapper categoryMapper;

    @RequestMapping("/addCategory")
    public String listCategory(Category c) throws Exception {
        categoryMapper.save(c);
        return "redirect:listCategory";
    }
    @RequestMapping("/deleteCategory")
    public String deleteCategory(Category c) throws Exception {
        categoryMapper.delete(c.getId());
        return "redirect:listCategory";
    }
    @RequestMapping("/updateCategory")
    public String updateCategory(Category c) throws Exception {
			//调用categoryMapper的方法 间接调用sql语句
        categoryMapper.update(c);

        return "redirect:listCategory";
    }
    @RequestMapping("/editCategory")
    public String listCategory(int id,Model m) throws Exception {
        //通过id查找对象
        Category c= categoryMapper.get(id);
				 //在传入的模型层对象m 添加键值对，使m所在页面能通过key("c") 访问到value(c)
        m.addAttribute("c", c);
       //此时返回ListCategory页面 模型层对象m 可以通过“c”访问到对象c   
        return "editCategory";
    }

    @RequestMapping("/listCategory")
    public String listCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
      //以上，参数：传入listCategory页面的模型层对象m 和分页要用到的参数
      //以下，设置pageHelp插件让数据以id自减显示并设置开始 和每页的数量。
    	PageHelper.startPage(start,size,"id desc");
      //通过mapper的findAll方法间接调用sql查询到的数据封装到List容器 <Category> cs中
        List<Category> cs=categoryMapper.findAll();
        //将List容器 cs<Category> 赋值给PageInfo容器 <Category> page
        PageInfo<Category> page = new PageInfo<>(cs);
				 //在传入的模型层对象m 添加键值对，使m所在页面能通过key("page") 访问到value(page)
        m.addAttribute("page", page);  
          //此时返回ListCategory页面 模型层对象m 可以通过“page”访问到page容器    
        return "listCategory";
    }

}
```
#### listCategory.html&editCategory.html

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>



<div style="width:500px;margin:20px auto;text-align: center">
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>id</td>
            <td>name</td>
            <td>编辑</td>
            <td>删除</td>
        </tr>
        <!-- 此处通过控制层在模型层添加的键值对，可以通过page字符串访问到page对象，并调用其方法-->
        <tr th:each="c:${page.list}">
            <td th:text="${c.id}"></td>
            <td th:text="${c.name}"></td>
            <td><a th:href="@{/editCategory(id=${c.id})}">编辑</a></td>
            <td><a th:href="@{/deleteCategory(id=${c.id})}">编辑</a></td>
        </tr>
    </table>
    <br/>
    <div>
      <!--分页  -->
			<a th:href="@{/listCategory(start=0)}">[首  页]</a>
			<a th:href="@{/listCategory(start=${page.pageNum-1})}">[上一页]</a>
			<a th:href="@{/listCategory(start=${page.pageNum+1})}">[下一页]</a>
			<a th:href="@{/listCategory(start=${page.pages})}">[末  页]</a>
    </div>
    <br/>    
    <!--这里字符串到控制层怎么变成Category对象的？
      运行程序，一步步剖析
      应该是把name字符串通过键值对 赋给啦Category对象的name属性
      然后在跳转到控制层时 传入该Category对象
      通过mapper间接调用sql将该对象的name属性添加到数据库
      -->
    <form action="addCategory" method="post">

    name: <input name="name"/> <br/>
    <button type="submit">提交</button>

    </form>
</div>

</body>
</html>
```

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>hello</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<div style="margin:0px auto; width:500px">
<!--因为editCategory页面的模型层已经m.addAttribute("c",c)
    所以，在提交表单时，c.name,c.id都会注入对象Category
    当跳转到控制层只会传入这个Category对象 -->
	<form action="updateCategory" method="post">

	name: <input name="name" th:value="${c.name}"/> <br/>

	<input name="id" type="hidden" th:value="${c.id}"/>
	<button type="submit">提交</button>

	</form>
</div>
</body>

</html>  
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180719195340.png)
