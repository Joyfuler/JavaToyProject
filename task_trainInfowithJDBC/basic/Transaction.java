package space.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Transaction {
    public static void main(String[] args) throws Exception{


        try {

            Class.forName(mysqlPath.DRIVER);
            Connection conn = null;
            boolean exceptionYN = true;
            conn = DriverManager.getConnection(mysqlPath.PATH, mysqlPath.USERNAME, mysqlPath.PASSWORD);
            conn.setAutoCommit(false); // 자동커밋하지 못하도록
            PreparedStatement pstmt = conn.prepareStatement("update emp set sal = sal * 1.1 where deptno = 10");
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement("update emp set sal = sal * 1.2 where deptno = 20");
            pstmt.executeUpdate();
            pstmt = conn.prepareStatement("update emp set sal = sal * 1.3 where deptno = 30");
            pstmt.executeUpdate();



            conn.commit();
            System.out.println("정상적으로 업데이트 완료됨");
            conn.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }


    }

}
