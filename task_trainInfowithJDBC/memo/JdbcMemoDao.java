package space.jdbc.memo;

import oracle.jdbc.proxy.annotation.Pre;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMemoDao implements MemoDao{

    // Connection conn = null; -- 전역변수로 사용하지 말 것. db와 상호작용 끝나면 매번 종료하자.
    @Override
    public List<Memo> findAll() {
        List<Memo> memoList = new ArrayList<Memo>();
        String sql = "SELECT * FROM MEMO ORDER BY ID DESC";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()){
                int id = rs.getInt("id");
                String content = rs.getString("content");
                memoList.add(new Memo(id, content));
            }


        } catch (Exception e){
            e.getStackTrace();
        }
        return memoList;
    }

    @Override
    public Memo findById(int id) {
        Memo findedMemo = new Memo();
        String sql = "select * from memo where id = ?";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                findedMemo.setId(id);
                findedMemo.setContent(rs.getString("content"));
            }

            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findedMemo;
    }

    @Override
    public int insert(Memo memo) {
        int result = 0;
        String sql = "INSERT INTO MEMO (CONTENT) VALUES (?)";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

             pstmt.setString(1, memo.getContent());
             result = pstmt.executeUpdate(); // insert된 줄 수. 정상적으로 입력시 1줄

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
        // try 괄호 내부에 conn 객체를 넣어 자동으로 close하도록 할 수 있음.
        // 세미콜론 사이에 두 줄을 넣고 둘다 닫도록 할 수도 있다.

    @Override
    public int update(Memo memo) {
        int result = 0;
        String sql = "update memo set content = '"+memo.getContent()+"' where id =" + memo.getId();
        try (Connection conn = DataSource.getDataSource();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            result = pstmt.executeUpdate();
            System.out.println(result + "행이 변경되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int deleteById(int id) {
        int result = 0;
        String sql = "delete from memo where id = ?";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
