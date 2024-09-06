package space.jdbc.card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCardDao implements CardDao{
    @Override
    public List<Card> allCardList() {
        List<Card> allCardList = new ArrayList<>();
        String sql = "SELECT * FROM CARD ORDER BY registerdate DESC";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                allCardList.add(new Card(rs.getString("cardnumber"),
                                         rs.getTimestamp("registerdate")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return allCardList;
    }

    @Override
    public Card searchForCardNumber(String cardNumber) {
        Card searchedCard = new Card();
        String sql = "select * from card where cardnumber = ?";

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cardNumber);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()){
                    searchedCard = new Card(rs.getString("cardnumber"), rs.getTimestamp("registerdate"));
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return searchedCard;
    }

    @Override
    public int insertCard(Card card) {
        int result = 0;
        String sql = "insert into card \n" +
                "values (?, ?);";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, card.getCardNumber());
            pstmt.setTimestamp(2, card.getRegisterDate());
            result = pstmt.executeUpdate(); // insert된 줄 수. 정상적으로 입력시 1줄

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int updateCard(Card card) {
        int result = 0;
        String sql = "update card set registerdate = ? where cardnumber = ?";
        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setTimestamp(1, card.getRegisterDate());
            pstmt.setString(2, card.getCardNumber());

            result = pstmt.executeUpdate();
            System.out.println(result + "행이 변경되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int deleteCard(String cardNumber) {
        int result = 0;
        String sql = "delete from card where cardnumber = ?";
        try (Connection conn = space.jdbc.member.DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cardNumber);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Card> displayConfirmList(String cardNumber) {
        List<Card> cardDetailList = new ArrayList<Card>();
        String sql = "select confirmid, companyid, confirmdate, cardnumber \n" +
                "from card natural join card_detail \n" +
                "where cardnumber = ?;";

        try (Connection conn = DataSource.getDataSource();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cardNumber);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                cardDetailList.add(new Card(
                        new Card_Detail(rs.getInt("confirmid"), rs.getString("companyid"), rs.getTimestamp("confirmdate")),
                        rs.getString("cardnumber")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cardDetailList;
    }
}
