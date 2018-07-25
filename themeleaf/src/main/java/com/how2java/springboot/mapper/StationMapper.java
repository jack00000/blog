package com.how2java.springboot.mapper;

import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.Station;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StationMapper {
    @Select("select * from station ")
    List<Station> findAll();

    @Insert(" insert into category_ ( name ) values (#{name}) ")
    public int save(Category category);

    @Delete(" delete from category_ where id= #{id} ")
    public void delete(int id);

    @Select("select * from station where slid= #{slid} ")
    public Station get(int slid);

    @Update("update station set name=#{name} where id=#{id} ")
    public int update(Station s);
}
