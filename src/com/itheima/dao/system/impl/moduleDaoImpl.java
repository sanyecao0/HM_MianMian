package com.itheima.dao.system.impl;

import com.itheima.dao.system.moduleDao;
import com.itheima.domain.system.Module;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class moduleDaoImpl implements moduleDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());

    @Override
    public List<Module> findModulesByUserId(String id) throws SQLException {
        String sql="select distinct m.* from user_role ur, role_module rm, module m where ur.roleId=rm.roleId and rm.moduleId=m.id and ur.userId=?";
        return queryRunner.query(sql,new BeanListHandler<>(Module.class),id);
    }

    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from module",new ScalarHandler<>());
    }

    @Override
    public List<Module> findPage(int start, int size) throws SQLException {
        List<Module> modules= queryRunner.query("select * from module limit ?,?",
                new BeanListHandler<>(Module.class), start, size);
        return modules;
    }

    @Override
    public void save(Module module) throws SQLException {
        queryRunner.update("insert into module values(?,?,?,?,?,?,?,now(),now())"
                ,module.getId(),module.getParentId(),module.getName(),module.getCurl(),
                module.getCtype(),module.getState(),module.getRemark());
    }

    @Override
    public Module findByID(String id) throws SQLException {
        return queryRunner.query("select * from module where id=?",new BeanHandler<>(Module.class),id);
    }

    @Override
    public void update(Module module) throws SQLException {
        queryRunner.update("UPDATE module SET parentId=?,name=?,curl=?,ctype=?,state=?,remark=?,updateTime = now() WHERE id=?"
                ,module.getParentId(),module.getName(),module.getCurl(),module.getCtype(),
                module.getState(),module.getRemark(),module.getId());
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from module where id=?",id);
    }

    @Override
    public List<Module> findAll() throws SQLException {
        List<Module> moduleList=queryRunner.query("select * from module",new BeanListHandler<>(Module.class));
        return moduleList;
    }
    @Override
    public List<Module> findModulesByRoleId(String roleid) throws SQLException {
        List<String> modulesID=queryRunner.query("select moduleId from role_module where roleId=?",new ColumnListHandler<String>(),roleid);
        moduleDao moduleDao=new moduleDaoImpl();
        List<Module> modules=new ArrayList<>();
        for (String s:modulesID){
            Module module=moduleDao.findByID(s);
            modules.add(module);
        }
        return  modules;
    }
    @Override
    public boolean isExists(String id, List<Module> moduleList) throws SQLException {
        for (Module module:moduleList){
            if(id.equals(module.getId()))
                return  true;
        }
        return  false;
    }
}
