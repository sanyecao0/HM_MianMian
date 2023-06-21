package com.itheima.service.store.Impl;

import com.itheima.dao.system.CataLogDao;
import com.itheima.dao.system.impl.CataLogDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.Catalog;
import com.itheima.domain.store.Course;
import com.itheima.service.store.CatalogService;
import com.itheima.service.store.CourseService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CatalogServiceImpl implements CatalogService {
    CataLogDao cataLogDao=new CataLogDaoImpl();
    @Override
    public List<Catalog> findAll() throws SQLException {
        return cataLogDao.findAll();
    }
    @Override
    public void save(Catalog catalog) throws SQLException {
        String uid= UUID.randomUUID().toString();
        catalog.setId(uid);
        cataLogDao.save(catalog);
    }

    @Override
    public void delete(String id) throws SQLException {
        cataLogDao.delete(id);
    }

    @Override
    public void update(Catalog catalog) throws SQLException {
        cataLogDao.update(catalog);
    }
    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total= cataLogDao.findTotal();
        int start=(currpage-1)*size;
        List<Catalog> courseList= cataLogDao.findPage(start,size);
        CourseService courseService=new CourseServiceImpl();
        for(Catalog catalog:courseList){
            String id=catalog.getCourseId();
            catalog.setCourse(courseService.findByID(id));
        }
        /*for(Role dept:deptList){
            String id=dept.getParentId();
            dept.setParent(roleDao.findByID(id));
        }*/
        PageBean pageBean=new PageBean(currpage,size,total,courseList);
        return pageBean;
    }
    @Override
    public Catalog findByID(String id) throws SQLException {
        return cataLogDao.findByID(id);
    }
}
