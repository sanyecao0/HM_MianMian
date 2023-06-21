package com.itheima.service.system.impl;

import com.itheima.dao.system.RoleDao;
import com.itheima.dao.system.impl.RoleDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Module;
import com.itheima.domain.system.Role;
import com.itheima.service.system.RoleService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RoleServiceImpl implements RoleService {
    RoleDao roleDao =new RoleDaoImpl();
    @Override
    public Role findByID(String id) throws SQLException {
        return roleDao.findByID(id);
    }

    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total= roleDao.findTotal();
        int start=(currpage-1)*size;
        List<Role> roleList= roleDao.findPage(start,size);
        /*for(Role dept:deptList){
            String id=dept.getParentId();
            dept.setParent(roleDao.findByID(id));
        }*/
        PageBean pageBean=new PageBean(currpage,size,total,roleList);
        return pageBean;
    }

    @Override
    public void save(Role role) throws SQLException {
        String uid= UUID.randomUUID().toString();
        role.setId(uid);
        roleDao.save(role);
    }

    @Override
    public void delete(String id) throws SQLException {
        roleDao.delete(id);
    }

    @Override
    public void update(Role role) throws SQLException {
        roleDao.update(role);
    }

    @Override
    public List<Role> findAll() throws SQLException {
        return roleDao.findAll();
    }

    @Override
    public List<String> findRoleIdsByUserID(String id) throws SQLException {
        return roleDao.findRoleIdsByUserId(id);
    }
}
