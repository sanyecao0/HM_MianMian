package com.itheima.service.system.impl;

import com.itheima.dao.system.DeptDao;
import com.itheima.dao.system.UserDao;
import com.itheima.dao.system.impl.DeptDaoImpl;
import com.itheima.dao.system.impl.UserDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.User;
import com.itheima.service.system.UserService;
import com.itheima.utils.MD5Util;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    UserDao Dao=new UserDaoImpl();
    DeptDao deptDao=new DeptDaoImpl();
    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total=Dao.findTotal();
        int start=(currpage-1)*size;
        List<User> moduleList= Dao.findPage(start,size);
        PageBean pageBean=new PageBean(currpage,size,total,moduleList);
        return pageBean;
    }


    @Override
    public void save(User user) throws SQLException {
        String uid= UUID.randomUUID().toString();
        user.setId(uid);
        Dao.save(user);
    }

    @Override
    public User findByID(String id) throws SQLException {
        return Dao.findByID(id);
    }

    @Override
    public void update(User user) throws SQLException {
        Dao.update(user);
    }

    @Override
    public void delete(String id) throws SQLException {
        Dao.delete(id);
    }

    @Override
    public List<User> findAll() throws SQLException {
        return Dao.findAll();
    }
    @Override
    public List<String> findRoleID(String id) throws SQLException {
        return Dao.findRoleID(id);
    }

    @Override
    public void updateUserAndRoles(String[] roleIds, String userId) throws SQLException {
          Dao.SaveUser_role(roleIds,userId);
    }
    /**
     * 用户登录
     * @param email 邮箱
     * @param password 密码
     * @return
     */
    @Override
    public User login(String email, String password) throws SQLException {
        //1 对密码进行md5加密
        password= MD5Util.md5(password);
        //2 根据email和password查询用户信息
        User user = Dao.findByEmailAndPassword(email, password);
        //3 如果查到了就根据部门id查询用户的部门信息
        if(user!=null){
            Dept dept = deptDao.findByID(user.getDeptId());
            user.setDept(dept);
        }
        return user;
    }
}
