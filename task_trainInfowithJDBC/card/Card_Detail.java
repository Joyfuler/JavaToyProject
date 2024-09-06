package space.jdbc.card;

import java.sql.Timestamp;

public class Card_Detail {
    public int confirmId;
    public String companyId;
    public Timestamp confirmDate;
    public Card card;

    public Card_Detail(int confirmId, String companyId, Timestamp confirmDate, Card card) {
        this.confirmId = confirmId;
        this.companyId = companyId;
        this.confirmDate = confirmDate;
        this.card = card;
    }

    public Card_Detail(int confirmId, String companyId, Timestamp confirmDate) {
        this.confirmId = confirmId;
        this.companyId = companyId;
        this.confirmDate = confirmDate;
    }

    public Card_Detail() {}

    public int getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(int confirmId) {
        this.confirmId = confirmId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Timestamp getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Timestamp confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "카드상세[" +
                "승인번호=" + confirmId +
                ", 업체명='" + companyId + '\'' +
                ", 승인날짜=" + confirmDate +
                "]";
    }
}
