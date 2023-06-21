package com.itheima.service.store;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.store.Question;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public interface QuestionService {
    Question findByID(String id) throws SQLException;

    PageBean findPages(int currpage, int size) throws SQLException;

    void save(Question question) throws SQLException;

    void delete(String id) throws SQLException;

    void update(Question question) throws SQLException;

    List<Question> findAll() throws SQLException;


    void toExport(OutputStream outputStream) throws SQLException, IOException;
}
