package com.itheima.service.store;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.Catalog;

import java.sql.SQLException;
import java.util.List;

public interface CatalogService {
    List<Catalog> findAll() throws SQLException;

    void save(Catalog catalog) throws SQLException;

    void delete(String id) throws SQLException;

    void update(Catalog catalog) throws SQLException;

    PageBean findPages(int currpage, int size) throws SQLException;

    Catalog findByID(String id) throws SQLException;
}
