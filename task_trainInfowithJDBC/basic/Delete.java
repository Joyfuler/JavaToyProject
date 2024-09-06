package space.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Delete {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = null;

        Class.forName(mysqlPath.DRIVER);
        conn = DriverManager.getConnection(mysqlPath.PATH, mysqlPath.USERNAME, mysqlPath.PASSWORD);
        String sql = "delete from memo where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        Scanner scanner = new Scanner(System.in);
        System.out.println("삭제할 데이터 번호를 입력해주세요");
        String input = scanner.nextLine();
        pstmt.setString(1, input);
        pstmt.executeUpdate();
        System.out.println("정상적으로 데이터 삭제 완료!");
    }
}
