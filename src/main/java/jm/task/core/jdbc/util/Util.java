package jm.task.core.jdbc.util;

import com.mysql.cj.MysqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String NAME_USER = "root";
    private static final String PASSWORD = "adelfitnesinfo";
    private static final String URL = "jdbc:mysql://localhost:3306/dbtest";

    public static Connection connect()  {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.getStackTrace();
        }

        try {
            Connection con = DriverManager.getConnection(URL,NAME_USER,PASSWORD);
            con.setAutoCommit(false);
            return con;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // реализуйте настройку соеденения с БД
}
