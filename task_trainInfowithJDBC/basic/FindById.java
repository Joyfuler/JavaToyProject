package space.jdbc.basic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FindById {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driver2 = mysqlPath.DRIVER;
        String url2 = mysqlPath.PATH;
        String username2 = mysqlPath.USERNAME;
        String password2 = mysqlPath.PASSWORD;

        Connection conn = null;
        PreparedStatement pstmt = null;

        Class.forName(driver2); // 위에 작성했던 driver 경로를 이용해 실제 클래스로 가져온다.
        conn = DriverManager.getConnection(url2, username2, password2);
        pstmt = conn.prepareStatement("SELECT * FROM MEMO WHERE ID = ? ORDER BY ID DESC");
        pstmt.setInt(1, 4);
        ResultSet rs = pstmt.executeQuery(); // select 실행 결과를 resultSet으로 반환
        Memo newMemo = null;
        if (rs.next()){ //한줄 or 0줄이므로 if로 변경
            int id = rs.getInt("id");
            String content = rs.getString("content");
            newMemo = new Memo(id, content); // 출력만 한다면 if 내부에서.
            // 다른 로직에 사용할 것이라면 바깥에서 선언하여 대입.
        }
        System.out.println(newMemo);
        System.out.println("정상적으로 프로그램 종료");


    }
}
