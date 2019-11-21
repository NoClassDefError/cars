package org.cie.dao;

import org.cie.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDaoImpl extends BaseDAO {

    /**
     * 新建此类时自动连接数据库，并创建Statement接口
     *
     * @throws ClassNotFoundException 找不到AccessDriver
     * @throws SQLException           连接不到数据库
     */
    public BaseDaoImpl() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public void add(User user) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("INSERT INTO student2 VALUES (?,?)");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPasswrd());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String userName) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("DELETE FROM student2 WHERE username=?");
            preparedStatement.setString(1, userName);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User find(String userName) {
        User user = null;
        try {
            Statement statement = connection.createStatement();
            System.out.println(String.format("select * from student2.student2 where username='%s'", userName));
            ResultSet resultSet = statement.executeQuery(String.format("select * from student2.student2 where username='%s'", userName));
            //PreparedStatement preparedStatement
            //        = connection.prepareStatement("SELECT * FROM student2 WHERE username=?");
            //preparedStatement.setString(1, userName);
            //ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserName(resultSet.getString(1));
                user.setPasswrd(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void change(User user) {
        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("UPDATE student2 SET username = ?,password = ?");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPasswrd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
