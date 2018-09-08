---
title: 2小时入门springboot
date: 2018-8-10 20:13:10
tags:
---


# [慕课网完整教程](https://www.imooc.com/video/13593)

## 遇到问题首选Google 不然会被百度坑死
- tomcat把（64位） 与jvm （32位）版本不兼容
- 解决方法：重新下载jdk
- 更换jdk并配置 java -version 报错
- can't find java runtime environment
- 解决方法：把%JAVA_HOME%/bin移到最前面  然后重启


## pom.xml
- 引入两个依赖：mysql.jdbc/jpa

```java
<dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      </dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

## application.yml
- 配置mysql数据库连接信息 服务器信息 jpa信息

```java
server:
  port: 8081
  servlet:
    context-path: /gril
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/dbgril
    username: root
    password: admin
    driver-class-name: com.mysql.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true //执行的sql操作会打印在控制台

# profiles:
##    active: dev //使用开发环境配置
```

## 总结下注解/请求方式/关键类
### 注解
- @Controller  //返回json会抱错
- @RestController //可返回json
- @Entry //关联实体类 用于创建数据库
- @Id + @GeneratedValue //设置id自增长
- @...Mappering
- @Value("${cupSize}")
- @Component + @ConfigurationProperties(prefix = "gril") //把配置分组
- @Transactional //事务管理

```java
//获得配置文件单个属性值
@Value("${cupSize}")
private String cupSize;//类型在这时候才确定

//属性值太多-》分组
//application.yml
gril:
   cupSize: B
   age: 16
//GrilProperties
@Component
@ConfigurationProperties(prefix = "gril")
public class GrilProperties  {
    private String cupSize;
    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
//使用

@Autowired
    private GrilProperties grilProperties;

    @RequestMapping(value = {"/showgril","showboy"},method=RequestMethod.GET)
    public String show(){
        return grilProperties.getCupSize()+grilProperties.getAge();

    }
```

```java
//事务管理注释举例
//RestController
@RestController
public class GrilController {

    @Autowired
    private GrilService grilService;
    @PostMapping("/inserTwo")
       public void insertTwo(){
           grilService.insertTwo();
       }
//grilService
//@Service
public class GrilService {
    @Autowired
    GrilRepository grilRepository;

    @Transactional
    public void insertTwo(){
        Gril grilA=new Gril();
        grilA.setCupSize("A");
        grilA.setAge(8);
        grilRepository.save(grilA);

        Gril grilB=new Gril();
        grilB.setCupSize("A");
        grilB.setAge(8);
        grilRepository.save(grilB);
    }
}
```






### 关键类
- JpaRepository:继承啦很多数据库操作 eg:findAll()....

```java
public interface GrilRepository  extends JpaRepository<Gril,Integer> {
}
```

### 请求方式：主要是GET/POST  接口测试工具 postman
- 区别1：get 不提交数据到服务器 只从服务器获得数据
- 区别：post 提交/不提交数据到服务器 返回/不返回数据

```java
@PostMapping("/insert")
    public Gril insert(@RequestParam("cupSize")String cupSize,
                       @RequestParam("age") Integer age){
        Gril g=new Gril();
        g.setAge(age);
        g.setCupSize(cupSize);
        grilRepository.save(g);
        return g;

    }

    @GetMapping("select")
    public List<Gril> findall(){
        return grilRepository.findAll();
    }

```

![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-11_151141.png)
![](http://oy5lsbw4v.bkt.clouddn.com/2018-08-11_151043.png)
