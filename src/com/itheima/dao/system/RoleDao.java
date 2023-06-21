package com.itheima.dao.system;

import com.itheima.domain.system.Module;
import com.itheima.domain.system.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    void save(Role role) throws SQLException;

    void update(Role role) throws SQLException;

    Role findByID(String id) throws SQLException;

    Long findTotal() throws SQLException;

    List<Role> findPage(int start, int size) throws SQLException;

    List<Role> findAll() throws SQLException;

    void delete(String id) throws SQLException;

    void updateRoleAndModules(List<String> moduleIDs, String roleid) throws SQLException;

    List<String> findRoleIdsByUserId(String id) throws SQLException;
}
