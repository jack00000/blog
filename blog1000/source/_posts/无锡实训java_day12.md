---
title: 无锡实训java_day12
date: 2018-7-19 20:13:10
tags: java
categories: 无锡实训java
---

## 大致内容
- loginServlet类的doGet/doPost方法获取表单数据。
- 自己搭web开发框架。。。。(是真的麻烦)
- 自己动手实现对表的CURD(GUI) --以前都是直接套用。

```java
public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 *
	 *
	 */
	public void print(){

	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//servlet里面的 response 可以实现 打印网页的形式 输出一个网页
	// 1.页面的调度功能

		//request.getRequestDispatcher("adddormitry.html").forward(request, response);
		//2 取得数据
		//2.1 获得从客户端提交的数据
		String username=request.getParameter("username");
		String pw=request.getParameter("password");
		System.out.println(username+"----------------"+pw);
		IUserDao ud=new UserDao();
		User u=ud.getUserByNameandPw(username, pw);
		//要在转发的页面上显示现在的数据
		request.setAttribute("user", u);
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("welcome.jsp").forward(request, response);

		//servlet是纯JAVA代码 优势：处理业务逻辑 劣势：不适合显示数据
		//html --纯的静态网页
		//jsp---内容上来说 jsp=html+java   运行原理上 jsp==serlvet

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
```

```html
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 <style type="text/css">
     .cc{
      height: 300px;
         background-color: aqua;

     }
    </style>

</head>

<body>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<div class="container">

    <div class="row">
    <div class="col-md-12 cc">
        aaaaaaaaa

        </div>



    </div>
    <div class="row" >

        <div id="table_positon" class="col-md-6 col-md-push-6">



        </div>

    </div>

    </div>


</body>
<script>
    var wangzhichao = {
        stus: [{
            id: "001",
            name: "wzc",
            age: 18
        }, {
            id: "002",
            name: "zzw",
            age: 19
        }, {
            id: "003",
            name: "cyf",
            age: 19

        }, {
            id: "004",
            name: "dhl",
            age: 16
        }],
        del_studbyId: function(id) {
            //-------------删除方法
            for (var n = 0; n < this.stus.length; n++) {
                if (this.stus[n].id == id) {
                    this.stus.splice(n, 1);
                    break;
                }
            }
        },
        ths: ["学号", "姓名", "年龄", "操作"],
        tds: ["id", "name", "age"],
        init: function() {
            var body = document.body;
            var div1 = document.createElement("div");
            div1.setAttribute("class", "container");
            var div2 = document.createElement("div");
            div2.setAttribute("class", "row");
            var div3 = document.createElement("div");
            div3.setAttribute("class", "col-md-6 col-md-push-3");
            body.appendChild(div1);
            div1.appendChild(div2);
            div2.appendChild(div3);

            var table = document.createElement("table");
            var table_position=document.getElementById("table_positon");
            table_position.appendChild(table);
            table.setAttribute("class", "table table-bordered");

            var tr = document.createElement("tr");
            table.appendChild(tr);
            for (var i = 0; i < this.ths.length; i++) {

                var th = document.createElement("th");
                th.setAttribute("class", "text-center");
                th.innerHTML = this.ths[i];
                th.style.border = "1px solid #ddd";
                tr.appendChild(th);
            }

            for (var j = 0; j < this.stus.length; j++) {

                var tr1 = document.createElement("tr");
               /* tr1.onmouseover=function(){
                    tr1.style.backgroundColor = "red";

                }*/
                tr1.onmouseover = (function(tr1) {
                    // alert("----------");
                    //  tr1.style.backgroundColor = "red";
                    return function() {
                        tr1.style.backgroundColor = "#f5f5f5";

                    };
                })(tr1);
                tr1.onmouseout = (function(tr1) {

                    return function() {

                        tr1.style.backgroundColor = "#f9f9f9";
                    }
                })(tr1);
                // if(j)
                table.appendChild(tr1);
                for (var k = 0; k < this.tds.length; k++) {

                    var td = document.createElement("td");
                    //  if (j % 3 == 0) td.style.backgroundColor = "#f9f9f9";
                    td.setAttribute("class", "text-center");
                    td.style.border = "1px solid #ddd";
                    td.innerHTML = this.stus[j][this.tds[k]];
                    tr1.appendChild(td);
                }
                var td_last = document.createElement("td");
                // if (j % 3 == 0) td_last.style.backgroundColor = "#f9f9f9";
                td_last.setAttribute("class", "text-center");
                var button = document.createElement("button");
                button.setAttribute("class", "btn btn-sm btn-danger");
                button.innerHTML = "删除";
                tr1.appendChild(td_last);
                td_last.style.border = "1px solid #ddd";
                td_last.appendChild(button);

                button.onclick = function(event) {

                    var target = event.target;
                    var del_target = target.parentNode.parentNode;

                    var stuid = del_target.firstChild.textContent;
                    alert(stuid);
                    table.removeChild(del_target);
                    wangzhichao.del_studbyId(stuid);
                    //————调用方法

                }
                console.info(this.stus);
            }
        }
    };

    window.onload = function() {

        wangzhichao.init();

    }

</script>

</html>
```

### 如何将js创建的table 在body中引用。

```JavaScript
//在table 标签下创建表，把table标签赋给 id=table_positon的div
var table = document.createElement("table");
var table_position=document.getElementById("table_positon");
table_position.appendChild(table);
```

```html
<div id="table_positon" class="col-md-6 col-md-push-6">
```  
