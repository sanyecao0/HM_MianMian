package com.itheima.service.store.Impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.itheima.dao.store.QuestionDao;
import com.itheima.dao.store.impl.QuestionDaoImpl;
import com.itheima.dao.system.CataLogDao;
import com.itheima.dao.system.CompanyDao;
import com.itheima.dao.system.impl.CataLogDaoImpl;
import com.itheima.dao.system.impl.CompanyDaoImpl;
import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.Question;
import com.itheima.service.store.QuestionService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class QuestionServiceImpl implements QuestionService {
    QuestionDao questionDao=new QuestionDaoImpl();
    @Override
    public Question findByID(String id) throws SQLException {
        return questionDao.findByID(id);
    }

    @Override
    public PageBean findPages(int currpage, int size) throws SQLException {
        Long total=questionDao.findTotal();
        int start=(currpage-1)*size;
        List<Question> questionList= questionDao.findPage(start,size);
        CompanyDao companyDao=new CompanyDaoImpl();
        CataLogDao cataLogDao=new CataLogDaoImpl();
        for(Question question:questionList){
            String id=question.getCompanyId();
            question.setCompany(companyDao.findByID(id));
            id=question.getCatalogId();
            question.setCatalog(cataLogDao.findByID(id));
        }
        PageBean pageBean=new PageBean(currpage,size,total,questionList);
        return pageBean;
    }

    @Override
    public void save(Question question) throws SQLException {
        String uid= UUID.randomUUID().toString();
        question.setId(uid);
        questionDao.save(question);
    }

    @Override
    public void delete(String id) throws SQLException {
        questionDao.delete(id);
    }

    @Override
    public void update(Question question) throws SQLException {
        questionDao.update(question);
    }

    @Override
    public List<Question> findAll() throws SQLException {
        return questionDao.findAll();
    }
    @Override
    public void toExport(OutputStream outputStream) throws SQLException, IOException {
        //1 获取所有问题信息
        List<Question> questionList = questionDao.findAll();
        EasyExcel.write(outputStream,Question.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("question").doWrite(questionList);

    }
}
