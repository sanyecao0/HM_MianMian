package com.itheima.dao.store.impl;

import com.itheima.dao.store.CourseDao;
import com.itheima.domain.store.Course;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());


    @Override
    public void save(Course course) throws SQLException {
        queryRunner.update("insert into course values(?,?,?,?,now(),now())"
                ,course.getId(),course.getName(),course.getState(),course.getRemark());
    }

    @Override
    public void update(Course course) throws SQLException {
        queryRunner.update("update course set name=?,state=?,remark=?, updateTime=now() where id=?"
                ,course.getName(),course.getState(),course.getRemark(), course.getId());
    }

    @Override
    public Course findByID(String id) throws SQLException {
        return queryRunner.query("select * from course where id=?",new BeanHandler<>(Course.class),id);
    }

    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from course",new ScalarHandler<>());
    }

    @Override
    public List<Course> findPage(int start, int size) throws SQLException {
        List<Course> courseList= queryRunner.query("select * from course limit ?,?",
                new BeanListHandler<>(Course.class), start, size);
        return courseList;
    }

    @Override
    public List<Course> findAll() throws SQLException {
        List<Course> courseList=queryRunner.query("select * from course",new BeanListHandler<>(Course.class));
        return courseList;
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from course where id=?",id);
    }
}
