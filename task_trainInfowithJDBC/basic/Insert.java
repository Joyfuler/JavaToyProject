package space.jdbc.basic;

import oracle.jdbc.pool.OraclePooledConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driver = oraclePath.DRIVER;
        String url = oraclePath.URL;
        String username = oraclePath.USERNAME;
        String password = oraclePath.PASSWORD;

        // 이하 mysql용 접속데이터

        String driver2 = mysqlPath.DRIVER;
        String url2 = mysqlPath.PATH;
        String username2 = mysqlPath.USERNAME;
        String password2 = mysqlPath.PASSWORD;

        Connection conn = null;
        PreparedStatement pstmt = null;

        Class.forName(driver2); // 위에 작성했던 driver 경로를 이용해 실제 클래스로 가져온다.
        conn = DriverManager.getConnection(url2, username2, password2);
        System.out.println(conn);

        pstmt = conn.prepareStatement("insert into memo (content) values " +
                "(?)");
        // 안녕하세요를 입력했다는 가정
        Scanner scan = new Scanner(System.in);
        System.out.println("메모입력.");
        String inputValue = scan.nextLine();
        pstmt.setString(1, inputValue);
        pstmt.executeUpdate();
        System.out.println("데이터 입력 완료!");
        conn.close(); // 반드시 닫을것!

    }
}
