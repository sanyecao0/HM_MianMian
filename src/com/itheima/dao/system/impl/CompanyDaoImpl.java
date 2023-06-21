package com.itheima.dao.system.impl;

import com.itheima.dao.system.CompanyDao;
import com.itheima.domain.system.Company;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from company",new ScalarHandler<>());
    }

    @Override
    public List<Company> findPage(int start, int size) throws SQLException {
       List<Company> companyList= queryRunner.query("select * from company limit ?,?",
               new BeanListHandler<>(Company.class), start, size);
       return  companyList;
    }

    @Override
    public void save(Company company) throws SQLException {
        queryRunner.update("insert into company values(?,?,?,?,?,?,?,?,?,?,?,?,now(),now())"
                ,company.getId(),company.getName(),company.getExpirationDate(),company.getAddress()
                ,company.getLicenseId(),company.getRepresentative(),company.getPhone(),company.getCompanySize()
                ,company.getIndustry(),company.getRemarks(),company.getState(),company.getCity());
    }
    @Override
    public Company findByID(String id) throws SQLException {
        return queryRunner.query("select * from company where id=?",new BeanHandler<>(Company.class),id);
    }

    @Override
    public void update(Company company) throws SQLException {
        queryRunner.update("update company set name=?,expirationDate=?,address=?,licenseId=?,representative=?,phone=?,companySize=?,industry=?,remarks=?,state=?,city=?,updateTime=now() where id=?"
                ,company.getName(),company.getExpirationDate(),company.getAddress()
                ,company.getLicenseId(),company.getRepresentative(),company.getPhone(),company.getCompanySize()
                ,company.getIndustry(),company.getRemarks(),company.getState(),company.getCity(),company.getId());
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from company where id=?",id);
    }
    @Override
    public List<Company> findAll() throws SQLException {
        List<Company> companyList=queryRunner.query("select * from company",new BeanListHandler<>(Company.class));
        return companyList;
    }
}
