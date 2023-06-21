package com.itheima.dao.system.impl;

import com.itheima.dao.system.SylogDao;
import com.itheima.domain.system.Company;
import com.itheima.domain.system.SysLog;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class SylogDaoImpl implements SylogDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from sys_log",new ScalarHandler<>());
    }

    @Override
    public List<SysLog> findPage(int start, int size) throws SQLException {
        List<SysLog> list= queryRunner.query("select * from sys_log limit ?,?",
                new BeanListHandler<>(SysLog.class), start, size);
        return  list;
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from sys_log where id=?",id);
    }

    @Override
    public void save(SysLog sysLog) throws SQLException {
        queryRunner.update("INSERT INTO sys_log VALUES (?, ?, ?, now(),?,?)"
                ,sysLog.getId(),sysLog.getUsername(),
                sysLog.getIp(),sysLog.getMethod(),sysLog.getAction());
    }
}
