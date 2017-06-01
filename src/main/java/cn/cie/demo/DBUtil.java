package cn.cie.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by RojerAlone on 2017/6/1.
 */
public class DBUtil {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/shop";
    private static final String username = "root";
    private static final String password = "123456";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("驱动没有找到" + e);
        } catch (SQLException e) {
            System.out.println("获取连接失败" + e);
        }
        return connection;
    }

}
