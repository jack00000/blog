---
title: springboot进阶之web进阶
date: 2018-7-28 20:13:10
tags:
---







# [慕课网完整教程](https://www.imooc.com/video/14338/0)
# 以下教程[完整源码](https://github.com/jack00000/springboot-)

## @Valid  表单验证

```java
//控制器
@PostMapping("/insert")
   public Gril insert(@Valid  Gril g1, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           System.out.println(bindingResult.getFieldError().getDefaultMessage());
       }
       Gril g=new Gril();
       g.setAge(g1.getAge());
       g.setCupSize(g1.getCupSize());

       return grilRepository.save(g);

   }

//实体类
@Min(value = 18,message = "没成年不能插入数据库")
private Integer age;
```

![](http://oy5lsbw4v.bkt.clouddn.com/2018-08-11_174219.png)

## AOP 面向切面编程思想
- 图解:将通用业务从具体逻辑中分离出来
![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-11_175159.png)

### AOP记录http请求
- @After
- @Before
- @AfterReturning
![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-11_184706.png)



## 统一异常处理

### 自己写返回格式
- 新建Result类

```java
public class Result<T> {
    //错误码
    private Integer code;
    //错误信息
    private String msg;
    //数据 T是个泛型
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
/*//通一异常处理 单个对象返回json举例
//错误时
{
     "code":1,
     "msg":"金额必传",
     "data":null
}
//正确时
{
        "code":0,
        "msg":"成功",
        "data":{
            "id":20,
             "cupSize":"B",
             "age":25,
             "money":1.2
        }
}*/
```
- 在controller类使用
```java
public Object insert(@Valid  Gril g1, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
//            System.out.println(bindingResult.getFieldError().getDefaultMessage());
           //改进 统一异常处理 @Autowrite引入报错
               Result result=new Result();
               result.setCode(1);
               result.setMsg(bindingResult.getFieldError().getDefaultMessage());
               result.setData(null);
               return result;

       }
       Gril g=new Gril();
       g.setAge(g1.getAge());
       g.setCupSize(g1.getCupSize());
       Result result=new Result();
       result.setCode(0);
       result.setMsg("成功");
       result.setData(grilRepository.save(g));

       return result;

   }
```
![](http://oyj1fkfcr.bkt.clouddn.com/2018-08-12_111437.png)
![](http://oxz3x2njl.bkt.clouddn.com/2018-08-12_111449.png)

### 把上面的代码用工具类ResultUtil 封装
- 实现年龄判断并做相应处理 统一异常返回格式。
- ResultUtil.err(1,"未成年禁止入内");
- @ControllerAdvance
- @ExceptionHandel
- @ResponseBody
```java
//新建工具类
public class ResultUtil {
    public static Result success(Object object){
        Result result=new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }
    public static Result success(){
        return success(null);
    }
    public static Result err(Integer code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;

    }
}
//GrilService
public void getAge(Integer id)throws Exception{
       Gril gril=grilRepository.getOne(id);
       if(gril.getAge()<10){
           throw new Exception("你在上小学");
       }else if(gril.getAge()<30){
           throw new Exception("你在上初中");
       }
   }
//Controller
@GetMapping(value = "/getAge/{id}")
   public void getAge(@PathVariable("id")Integer id)throws Exception{
       grilService.getAge(id);
   }
//ExceptionHandel
@ControllerAdvice
public class ExceptionHandel {
    //获取具体Controller类抛出的异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handel(Exception e){
        return ResultUtil.err(100,e.getMessage());
    }

}
```
![](http://oy5lsbw4v.bkt.clouddn.com/2018-08-12_120114.png)

### 在改进  自定义异常GrilException

```java
public class GrilException extends RuntimeException{
    private Integer code;

    public GrilException(Integer code ,String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
//使用
//GrilService
public void getAge(Integer id)throws Exception{
        Gril gril=grilRepository.getOne(id);
        if(gril.getAge()<10){
            throw new GrilException(100,"你在上小学");
        }else if(gril.getAge()<30){
            throw new GrilException(101,"你在上初中");
        }
    }
//GrilController
@GetMapping(value = "/getAge/{id}")
  public void getAge(@PathVariable("id")Integer id)throws Exception{
      grilService.getAge(id);
  }
//ExceptionHandel
@ControllerAdvice
public class ExceptionHandel {
    private final static Logger logger=LoggerFactory.getLogger(ExceptionHandler.class);
    //获取具体Controller类抛出的异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handel(Exception e){
        if(e instanceof GrilException){
            GrilException grilException=(GrilException)e;
            return ResultUtil.err(grilException.getCode(),grilException.getMessage());
        }else {
            logger.error("【系统异常】{}");
            return ResultUtil.err(-1,"未知错误");
        }

    }
```




### 在改进 把错误码方法enum中管理

```java
//ResultEnum
public enum ResultEnum {
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    PRIMARY_SCHOOL(100,"还在上小学"),
    MIDDLE_SCHOOL(101,"还在上初中"),
    ;


    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
//使用
//GrilService
public void getAge(Integer id)throws Exception{
       Gril gril=grilRepository.getOne(id);

       if(gril.getAge()<10){
           throw new GrilException(ResultEnum.PRIMARY_SCHOOL.getCode(),ResultEnum.PRIMARY_SCHOOL.getMsg());
       }else if(gril.getAge()<30){
           throw new GrilException(ResultEnum.MIDDLE_SCHOOL.getCode(),ResultEnum.MIDDLE_SCHOOL.getMsg());
       }
   }
//GrilController
public void getAge(Integer id)throws Exception{
       Gril gril=grilRepository.getOne(id);

       if(gril.getAge()<10){
           throw new GrilException(ResultEnum.PRIMARY_SCHOOL);
       }else if(gril.getAge()<30){
           throw new GrilException(ResultEnum.MIDDLE_SCHOOL);
       }
   }
//报错 需改 GrilException
public class GrilException extends RuntimeException{
    private Integer code;

    public GrilException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
//ExceptionHandel 不用改
@ControllerAdvice
public class ExceptionHandel {
    private final static Logger logger=LoggerFactory.getLogger(ExceptionHandler.class);
    //获取具体Controller类抛出的异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handel(Exception e){
        if(e instanceof GrilException){
            GrilException grilException=(GrilException)e;
            return ResultUtil.err(grilException.getCode(),grilException.getMessage());
        }else {
            logger.error("【系统异常】{}");
            return ResultUtil.err(-1,"未知错误");
        }

    }

}

```



## 单元测试
- 对api接口(Mappering(url)) /service/ controller方法进行测试
- @RunWith
- @SpringBootTest
- @AutoConfigureMockMvc

```java
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GrilControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void grilList() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/grilList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("abc"));

    }
}
```
![](http://oy5lsbw4v.bkt.clouddn.com/2018-08-12_151957.png)

### 多个单元测试
- 项目打包时会自动运行测试类 有错则打包失败

![](http://oyhm15net.bkt.clouddn.com/2018-08-12_152621.png)
