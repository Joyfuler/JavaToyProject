package space.jdbc.card;

import java.sql.Timestamp;

public class Card {
    public String cardNumber;
    public Timestamp registerDate;

    public Card_Detail card_detail;

    public Card() {}

    public Card(String cardNumber, Timestamp registerDate) {
        this.cardNumber = cardNumber;
        this.registerDate = registerDate;
    }

    public Card(String cardNumber, Timestamp registerDate, Card_Detail card_detail) {
        this.cardNumber = cardNumber;
        this.registerDate = registerDate;
        this.card_detail = card_detail;
    }

    public Card(Card_Detail cardDetail, String cardNumber) {
        this.card_detail = cardDetail;
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public Card_Detail getCard_detail() {
        return card_detail;
    }

    public void setCard_detail(Card_Detail card_detail) {
        this.card_detail = card_detail;
    }

    @Override
    public String toString() {
        return "Card[" +
                "카드번호: '" + cardNumber + '\'' +
                ", 등록일: " + (registerDate == null? "DB에서 안갖고와서 모름": registerDate) +
                ", 카드상세정보: " + (card_detail == null? "DB에서 안갖고와서 모름": card_detail) +
                ']';
    }
}
