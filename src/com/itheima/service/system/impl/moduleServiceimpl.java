package com.itheima.service.system.impl;

import com.itheima.dao.system.impl.moduleDaoImpl;
import com.itheima.dao.system.moduleDao;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Module;
import com.itheima.service.system.moduleService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class moduleServiceimpl implements moduleService {
    moduleDao Dao=new moduleDaoImpl();

    @Override
    public List<Module> findModulesByUserId(String id) throws SQLException {
        return Dao.findModulesByUserId(id);
    }

    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total=Dao.findTotal();
        int start=(currpage-1)*size;
        List<Module> moduleList= Dao.findPage(start,size);
        PageBean pageBean=new PageBean(currpage,size,total,moduleList);
        return pageBean;
    }


    @Override
    public void save(Module module) throws SQLException {
        String uid= UUID.randomUUID().toString();
        module.setId(uid);
        Dao.save(module);
    }

    @Override
    public Module findByID(String id) throws SQLException {
        return Dao.findByID(id);
    }

    @Override
    public void update(Module module) throws SQLException {
        Dao.update(module);
    }

    @Override
    public void delete(String id) throws SQLException {
        Dao.delete(id);
    }

    @Override
    public List<Module> findAll() throws SQLException {
        return Dao.findAll();
    }

    @Override
    public List<Module> findModulesByRoleId(String roleid) throws SQLException {
        return  Dao.findModulesByRoleId(roleid);
    }
    @Override
    public boolean isExists(String id, List<Module> moduleList) throws SQLException {
        return  Dao.isExists(id,moduleList);
    }
}
