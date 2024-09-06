package space.jdbc.basic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FindAll {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driver2 = mysqlPath.DRIVER;
        String url2 = mysqlPath.PATH;
        String username2 = mysqlPath.USERNAME;
        String password2 = mysqlPath.PASSWORD;

        Connection conn = null;
        PreparedStatement pstmt = null;

        Class.forName(driver2); // 위에 작성했던 driver 경로를 이용해 실제 클래스로 가져온다.
        conn = DriverManager.getConnection(url2, username2, password2);

        pstmt = conn.prepareStatement("SELECT * FROM MEMO ORDER BY ID DESC");
        ResultSet rs = pstmt.executeQuery(); // select 실행 결과를 resultSet으로 반환
        List<Memo> memoList = new ArrayList<Memo>();
        while (rs.next()){
            int id = rs.getInt("id");
            String content = rs.getString("content");
            memoList.add(new Memo(id, content));
        }

        System.out.println("-----출력결과-----");
        for (Memo m : memoList){
            System.out.println(m);
        }
        conn.close();
    }
}
