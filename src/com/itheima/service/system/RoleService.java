package com.itheima.service.system;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {
    Role findByID(String id) throws SQLException;

    PageBean findPages(int currpage, int size) throws SQLException;

    void save(Role role) throws SQLException;

    void delete(String id) throws SQLException;

    void update(Role role) throws SQLException;

    List<Role> findAll() throws SQLException;

    List<String> findRoleIdsByUserID(String id) throws SQLException;
}
