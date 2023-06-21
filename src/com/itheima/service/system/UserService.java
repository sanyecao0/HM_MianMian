package com.itheima.service.system;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    PageBean findPages(int currpage, int size) throws SQLException;

    void save(User user) throws SQLException;

    User findByID(String id) throws SQLException;

    void update(User user) throws SQLException;

    void delete(String id) throws SQLException;

    List<User> findAll() throws SQLException;

    List<String> findRoleID(String id) throws SQLException;

    void updateUserAndRoles(String[] roleIds, String userId) throws SQLException;

    User login(String email, String password) throws SQLException;
}
