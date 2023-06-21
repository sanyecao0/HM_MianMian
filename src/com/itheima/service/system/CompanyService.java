package com.itheima.service.system;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyService {

     Company findByID(String id) throws SQLException;

    PageBean findPages(int currpage, int size) throws SQLException;

    public void save(Company company) throws SQLException;

    void delete(String id) throws SQLException;

    void update(Company company) throws SQLException;

    List<Company> findAll() throws SQLException;
}
