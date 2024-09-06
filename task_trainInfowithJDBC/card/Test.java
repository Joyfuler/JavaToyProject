package space.jdbc.card;

import java.sql.Timestamp;

public class Test {
    public static void main(String[] args) {
        CardDao cardDao = new JDBCCardDao();
        System.out.println("전체카드정보 출력");
        for (Card c: cardDao.allCardList()){
            System.out.println(c);
        }
        System.out.println("====================================");

        Card insertCard = new Card("12345676543", Timestamp.valueOf("2024-09-07 12:01:03.1515"));
        System.out.println(cardDao.insertCard(insertCard) + "행 삽입되었습니다.");
        System.out.println("삽입된 카드 정보:" + cardDao.searchForCardNumber("12345676543"));
        System.out.println("====================================");

        Card updateCard = new Card("12345676543", Timestamp.valueOf("2025-09-07 12:02:04.1616"));
        System.out.println(cardDao.updateCard(updateCard) + "행 변경되었습니다.");
        System.out.println("변경된 카드 정보:" + cardDao.searchForCardNumber("12345676543"));
        System.out.println("====================================");
        System.out.println(cardDao.deleteCard("12345676543") + "행 삭제되었습니다.");
        System.out.println("====================================");
        System.out.println("(조인후) 카드가 어느 점포에서 언제 사용되었는지 확인");
        String cardNumber = "1234567890123456";
        for (Card c: cardDao.displayConfirmList(cardNumber)){
            System.out.println(c);
        }
    }
}
