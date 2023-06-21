package com.itheima.service.system.impl;

import com.itheima.dao.system.SylogDao;
import com.itheima.dao.system.impl.SylogDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.SysLog;
import com.itheima.domain.system.User;
import com.itheima.service.system.SylogService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class SylogServiceImpl implements SylogService {
    SylogDao Dao=new SylogDaoImpl();
    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total=Dao.findTotal();
        int start=(currpage-1)*size;
        List<SysLog> List= Dao.findPage(start,size);
        PageBean pageBean=new PageBean(currpage,size,total,List);
        return pageBean;
    }

    @Override
    public void delete(String id) throws SQLException {
        Dao.delete(id);
    }
    @Override
    public void save(SysLog sysLog) throws SQLException {
        String uid= UUID.randomUUID().toString();
        sysLog.setId(uid);
        Dao.save(sysLog);
    }
}
