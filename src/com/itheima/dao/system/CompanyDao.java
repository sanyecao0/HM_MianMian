package com.itheima.dao.system;

import com.itheima.domain.system.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDao {
    Long findTotal() throws SQLException;

    List<Company> findPage(int start, int size) throws SQLException;

    void save(Company company) throws SQLException;

    void delete(String id) throws SQLException;

    Company findByID(String id) throws SQLException;

    void update(Company company) throws SQLException;

    List<Company> findAll() throws SQLException;
}
