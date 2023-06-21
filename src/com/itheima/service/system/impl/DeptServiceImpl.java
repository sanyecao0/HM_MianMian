package com.itheima.service.system.impl;

import com.itheima.dao.system.DeptDao;
import com.itheima.dao.system.impl.DeptDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Company;
import com.itheima.domain.system.Dept;
import com.itheima.service.system.DeptService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DeptServiceImpl implements DeptService {
    DeptDao deptDao=new DeptDaoImpl();
    @Override
    public Dept findByID(String id) throws SQLException {
        return deptDao.findByID(id);
    }

    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total=deptDao.findTotal();
        int start=(currpage-1)*size;
        List<Dept> deptList= deptDao.findPage(start,size);
        for(Dept dept:deptList){
            String id=dept.getParentId();
            dept.setParent(deptDao.findByID(id));
        }
        PageBean pageBean=new PageBean(currpage,size,total,deptList);
        return pageBean;
    }

    @Override
    public void save(Dept dept) throws SQLException {
        String uid= UUID.randomUUID().toString();
        dept.setId(uid);
        deptDao.save(dept);
    }

    @Override
    public void delete(String id) throws SQLException {
        deptDao.delete(id);
    }

    @Override
    public void update(Dept dept) throws SQLException {
        deptDao.update(dept);
    }

    @Override
    public List<Dept> findAll() throws SQLException {
        return deptDao.findAll();
    }

}
