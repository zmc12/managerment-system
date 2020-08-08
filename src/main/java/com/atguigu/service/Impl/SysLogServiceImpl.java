package com.atguigu.service.Impl;

import com.atguigu.mapper.SysLogMapper;
import com.atguigu.pojo.SysLog;
import com.atguigu.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return sysLogMapper.findAll();
    }

    @Override
    public void delete(Integer id) {
        sysLogMapper.delete(id);
    }
}
