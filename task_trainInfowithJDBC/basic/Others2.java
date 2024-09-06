package space.jdbc.basic;
import java.sql.*;
import java.util.Scanner;

public class Others2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(oraclePath.DRIVER);
        Connection conn = DriverManager.getConnection(mysqlPath.PATH, mysqlPath.USERNAME, mysqlPath.PASSWORD);
        String sql = "INSERT INTO MEMBER (PASSWORD, EMAIL) VALUES " +
                "(?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        System.out.println("======멤버 등록메뉴입니다. (ID는 순번자동발행)======");
        System.out.println("비밀번호를 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        System.out.println("이메일을 입력해주세요.");
        String email = scanner.nextLine();
        pstmt.setString(1, password);
        pstmt.setString(2, email);
        pstmt.executeUpdate();
        System.out.println("정상적으로 회원입력이 완료되었습니다.");
        conn.close();
    }
}
