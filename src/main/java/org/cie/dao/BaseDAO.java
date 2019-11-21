package org.cie.dao;

import org.cie.entities.User;
import org.cie.utilities.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {

    Connection connection;
    private PropertiesUtil myProperties = null;

    /**
     * 新建此类时自动连接数据库
     *
     * @throws ClassNotFoundException 找不到AccessDriver
     * @throws SQLException           连接不到数据库
     */
    BaseDAO() throws ClassNotFoundException, SQLException {
        myProperties = new PropertiesUtil();
        Class.forName(myProperties.getProperty("classname"));
        connection = DriverManager.getConnection(
                myProperties.getProperty("url"),
                myProperties.getProperty("username"),
                myProperties.getProperty("password"));
    }

    public void reconnect() throws SQLException {
        connection = DriverManager.getConnection(
                myProperties.getProperty("url"),
                myProperties.getProperty("username"),
                myProperties.getProperty("password"));
    }

    public abstract void add(User user);

    public abstract void delete(String userName);

    public abstract User find(String userName);

    public abstract void change(User user);

}
