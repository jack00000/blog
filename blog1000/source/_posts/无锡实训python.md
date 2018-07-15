---
title: 无锡实训python.md
date: 2018-7-3 20:13:10
tags:
categories: 无锡实训python  
---

## 这是根据python班同学发我的资料写的笔记，学过廖雪峰的python教程，感觉这些文件没啥东西，不过还是挑一些东西学一下
### 1.python运行过程 没有什么.class文件
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180704192501.png)

### 2.raw.input()获取键盘输入
```python
# -*- coding:utf-8 -*-
password=raw_input("请输入密码")
print '密码是',password
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180704193646.png)

### 3.input() 必须输入表达式
```python
# -*- coding:utf-8 -*-
sum=input("请输入表达式")
print '密码是',sum
```
![](http://oyj1fkfcr.bkt.clouddn.com/%E6%B7%B1%E5%BA%A6%E6%88%AA%E5%9B%BE_20180704194251.png)

### 4.大小驼峰命名法：叫得很吊，其实就是单词首个字母大不大写的问题，垃圾  LaJi->大驼峰  laJi->小驼峰


### 5.下标
```python
name="asdfghjk"
print (name[0])
print (name[1])
print (name[2])
print (name[2])
```
### 6.python_while循环-乘法口诀表
```python
i = 1
while i <= 9:
    j = 1
    while j <= i:
        print("%d*%d=%-2d " % (j, i, i * j))
        j += 1
    print()
    i += 1
```

### 7.python_for循环-乘法口诀表
```python
for i in range(1,9):
    for j in range(1,9):
       print("%d*%d=%d"%(j,i,i*j))
       j+=1
       print()
    i+=1   
```

### 8.python对文件的操作
- 写数据/读数据(read)
```python
# -*- coding:utf-8 -*-
f = open('test.txt', 'w')
f.write('hello world, i am here!')
f.close()

f = open('test.txt', 'r')
content = f.read(5)#单位字节
print(content)

# /usr/bin/python2.7 /home/fangjun/PycharmProjects/untitled/text.py
# hello
#
# Process finished with exit code 0

```
- 获取当前读写的位置 f.tell()
### 练习：复制文件
```java
oldFileName = input("请输入要拷贝的文件名字:")
oldFile = open(oldFileName, 'r')
# 如果打开文件
if oldFile:
    # 提取文件的后缀
    fileFlagNum = oldFileName.rfind('.')
    if fileFlagNum > 0:
        fileFlag = oldFileName[fileFlagNum:]
    # 组织新的文件名字
    newFileName = oldFileName[:fileFlagNum] + '-副本' + fileFlag
    # 创建新文件
    newFile = open(newFileName, 'w')
    # 把旧文件中的数据，一行一行的进行复制到新文件中
    for lineContent in oldFile.readlines():
        newFile.write(lineContent)
    # 关闭文件
    oldFile.close()
    newFile.close()
```
