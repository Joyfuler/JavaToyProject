package space.jdbc.basic;

import java.sql.*;
import java.util.Scanner;

public class Others3 {
    public static void main(String[] args) {
        try {

            Class.forName(mysqlPath.DRIVER);
            Connection conn = DriverManager.getConnection(mysqlPath.PATH, mysqlPath.USERNAME, mysqlPath.PASSWORD);
            conn.setAutoCommit(false);
            String sql = "";
            Scanner scanner = new Scanner(System.in);
            System.out.println("조회할 자료를 선택해주세요. 1. 회원정보 2. 상품정보");
            String select = scanner.nextLine();

            switch (select){
                case "1":
                    sql = "SELECT * FROM MEMBER";
                    break;
                case "2":
                    sql = "SELECT * FROM PRODUCT";
                    break;
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                switch (select){
                    case "1":
                        Member member = new Member();
                        member.setId(rs.getInt("id"));
                        member.setEmail(rs.getString("email"));
                        member.setPassword(rs.getString("password"));
                        System.out.println(member);
                        break;
                    case "2":
                        Product product = new Product();
                        product.setId(rs.getInt("id"));
                        product.setName(rs.getString("name"));
                        product.setPrice(rs.getInt("price"));
                        product.setKind(rs.getString("kind"));
                        System.out.println(product);
                        break;
                }
            }
            conn.commit();
            System.out.println("정상적으로 조회 완료");

            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
