package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static String URL = "jdbc:mysql://localhost:3306/kata_task_1.1.3";
    private static String USERNAME = "root";
    private static String PASSWORD = "root505";

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }
}