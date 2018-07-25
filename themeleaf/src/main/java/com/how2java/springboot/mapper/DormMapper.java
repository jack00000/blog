package com.how2java.springboot.mapper;

import com.how2java.springboot.pojo.Dorm;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DormMapper {

    @Select("select * from dorm_ ")
    List<Dorm> findAll();

    @Insert(" insert into dorm_ ( type ) values (#{type}) ")
    public void save(Dorm dorm);

    @Delete(" delete from dorm_ where id= #{id} ")

    public void delete(int id);

    @Select("select * from dorm_ where id= #{id} ")
    public Dorm get(int id);

    @Update("update dorm_ set type=#{type} where id=#{id} ")
    public String update(Dorm dorm);
}
