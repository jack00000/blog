---
title: tmall_j2ee项目练习
date: 2017-11-29 15：00
tags:    java
categories: how2j
---

h3>完整教程：[how2j](http://how2j.cn/k/tmall-j2ee/tmall-j2ee-973/973.html#nowhere)</h3>

<h3>备忘</h3>

<h3>1.建表（根据表设计尸体类）</h3>
- 用户表 user
    name
    id
    password

- 分类表 catagory
private String name;
private int id;
List<Product> products;
List<List<Product>> productsByRow;    

- 属性表 Property
private String name;
    private Category category;
    private int id;
- 产品表 Product
private String name;
    private String subTitle;
    private float orignalPrice;
    private float promotePrice;
    private int stock;
    private Date createDate;
    private Category category;
    private int id;
    private ProductImage firstProductImage;
    private List<ProductImage> productImages;
    private List<ProductImage> productSingleImages;//
    private List<ProductImage> productDetailImages;
    private int reviewCount;
    private int saleCount;4
- 产品图片表 ProductImage
private String type;
   private Product product;
   private int id;
- 属性值表 PropertyValue
private String value;
    private Product product;
    private Property property;//注意 这里是property的对象
    private int id;       
