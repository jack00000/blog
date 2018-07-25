package com.how2java.springboot.mapper;

import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SellerMapper {
    @Select("select * from seller ")
    List<Seller> findAll();
    @Insert(" insert into seller  (slid,slna ,slpa  ) values (#{slid},#{slna} ,#{slpa} )")

    public int save(Seller s);

    @Delete(" delete from seller where slid= #{slid} ")
    public void delete(int slid);

    @Select("select * from seller where slid= #{slid} ")
    public Seller get(int slid);

    @Update("update category_ set name=#{name} where id=#{id} ")
    public int update(Seller s);
}
