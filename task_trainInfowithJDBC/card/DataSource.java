package space.jdbc.card;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String PATH = "jdbc:mysql://localhost:3306/jangdb?useSSL=false&serverTimezone=Asia/Seoul";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "12345";

    public static Connection getDataSource() {

        Connection conn = null;

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(PATH, USERNAME, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
