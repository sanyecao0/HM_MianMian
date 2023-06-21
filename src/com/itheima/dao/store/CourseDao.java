package com.itheima.dao.store;

import com.itheima.domain.store.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    void save(Course course) throws SQLException;

    void update(Course course) throws SQLException;

    Course findByID(String id) throws SQLException;

    Long findTotal() throws SQLException;

    List<Course> findPage(int start, int size) throws SQLException;

    List<Course> findAll() throws SQLException;

    void delete(String id) throws SQLException;
}
