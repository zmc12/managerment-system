package com.atguigu.service;

import com.atguigu.pojo.SysLog;

import java.util.List;

public interface SysLogService {

    public void save(SysLog sysLog) ;

    List<SysLog> findAll();

    void delete(Integer id);
}
