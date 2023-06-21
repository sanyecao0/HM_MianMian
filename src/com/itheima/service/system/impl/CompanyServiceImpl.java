package com.itheima.service.system.impl;

import com.itheima.dao.system.CompanyDao;
import com.itheima.dao.system.impl.CompanyDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.Company;
import com.itheima.service.system.CompanyService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CompanyServiceImpl implements CompanyService {
    private CompanyDao companyDao=new CompanyDaoImpl();

    @Override
    public Company findByID(String id) throws SQLException {
        return companyDao.findByID(id);
    }

    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total=companyDao.findTotal();
        int start=(currpage-1)*size;
        List<Company> companyList= companyDao.findPage(start,size);
        PageBean pageBean=new PageBean(currpage,size,total,companyList);
        return pageBean;
    }

    @Override
    public void save(Company company) throws SQLException {
        String uid= UUID.randomUUID().toString();
        company.setId(uid);
        companyDao.save(company);
    }

    @Override
    public void delete(String id) throws SQLException {
         companyDao.delete(id);
    }

    @Override
    public void update(Company company) throws SQLException {
        companyDao.update(company);
    }
    @Override
    public List<Company> findAll() throws SQLException {
        return companyDao.findAll();
    }
}
