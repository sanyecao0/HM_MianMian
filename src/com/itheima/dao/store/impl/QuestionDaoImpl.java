package com.itheima.dao.store.impl;

import com.itheima.dao.store.QuestionDao;
import com.itheima.domain.store.Question;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
    @Override
    public void save(Question question) throws SQLException {
        queryRunner.update("insert into question values(?,?,?,?,?,?,?,?,?,?,?,?,now(),now())"
                ,question.getId(),question.getCatalogId(),question.getSubject(),question.getType(),
                question.getDifficulty(),question.getPicture(),question.getAnalysis(),question.getRemark(),
                question.getIsClassic(),question.getState(),question.getReviewStatus(),question.getCompanyId()
                );
    }

    @Override
    public void update(Question question) throws SQLException {
        queryRunner.update("update question set catalogId=?,subject=?,type=?,difficulty=?,picture=?,analysis=?,remark=?,isClassic=?,state=?,reviewStatus=?,companyId=?, updateTime=now() where id=?"
                ,question.getCatalogId(),question.getSubject(),question.getType(),
                question.getDifficulty(),question.getPicture(),question.getAnalysis(),question.getRemark(),
                question.getIsClassic(),question.getState(),question.getReviewStatus(),question.getCompanyId(), question.getId());
    }

    @Override
    public Question findByID(String id) throws SQLException {
        return queryRunner.query("select * from question where id=?",new BeanHandler<>(Question.class),id);
    }

    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from question",new ScalarHandler<>());
    }

    @Override
    public List<Question> findPage(int start, int size) throws SQLException {
        List<Question> questions= queryRunner.query("select * from question limit ?,?",
                new BeanListHandler<>(Question.class), start, size);
        return questions;
    }

    @Override
    public List<Question> findAll() throws SQLException {
        List<Question> questions=queryRunner.query("select * from question",new BeanListHandler<>(Question.class));
        return questions;
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from question where id=?",id);
    }
}
