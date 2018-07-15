---
title: js遍历图片并显示在html中
date: 2018-7-9 20:13:10
tags:
categories:
---

### 源码
```html


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">

	#pic img{ border:1px solid #ccc; padding:1px; margin:3px;}
</style>

</head>

<body>
<div id="pic">

</div>
<script type="text/javascript">
var tbsource = "1";//本地文件夹路径

var hdfiles = "";

var objFSO =new ActiveXObject('Scripting.FileSystemObject');

if(!objFSO.FolderExists(tbsource))

{

alert("<"+tbsource+">该文件夹路径不存在，或者路径不能含文件名！");

objFSO = null;

//return;

}

var objFolder = objFSO.GetFolder(tbsource);

var colFiles = new Enumerator(objFolder.Files);

//var re_inf1 = /\.jpg$/; 验证文件夹文件是否jpg文件

//var re_inf1 = /\[.](jpg|gif|bmg)$/;

var re_inf1 =/\.(gif|jpg|jpeg|bmp)$/i;

for (; !colFiles.atEnd(); colFiles.moveNext()) //读取文件夹下文件

{

	var objFile = colFiles.item();

	if(re_inf1.test(objFile.Name.toLowerCase()))

	{

	hdfiles = hdfiles+"<img src='1/"+objFile.Name+"'>";

	}

}

alert(hdfiles);
document.getElementById("pic").innerHTML=hdfiles;

</script>
</body>
</html>

```
