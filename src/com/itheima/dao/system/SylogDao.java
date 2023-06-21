package com.itheima.dao.system;
import com.itheima.domain.system.SysLog;

import java.sql.SQLException;
import java.util.List;

public interface SylogDao {
    Long findTotal() throws SQLException;

    List<SysLog> findPage(int start, int size) throws SQLException;

    void delete(String id) throws SQLException;

    void save(SysLog sysLog) throws SQLException;
}
