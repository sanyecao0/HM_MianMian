package com.itheima.service.system;

import com.itheima.domain.common.PageBean;
import com.itheima.domain.system.SysLog;

import java.sql.SQLException;

public interface SylogService {
    PageBean findPages(int currpage, int size) throws SQLException;

    void delete(String id) throws SQLException;

    void save(SysLog sysLog) throws SQLException;
}
