package space.jdbc.card;

import java.util.List;

public interface CardDao {
    public List<Card> allCardList();
    public Card searchForCardNumber(String cardNumber);
    public int insertCard (Card card);
    public int updateCard (Card card);
    public int deleteCard(String cardNumber);
    public List<Card> displayConfirmList(String cardNumber);
}
