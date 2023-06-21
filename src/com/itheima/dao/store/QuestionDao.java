package com.itheima.dao.store;

import com.itheima.domain.store.Question;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDao {
    void save(Question question) throws SQLException;

    void update(Question question) throws SQLException;

    Question findByID(String id) throws SQLException;

    Long findTotal() throws SQLException;

    List<Question> findPage(int start, int size) throws SQLException;

    List<Question> findAll() throws SQLException;

    void delete(String id) throws SQLException;
}
