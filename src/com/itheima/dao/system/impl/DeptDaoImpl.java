package com.itheima.dao.system.impl;

import com.itheima.dao.system.DeptDao;
import com.itheima.domain.system.Company;
import com.itheima.domain.system.Dept;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class DeptDaoImpl implements DeptDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());


    @Override
    public void save(Dept dept) throws SQLException {
        queryRunner.update("insert into dept values(?,?,?,?,now(),now())"
                ,dept.getId(),dept.getName(),dept.getParentId(),dept.getState());
    }

    @Override
    public void update(Dept dept) throws SQLException {
        queryRunner.update("update dept set name=?,parentId=?,state=?,updateTime=now() where id=?"
                ,dept.getName(),dept.getParentId(),dept.getState(),dept.getId());
    }

    @Override
    public Dept findByID(String id) throws SQLException {
        return queryRunner.query("select * from dept where id=?",new BeanHandler<>(Dept.class),id);
    }

    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from dept",new ScalarHandler<>());
    }

    @Override
    public List<Dept> findPage(int start, int size) throws SQLException {
        List<Dept> deptList= queryRunner.query("select * from dept limit ?,?",
                new BeanListHandler<>(Dept.class), start, size);
        return deptList;
    }

    @Override
    public List<Dept> findAll() throws SQLException {
        List<Dept> deptList=queryRunner.query("select * from dept",new BeanListHandler<>(Dept.class));
        return deptList;
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from dept where id=?",id);
    }
}
