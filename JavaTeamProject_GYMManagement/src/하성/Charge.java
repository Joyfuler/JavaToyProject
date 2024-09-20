package 하성;

public class Charge {
    private int charge_num;
    private int period_date;
    private int pt_count;
    private String name;

    Charge() {
    }

    public Charge(int charge_num, int period_date, int pt_count, String name) {
        this.charge_num = charge_num;
        this.period_date = period_date;
        this.pt_count = pt_count;
        this.name = name;
    }

    public int getCharge_num() {
        return charge_num;
    }

    public void setCharge_num(int charge_num) {
        this.charge_num = charge_num;
    }

    public int getPeriod_date() {
        return period_date;
    }

    public void setPeriod_date(int period_date) {
        this.period_date = period_date;
    }

    public int getPt_count() {
        return pt_count;
    }

    public void setPt_count(int pt_count) {
        this.pt_count = pt_count;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[요금제번호=" + charge_num + ", 개월수=" + period_date + ", PT 횟수=" + pt_count + ", 요금제명 =" + name + "]";
    }


}
