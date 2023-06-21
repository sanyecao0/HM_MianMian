package com.itheima.dao.system;

import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    Long findTotal() throws SQLException;

    List<User> findPage(int start, int size) throws SQLException;

    void save(User user) throws SQLException;

    User findByID(String id) throws SQLException;

    void update(User user) throws SQLException;

    void delete(String id) throws SQLException;

    List<User> findAll() throws SQLException;

    List<String> findRoleID(String id) throws SQLException;

    void SaveUser_role(String[] roleIds, String userId) throws SQLException;

    User findByEmailAndPassword(String email, String password) throws SQLException;
}
