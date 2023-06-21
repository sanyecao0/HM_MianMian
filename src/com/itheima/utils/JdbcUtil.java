package com.itheima.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Jdbc以及事务控制类
 */
public class JdbcUtil {
    //创建连接池对象
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    //创建线程本地变量，保存connection连接对象
    private static ThreadLocal<Connection> tl=new ThreadLocal<>();

    /**
     * 获取连接池
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 从连接池获取连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //优先从ThreadLocal中获取连接对象
        Connection conn = tl.get();
        //如果没有获取到就从连接池中获取连接对象
        if(conn==null){
            conn=dataSource.getConnection();
            //保存到ThreadLocal中，下次使用
            tl.set(conn);
        }
        return conn;
    }

    /**
     * 开启事务
     */
    public static void startTransaction() throws SQLException {
        //优先从ThreadLocal中获取连接对象
        Connection conn = tl.get();
        if(conn!=null) {
            conn.setAutoCommit(false);
        }
    }

    /**
     * 提交事务
     */
    public static void commit() throws SQLException {
        //优先从ThreadLocal中获取连接对象
        Connection conn = tl.get();
        if(conn!=null) {
            conn.commit();
        }
    }

    /**
     * 回滚事务
     */
    public static void rollback() throws SQLException{
        //优先从ThreadLocal中获取连接对象
        Connection conn = tl.get();
        if(conn!=null) {
            conn.rollback();
        }
    }

    /**
     * 释放资源
     */
    public static void close() throws SQLException{
        //优先从ThreadLocal中获取连接对象
        Connection conn = tl.get();
        if(conn!=null) {
            tl.remove();
            conn.close();
        }
    }
}
