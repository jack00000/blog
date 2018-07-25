package com.how2java.springboot.mapper;

import com.how2java.springboot.pojo.Back;
import com.how2java.springboot.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BackMapper {
    @Select("select * from back ")
    List<Back> findAll();

    @Insert(" insert into back ( tiid,backprice ) values (#{tiid},#{backprice}) ")
    public int save(Back b);

    @Delete(" delete from back where tiid= #{tiid} ")
    public void delete(int tiid);

    @Select("select * from back where tiid= #{tiid} ")
    public Back get(int tiid);

    @Update("update back set name=#{name} where id=#{id} ")
    public int update(Back b);
}
