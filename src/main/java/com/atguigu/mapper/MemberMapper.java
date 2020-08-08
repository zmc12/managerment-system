package com.atguigu.mapper;

import com.atguigu.pojo.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberMapper {


    @Select("select * from member where id=#{id}")
    public Member findById(Integer id);
}
