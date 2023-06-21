package com.itheima.service.store.Impl;

import com.itheima.dao.store.CourseDao;
import com.itheima.dao.store.impl.CourseDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.Course;
import com.itheima.service.store.CourseService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CourseServiceImpl implements CourseService {
    CourseDao courseDao =new CourseDaoImpl();
    @Override
    public Course findByID(String id) throws SQLException {
        return courseDao.findByID(id);
    }

    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total= courseDao.findTotal();
        int start=(currpage-1)*size;
        List<Course> courseList= courseDao.findPage(start,size);
        /*for(Role dept:deptList){
            String id=dept.getParentId();
            dept.setParent(roleDao.findByID(id));
        }*/
        PageBean pageBean=new PageBean(currpage,size,total,courseList);
        return pageBean;
    }

    @Override
    public void save(Course course) throws SQLException {
        String uid= UUID.randomUUID().toString();
        course.setId(uid);
        courseDao.save(course);
    }

    @Override
    public void delete(String id) throws SQLException {
        courseDao.delete(id);
    }

    @Override
    public void update(Course course) throws SQLException {
        courseDao.update(course);
    }

    @Override
    public List<Course> findAll() throws SQLException {
        return courseDao.findAll();
    }
}
