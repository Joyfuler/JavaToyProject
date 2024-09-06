package space.jdbc.basic;

import java.sql.*;
import java.util.Scanner;

public class Others {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(oraclePath.DRIVER);
        Connection conn = DriverManager.getConnection(mysqlPath.PATH, mysqlPath.USERNAME, mysqlPath.PASSWORD);
        String sql = "INSERT INTO Product (name, price, kind) VALUES " +
                "(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        System.out.println("======상품이름 등록메뉴입니다.======");
        System.out.println("상품이름을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("상품가격을 입력해주세요.");
        String price = scanner.nextLine();
        System.out.println("상품종류를 입력해주세요.");
        String kind = scanner.nextLine();

        pstmt.setString(1, name);
        pstmt.setString(2, price);
        pstmt.setString(3, kind);

        pstmt.executeUpdate();
        System.out.println("정상적으로 입력이 완료되었습니다.");
        conn.close();
    }
}
