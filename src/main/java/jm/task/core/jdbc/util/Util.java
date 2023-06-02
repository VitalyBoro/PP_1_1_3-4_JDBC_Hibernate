package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_1";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "springcourse";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
            System.out.println("Соединение с базой данных успешно установлено");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return conn;
        }
}
