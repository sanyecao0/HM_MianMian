package com.itheima.service.system;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Company;
import com.itheima.domain.system.Dept;

import java.sql.SQLException;
import java.util.List;

public interface DeptService {
    
    Dept findByID(String id) throws SQLException;

    PageBean findPages(int currpage, int size) throws SQLException;

    public void save(Dept dept) throws SQLException;

    void delete(String id) throws SQLException;

    void update(Dept dept) throws SQLException;

    List<Dept> findAll() throws SQLException;
}
