package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.connection.url", URL);
            configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            configuration.setProperty("hibernate.connection.username", USERNAME);
            configuration.setProperty("hibernate.connection.password", PASSWORD);
            configuration.setProperty("hibernate.current_session_context_class", "thread");
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.addAnnotatedClass(User.class);

            sessionFactory = configuration.buildSessionFactory();

        return sessionFactory;
    }
}