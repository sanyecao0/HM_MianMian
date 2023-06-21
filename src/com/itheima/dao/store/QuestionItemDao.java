package com.itheima.dao.store;

import com.itheima.domain.store.QuestionItem;

import java.sql.SQLException;
import java.util.List;

public interface QuestionItemDao {
    void save(QuestionItem question) throws SQLException;

    List<QuestionItem> findAll() throws SQLException;

    void update(QuestionItem question) throws SQLException;

    void delete(String id) throws SQLException;

    Long findTotal() throws SQLException;

    List<QuestionItem> findPage(int start, int size) throws SQLException;

    QuestionItem findByID(String id) throws SQLException;

    List<QuestionItem> findAll(String questionId) throws SQLException;
}
