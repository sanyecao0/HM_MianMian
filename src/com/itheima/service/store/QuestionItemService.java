package com.itheima.service.store;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.QuestionItem;

import java.sql.SQLException;
import java.util.List;

public interface QuestionItemService {
    QuestionItem findByID(String id) throws SQLException;

    PageBean findPages(int currpage, int size) throws SQLException;

    void save(QuestionItem question) throws SQLException;

    void delete(String id) throws SQLException;

    void update(QuestionItem question) throws SQLException;

    List<QuestionItem> findAll() throws SQLException;

    List<QuestionItem> findAll(String questionId) throws SQLException;
}
