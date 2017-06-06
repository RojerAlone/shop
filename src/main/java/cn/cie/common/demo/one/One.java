package cn.cie.common.demo.one;

import cn.cie.common.demo.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by RojerAlone on 2017/6/1.
 */
public class One {

    public static void main(String[] args) {
        create();
//        insert();
//        update("wangwu5");
        addColumn();
    }



    public static void create() {
        String sql = "create table if not exists student (" +
                "`id` int not null auto_increment, " +
                "`name` varchar(50) not null, " +
                "PRIMARY KEY (`id`)" +
                ") ENGINE=InnoDB charset=utf8;";
        Connection con = DBUtil.getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert() {
        String sql = "insert into student(`name`) values (?);";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "zhangsan");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String name) {
        String sql = "update student set name=?";
        Connection con = DBUtil.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addColumn() {
        String sql = "alter table student add column hiredate timestamp;";
        Connection con = DBUtil.getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
