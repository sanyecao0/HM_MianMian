package com.itheima.dao.store.impl;

import com.itheima.dao.store.QuestionItemDao;
import com.itheima.domain.store.QuestionItem;
import com.itheima.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class QuestionItemDaoImpl implements QuestionItemDao {
    private QueryRunner queryRunner=new QueryRunner(JdbcUtil.getDataSource());
    @Override
    public void save(QuestionItem question) throws SQLException {
        queryRunner.update("insert into question_item values(?,?,?,?)"
                ,question.getId(),question.getQuestionId(),question.getContent(),question.getIsRight());
    }

    @Override
    public List<QuestionItem> findAll() throws SQLException {
        List<QuestionItem> questions=queryRunner.query("select * from question_item",new BeanListHandler<>(QuestionItem.class));
        return questions;
    }

    @Override
    public void update(QuestionItem question) throws SQLException {
        queryRunner.update("update question_item set questionId=?,content=?,isRight=? where id=?"
                ,question.getQuestionId(),question.getContent(),question.getIsRight(), question.getId());
    }

    @Override
    public void delete(String id) throws SQLException {
        queryRunner.update("delete from question_item where id=?",id);
    }

    @Override
    public Long findTotal() throws SQLException {
        return queryRunner.query("select count(*) from question_item",new ScalarHandler<>());
    }

    @Override
    public List<QuestionItem> findPage(int start, int size) throws SQLException {
        List<QuestionItem> questions= queryRunner.query("select * from question_item limit ?,?",
                new BeanListHandler<>(QuestionItem.class), start, size);
        return questions;
    }

    @Override
    public QuestionItem findByID(String id) throws SQLException {
        return queryRunner.query("select * from question_item where id=?",new BeanHandler<>(QuestionItem.class),id);
    }
    @Override
    public List<QuestionItem> findAll(String questionId) throws SQLException {
        return queryRunner.query("select * from question_item where questionId=?",new BeanListHandler<>(QuestionItem.class),questionId);
    }
}

