package 진욱;

import DataSource.DataSource;
import Gym.Logic.Logic.DAOManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCReviewDao implements ReviewDao {
    @Override
    public List<Review> allReviewList() { // 전체 리뷰 리스트를 출력한다. (테스트 완료)
        List<Review> allList = new ArrayList<Review>();

        String sql = "SELECT A.*, B.class_detail, C.name \n" +
                "FROM REVIEW A JOIN CLASS_LIST B ON A.class_num = B.class_num \n" +
                "JOIN GYM_MEMBER C ON B.member_num = C.member_num";

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()){
                    int review_num = rs.getInt("review_num");
                    int score = rs.getInt("score");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    Timestamp write_date = rs.getTimestamp("write_date");
                    int class_num = rs.getInt("class_num");
                    String class_detail = rs.getString("class_detail");
                    String name = rs.getString("name");

                    allList.add(new Review(review_num, score, title, content,
                            write_date, class_num, class_detail, name));
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allList;
    }

    @Override
    public int allReviewCount() { // 전체 리뷰 수를 불러온다. 검색시 결과 표시용도
        int count = 0;
        String sql = "SELECT COUNT(*) count FROM REVIEW";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("count");
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    @Override
    // 검색 방법에 따라 다른 검색 결과를 출력한다.
    public List<Review> searchReview(int method, String query) {
        List<Review> searchList = new ArrayList<Review>();
        String sql = "";
        Connection conn = DataSource.getDataSource();
        PreparedStatement pstmt = null;
        switch (method) { // 1. 글쓴이로 검색 2. 제목으로 검색 3. 내용으로 검색 4. 현재 로그인한 유저의 id를 통해 검색.
            case 1:
                sql = "SELECT A.*, B.class_detail, C.name \n" +
                        "FROM REVIEW A \n" +
                        "JOIN CLASS_LIST B ON A.class_num = B.class_num \n" +
                        "JOIN GYM_MEMBER C ON B.member_num = C.member_num \n" +
                        "WHERE C.name like ?";
                break;
            case 2:
                sql = "SELECT A.*, B.class_detail, C.name \n" +
                        "FROM REVIEW A \n" +
                        "JOIN CLASS_LIST B ON A.class_num = B.class_num \n" +
                        "JOIN GYM_MEMBER C ON B.member_num = C.member_num \n" +
                        "WHERE A.title like ?";
                break;

            case 3:
                sql = "SELECT A.*, B.class_detail, C.name \n" +
                        "FROM REVIEW A \n" +
                        "JOIN CLASS_LIST B ON A.class_num = B.class_num \n" +
                        "JOIN GYM_MEMBER C ON B.member_num = C.member_num \n" +
                        "WHERE A.content like ?";
                break;

            case 4:
                sql = "SELECT A.*, B.class_detail, C.name, C.login_id\n" +
                        "                        FROM REVIEW A\n" +
                        "                        JOIN CLASS_LIST B ON A.class_num = B.class_num\n" +
                        "                        JOIN GYM_MEMBER C ON B.member_num = C.member_num\n" +
                        "                        WHERE C.login_id LIKE ?";
        }
                try {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, "%"+ query +"%");
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()){
                        int review_num = rs.getInt("review_num");
                        int score = rs.getInt("score");
                        String title = rs.getString("title");
                        String content = rs.getString("content");
                        Timestamp write_date = rs.getTimestamp("write_date");
                        int class_num = rs.getInt("class_num");
                        String class_detail = rs.getString("class_detail");
                        String name = rs.getString("name");
                        String login_id = rs.getString("login_id");

                        searchList.add(new Review(review_num, score, title,
                                content, write_date, class_num, class_detail, name, login_id));
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        return searchList;
    }


    @Override
    public int insertReview(Review review) {
        int result = 0;
        String sql = "INSERT INTO REVIEW (SCORE, TITLE, CONTENT, CLASS_NUM, WRITE_DATE) \n" +
                "VALUES (?, ?, ?, ?, SYSTIMESTAMP)";

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getScore());
            pstmt.setString(2, review.getTitle());
            pstmt.setString(3, review.getContent());
            pstmt.setInt(4, review.getClass_num());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int updateReview(Review review) {
        int result = 0;
        String sql = "update review set \n" +
                "    score = ?, \n" +
                "    title = ?, \n" +
                "    content = ? \n" +
                "    where review_num =?";

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getScore());
            pstmt.setString(2, review.getTitle());
            pstmt.setString(3, review.getContent());
            pstmt.setInt(4, review.getClass_num());
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;


    }

    @Override
    public int deleteReview(int review_num) {
        int result = 0;
        String sql = "delete from review where review_num = ?";

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review_num);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int deleteReviewByClassNumber(int class_num){
        int result = 0;
        String sql = "DELETE FROM REVIEW WHERE CLASS_NUM = ?";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, class_num);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void deleteReviewByMemberNum(int member_num){
        String sql = "DELETE FROM REVIEW WHERE CLASS_NUM IN (SELECT CLASS_NUM FROM CLASS_LIST WHERE MEMBER_NUM = ?)";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, member_num);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Review getReview(int review_num) { // 리뷰 번호로 해당 리뷰 데이터를 불러온다. 수정 / 삭제 시 확인용도.
        Review oneReview = null;
        String sql = "SELECT * FROM REVIEW WHERE REVIEW_NUM = ?";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review_num);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                oneReview = new Review(rs.getInt("review_num"), rs.getInt("score"),
                        rs.getString("title"), rs.getString("content"), rs.getTimestamp("write_date"), rs.getInt("class_num"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return oneReview;
    }

    public void displayTrainerReviewScore(int select){
        String sql = "SELECT \n" +
                "        COUNT(A.SCORE) AS TOTAL, \n" +
                "        AVG(A.SCORE) AS AVERAGES \n" +
                "    FROM \n" +
                "        REVIEW A \n" +
                "        JOIN CLASS_LIST B ON A.CLASS_NUM = B.CLASS_NUM \n" +
                "        JOIN GYM_TRAINER C ON B.TRAINER_NUM = C.TRAINER_NUM \n" +
                "    WHERE\n" +
                "        C.TRAINER_NUM = ?";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setInt(1, select);
             ResultSet rs = pstmt.executeQuery();
             if (rs.next()){
                 System.out.println("검색하신 " + DAOManager.getInstance().gettDao().findByIndex(select).getName() + " 트레이너의 평균 점수: " + rs.getDouble("AVERAGES") + "점, 총 평가인원 : " + rs.getInt("TOTAL") + "명");
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
