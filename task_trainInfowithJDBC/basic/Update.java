package space.jdbc.basic;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class Update {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(mysqlPath.DRIVER);
        Connection conn = null;
        conn = DriverManager.getConnection(mysqlPath.PATH, mysqlPath.USERNAME, mysqlPath.PASSWORD);
        conn.setAutoCommit(false);
        String sql = "update memo set content = ? where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        Scanner scanner = new Scanner(System.in);
        System.out.println("바꿀 내용을 입력해주세요.");
        String content = scanner.nextLine();
        System.out.println("바꿀 id 번호를 입력해주세요.");
        String id = scanner.nextLine();
        pstmt.setString(1, content);
        pstmt.setString(2, id);
        pstmt.executeUpdate();
        System.out.println("데이터 변경이 완료되었습니다!");
        conn.commit();
        System.out.println("변경한 Memo 내용을 출력");
        String sql2 = "select * from memo where id=" + id;
        ResultSet rs = pstmt.executeQuery(sql2);
        Memo memo = new Memo();
        if (rs.next()){
            memo.setId(rs.getInt("id"));
            memo.setContent(rs.getString("content"));
            System.out.println(memo);
        }
    }
}
