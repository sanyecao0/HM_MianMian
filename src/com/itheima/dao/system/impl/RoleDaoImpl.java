package com.itheima.dao.system.impl;

import com.itheima.dao.system.RoleDao;
import com.itheima.domain.system.Role;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());


    @Override
    public void save(Role role) throws SQLException {
        queryRunner.update("insert into role values(?,?,?,now(),now())"
                ,role.getId(),role.getName(),role.getRemark());
    }

    @Override
    public void update(Role role) throws SQLException {
        queryRunner.update("update role set name=?,remark=?,updateTime=now() where id=?"
                ,role.getName(),role.getRemark(),role.getId());
    }

    @Override
    public Role findByID(String id) throws SQLException {
        return queryRunner.query("select * from role where id=?",new BeanHandler<>(Role.class),id);
    }

    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from role",new ScalarHandler<>());
    }

    @Override
    public List<Role> findPage(int start, int size) throws SQLException {
        List<Role> roleList= queryRunner.query("select * from role limit ?,?",
                new BeanListHandler<>(Role.class), start, size);
        return roleList;
    }

    @Override
    public List<Role> findAll() throws SQLException {
        List<Role> role=queryRunner.query("select * from role",new BeanListHandler<>(Role.class));
        return role;
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from role where id=?",id);
    }
    @Override
    public  void updateRoleAndModules(List<String> moduleIDs, String roleid) throws SQLException {
        queryRunner.update("delete from role_module where roleId=?",roleid);
        for(String s:moduleIDs){
            queryRunner.update("insert into role_module values(?,?)",roleid,s);
        }
    }

    @Override
    public List<String> findRoleIdsByUserId(String id) throws SQLException {
        return queryRunner.query("select roleId from user_role where userId=?",new ColumnListHandler<>(),id);

    }
}
