package com.itheima.service.store.Impl;


import com.itheima.dao.store.QuestionItemDao;
import com.itheima.dao.store.impl.QuestionItemDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.QuestionItem;
import com.itheima.service.store.QuestionItemService;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class QuestionItemServiceImpl implements QuestionItemService {
    QuestionItemDao questionDao=new QuestionItemDaoImpl();
    @Override
    public QuestionItem findByID(String id) throws SQLException {
        return questionDao.findByID(id);
    }

    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total=questionDao.findTotal();
        int start=(currpage-1)*size;
        List<QuestionItem> questionList= questionDao.findPage(start,size);
        //CompanyDao companyDao=new CompanyDaoImpl();
       // CataLogDao cataLogDao=new CataLogDaoImpl();
        /*for(QuestionItem question:questionList){
            String id=question.getCompanyId();
            question.setCompany(companyDao.findByID(id));
            id=question.getCatalogId();
            question.setCatalog(cataLogDao.findByID(id));
        }*/
        PageBean pageBean=new PageBean(currpage,size,total,questionList);
        return pageBean;
    }

    @Override
    public void save(QuestionItem question) throws SQLException {
        String uid= UUID.randomUUID().toString();
        question.setId(uid);
        questionDao.save(question);
    }

    @Override
    public void delete(String id) throws SQLException {
        questionDao.delete(id);
    }

    @Override
    public void update(QuestionItem question) throws SQLException {
        questionDao.update(question);
    }

    @Override
    public List<QuestionItem> findAll() throws SQLException {
        return questionDao.findAll();
    }
    @Override
    public List<QuestionItem> findAll(String questionId) throws SQLException {
        return questionDao.findAll(questionId);
    }
}
