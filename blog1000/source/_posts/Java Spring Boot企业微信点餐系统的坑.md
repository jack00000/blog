---
title: Java Spring Boot企业微信点餐系统的坑
date: 2018-8-16 20:13:10
tags:
---


## 遇坑集合
- 配置数据没设置UTF-8编码(记不住) 导致数据库乱码
- 没有设置spring-boot-starter-parent版本与教程一致  导致jpa.findOne()等方法报错
- 忘记写@Runner(SpringRunner.class)+@SpringBootTest注解导致测试类方法一直不成功
- 删除啦默认构造函数导致jpa默写方法失效报错No identifier specified for entity
- 还没搞清楚Date怎么自动记录当前时间并提交(已懂：不是通过注解实现的 是sql语句。。。。)
- @Id 忘啦设置导致No identifier specified for entity
- @Service 忘啦 报错Error creating bean with name
- java.util.Date 测试报错could not execute statement  换成java.sql.Date 并在数据库端更改字符集为utf8 测试sava()成功
- 自动生成创建时间 不能用java.sql.Date 又改回来
- enums  枚举类中没有构造方法 会报错  需要@Getter 不需要set方法
- 把Data当字段 应该Date  @Data 是注解
- Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable); (在jpa中加个这方法 花式报错 说参数不对)
- 不等价(测试时遇到的)
```java
List<ProductInfo>productInfos=null;
//正常错、对
 Assert.assertNotEquals(0,productInfos.size());
 //测试一直对
 Aseert.assertNouNull(productInfos);

```
### 用注解实现创建时间 更新时间 字段 [教程](https://blog.csdn.net/qq_14945847/article/details/79671123)
- @EntityListeners
- @CreatedDate
- @LastModifiedDate
- @EnableJpaAuditing
- 缺一不可

```java
@EntityListeners(AuditingEntityListener.class)
public class ProductCategory {
  @CreatedDate
    @Column(name="create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;
}
//启动类加注解
@EnableJpaAuditing
```
### 用注解实现表之间的关系 （eg:插入一列ProductInfo数据 必须先指定一个ProductCategory）[教程](https://blog.csdn.net/qq_39158142/article/details/80523103)
-
- @OneToOne
- @ManyToOne
- @OneToMany
- @ManyToMany
- @Column（name = "自定义字段名"，length = "自定义长度"，nullable = "是否可以空"，unique = "是否唯一"，columnDefinition = "自定义该字段的类型和长度"）
- @Transient 表示该属性并非一个到数据库表的字段的映射

### OneToOne关系映射
### OneToMany/ManyToOneOneToOne关系映射

```java
//ProductInfo
@ManyToOne
private ProductCategory productCategory;
//ProductCategory
//categoryType是关联属性 两个表都有
//上面单方管理有问题 下面没问题
@OneToMany
List<ProductInfo> productInfoList;
```

### ManyToMany关系映射

### jpa自定义sql实现复杂查询
- 自定义 select * from product_category 可以
- 下面sql语句数据库可以查出来 程序测试不同  未解决。
- 网上说：Select后面没有别名！！！
- 靠 多表关联查询不能有*吗？
- select a.category_id ,b.product_id from Product_category a inner join Product_info b on a.category_type=b.category_type
- 测试通过。。。。

```java
 //jpa中学原生sql
@Query(value="select a.* ,b.* from Product_category a inner join Product_info b on a.category_type=b.category_type", nativeQuery = true)
List<Object> testsql();
```
![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-17_124538.png)
![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-17_133806.png)

### 引错包 导致Page 分页参数错误
- 应该是import org.springframework.data.domain.Pageable;
- 而不是import java.awt.print.Pageable;
- 更正后测试通过

### 数据库sql建表 一直存在的误区
- CONSTRAINT fk_property_category FOREIGN KEY (cid) REFERENCES category (id)
- 只是建立外键约束 不一定是 一对多/一对一
- 意思是 property表中cid指向category表的id 两者一样 ，在property表插入数据，其cid的值必须在category的id中存在，否则插入失败。
- 一对多等表之间的关系在实体类中体现。

```java
class Dormitory{
  int id;
  String name;
  int size;
  private List<Student> students;
}
class Student{
  int id;
  int did;//外键字段
  String name;
  private Dormitory dormitory;
}
```

### 一个表的外键一定是另一个表的主键吗?  of cource!个毛
- 网上两种说法：1.对，2.不一定是主键，但必须是维一索引，主键是维一索引。 [区别](https://blog.csdn.net/haydenyu/article/details/78018782)
- 主键可以有多个 主键约束（PRIMARY KEY） 唯一性约束（UNIQUE）  唯一索引（INDEX）
- 放弃jpa建表 用原生sql建表

### jpa 建立外键约束 [教程](https://blog.csdn.net/huaishuming/article/details/53427491)
-  @JoinColumn(name = "tid",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
- name值会在创建数据库自动创建外键tid字段。(所以实体类不用声明外键字段)
- 目前问题：外键自动创建啦 但不管插什么都是空

![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-18_153504.png)

```java
@ManyToOne
@JoinColumn(name = "tid",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
private Teacher teacher;

@OneToMany(mappedBy = "teacher")
@org.hibernate.annotations.ForeignKey(name = "none")
private List<Student> students;
```


## 好的东西
- lombok插件 +@Data注解=get/set/toString()
- springboot框架注解
- 按ctrl+鼠标点方法 可以看到源代码
- 统一api接口返回的json格式。
