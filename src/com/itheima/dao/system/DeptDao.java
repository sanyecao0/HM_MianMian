package com.itheima.dao.system;

import com.itheima.domain.system.Company;
import com.itheima.domain.system.Dept;

import java.sql.SQLException;
import java.util.List;

public interface DeptDao {

    void save(Dept dept) throws SQLException;
    void update(Dept dept) throws SQLException;
    void delete(String id) throws SQLException;
    Dept findByID(String id) throws SQLException;

    Long findTotal() throws SQLException;

    List<Dept> findPage(int start, int size) throws SQLException;

    List<Dept> findAll() throws SQLException;
}
