package com.atguigu.mapper;

import com.atguigu.pojo.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogMapper {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) ;

    @Select("select * from sysLog order by visitTime desc")
    List<SysLog> findAll() ;

    @Delete("delete from sysLog where id=#{id}")
    void delete(@Param("id")Integer id);
}
