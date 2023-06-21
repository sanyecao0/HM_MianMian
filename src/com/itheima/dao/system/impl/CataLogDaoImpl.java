package com.itheima.dao.system.impl;

import com.itheima.dao.system.CataLogDao;
import com.itheima.domain.store.Catalog;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CataLogDaoImpl implements CataLogDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
    @Override
    public List<Catalog> findAll() throws SQLException {
        List<Catalog> catalogList=queryRunner.query("select * from catalog",new BeanListHandler<>(Catalog.class));
        return catalogList;
    }
    @Override
    public List<Catalog> findPage(int start, int size) throws SQLException {
        List<Catalog> catalogList= queryRunner.query("select * from catalog limit ?,?",
                new BeanListHandler<>(Catalog.class), start, size);
        return catalogList;
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from catalog where id=?",id);
    }

    @Override
    public void save(Catalog catalog) throws SQLException {
        queryRunner.update("insert into catalog values(?,?,?,?,?,now(),now())"
                ,catalog.getId(),catalog.getName(),catalog.getState(),catalog.getRemark(),catalog.getCourseId());
    }

    @Override
    public void update(Catalog catalog) throws SQLException {
        queryRunner.update("update catalog set name=?,state=?,remark=?,courseId=?,updateTime=now() where id=?"
                ,catalog.getName(),catalog.getState(),catalog.getRemark(),catalog.getCourseId(),catalog.getId());
    }

    @Override
    public Catalog findByID(String id) throws SQLException {
        return queryRunner.query("select * from catalog where id=?",new BeanHandler<>(Catalog.class),id);
    }

    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from catalog",new ScalarHandler<>());
    }
}
