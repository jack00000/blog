package com.how2java.springboot.mapper;

import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.Sale;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SaleMapper {
    @Select("select * from sale ")
    List<Sale> findAll();

    @Insert(" insert into sale ( tiid,slti ) values (#{tiid},#{slti}) ")
    public int save(Sale s);

    @Delete(" delete from sale where tiid= #{tiid} ")
    public void delete(int id);

    @Select("select * from sale where tiid= #{tiid} ")
    public Sale get(int tiid);

    @Update("update sale set name=#{name} where id=#{id} ")
    public int update(Sale s);
}
