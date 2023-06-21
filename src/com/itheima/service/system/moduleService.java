package com.itheima.service.system;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Dept;
import com.itheima.domain.system.Module;

import java.sql.SQLException;
import java.util.List;

public interface moduleService {
     List<Module> findModulesByUserId(String id) throws SQLException;


    PageBean findPages(int currpage, int size) throws SQLException;

    void save(Module module) throws SQLException;

    Module findByID(String id) throws SQLException;


    void update(Module module) throws SQLException;

    void delete(String id) throws SQLException;

    List<Module> findAll() throws SQLException;

    List<Module> findModulesByRoleId(String roleid) throws SQLException;

    boolean isExists(String id, List<Module> moduleList) throws SQLException;
}
