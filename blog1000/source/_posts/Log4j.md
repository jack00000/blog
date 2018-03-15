---
title: Log4j
date: 2017-11-29 15：00
tags:   skills java
categories: skills
---

<h3>完整教程：[how2j](http://how2j.cn/k/log4j/log4j-tutorial/1081.html#nowhere)</h3>

<h3>备忘</h3>
- 导入jar包 新建类并运行
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_110221.png)
- Log4j配置
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_110904.png)
- log4j.properties
 ![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_111643.png)

- log4j.xml
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_112517.png)

- log4j输出到文件
![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_113448.png)

![](http://oyj1fkfcr.bkt.clouddn.com/2017-12-19_113341.png)

```java
package log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog4j{
	//getLogger()获取需要打印日志的对象
	static Logger logger=Logger.getLogger(TestLog4j.class);
	public static void main(String[] args) {
		//到处logj日志的配置文件  输出的设置   如输出到控制台，输出到文件
		PropertyConfigurator.configure("E:\\project\\log4j\\src\\log4j.xml");
	    for(int i=0;i<5000;i++) {
	    	logger.info("输出信息");
	    	logger.warn("警告信息");
	    }
	}
}
```
