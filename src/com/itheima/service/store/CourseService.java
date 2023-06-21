package com.itheima.service.store;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseService {
    Course findByID(String id) throws SQLException;

    PageBean findPages(int currpage, int size) throws SQLException;

    void save(Course course) throws SQLException;

    void delete(String id) throws SQLException;

    void update(Course course) throws SQLException;

    List<Course> findAll() throws SQLException;
}
