package com.itheima.dao.system;

import com.itheima.domain.store.Catalog;

import java.sql.SQLException;
import java.util.List;

public interface CataLogDao {
    List<Catalog> findAll() throws SQLException;

    List<Catalog> findPage(int start, int size) throws SQLException;

    void delete(String id) throws SQLException;

    void save(Catalog catalog) throws SQLException;

    void update(Catalog catalog) throws SQLException;

    Catalog findByID(String id) throws SQLException;

    Long findTotal() throws SQLException;
}
