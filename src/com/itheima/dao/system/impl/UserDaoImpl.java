package com.itheima.dao.system.impl;

import com.itheima.dao.system.UserDao;
import com.itheima.domain.system.Role;
import com.itheima.domain.system.User;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.poi.ss.formula.functions.Column;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from user",new ScalarHandler<>());
    }

    @Override
    public List<User> findPage(int start, int size) throws SQLException {
        List<User> users= queryRunner.query("select * from user limit ?,?",
                new BeanListHandler<>(User.class), start, size);
        return users;
    }

    @Override
    public void save(User user) throws SQLException {
        queryRunner.update("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,now(),now())"
                ,user.getId(),user.getEmail(),user.getUsername(),user.getPassword(),
                user.getState(),user.getGender(),user.getTelephone(),user.getBirthday(),user.getJoinDate(),
                user.getDeptId());
    }

    @Override
    public User findByID(String id) throws SQLException {
        return queryRunner.query("select * from user where id=?",new BeanHandler<>(User.class),id);
    }

    @Override
    public void update(User user) throws SQLException {
        queryRunner.update("UPDATE user SET email=?,username=?,password=?,state=?,gender=?,telephone=?, birthday=?,joinDate=?,deptId=?,updateTime = now() WHERE id=?"
                ,user.getEmail(),user.getUsername(),user.getPassword(),
                user.getState(),user.getGender(),user.getTelephone(),user.getBirthday(),user.getJoinDate(),
                user.getDeptId(),user.getId());
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from user where id=?",id);
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users=queryRunner.query("select * from user",new BeanListHandler<>(User.class));
        return users;
    }
    @Override
    public List<String> findRoleID(String id) throws SQLException {
        List<String> ur=queryRunner.query("SELECT  * from role where Id in( select roleId from user_role where userid=?);",new ColumnListHandler<String>(),id);
        return  ur;
    }

    @Override
    public void SaveUser_role(String[] roleIds, String userId) throws SQLException {
        queryRunner.update("delete from user_role where userId=?",userId);
           for(String s:roleIds){
               queryRunner.update("insert into user_role VALUES(?,?)",userId,s);
           }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws SQLException {
        return queryRunner.query("select * from user where email=? and password=?",new BeanHandler<>(User.class),email,password);
    }

}
