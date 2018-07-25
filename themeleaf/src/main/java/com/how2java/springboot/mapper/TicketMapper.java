package com.how2java.springboot.mapper;

import com.how2java.springboot.pojo.Category;
import com.how2java.springboot.pojo.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketMapper {
    @Select("select * from ticket ")
    List<Ticket> findAll();

    @Insert(" insert into ticket ( tiid,tiss,tias,tist,tiat,tipr,titp,tity,tino ) values (#{tiid},#{tiss},#{tias},#{tist},#{tiat},#{tipr},#{titp},#{tity},#{tino}) ")
    public int save(Ticket t);

    @Delete(" delete from ticket where tiid= #{tiid} ")
    public void delete(int tiid);

    @Select("select * from ticket where id= #{id} ")
    public Ticket get(int id);

    @Update("update ticket set name=#{name} where id=#{id} ")
    public int update(Ticket t);
}
