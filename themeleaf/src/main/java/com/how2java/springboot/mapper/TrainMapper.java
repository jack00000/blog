package com.how2java.springboot.mapper;

import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.Train;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TrainMapper {
    @Select("select * from train ")
    List<Train> findAll();

    @Insert(" insert into train ( trty,trss,trsa,trst,trat,trcc,trsc,trkm) values (#{trty},#{trss},#{trsa},#{trst},#{trat},#{trcc},#{trsc},#{trkm}) ")
    public int save(Train t);

    @Delete(" delete from train where trid= #{trid} ")
    public void delete(int trid);

    @Select("select * from train where trid= #{trid} ")
    public Train get(int trid);

    @Update("update train set trty=#{trty},trss=#{trss},trsa=#{trsa},trst=#{trst},trat=#{trat},trcc=#{trcc},trsc=#{trsc},trkm=#{trkm} where trid=#{trid} ")
    public int update(Train t);



}
