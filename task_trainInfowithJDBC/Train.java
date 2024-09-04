package space.jdbc.train;

public class Train {
    private int train_no;
    private int train_count;
    private String driver;
    private String isExpress;
    private int id;

    public Train(){}


    public Train(int train_no, int train_count, String driver, String isExpress, int id) {
        this.train_no = train_no;
        this.train_count = train_count;
        this.driver = driver;
        this.isExpress = isExpress;
        this.id = id;
    }

    public Train(int train_no, int train_count, String driver, int id) {
        this.train_no = train_no;
        this.train_count = train_count;
        this.driver = driver;
        this.id = id;
    }

    public Train(int number, String trainCount, String driver, String id) {
        this.train_no = number;
        this.train_count = Integer.parseInt(trainCount);
        this.driver = driver;
        this.id = Integer.parseInt(id);
    }

    public Train(int anInt, String string) {
        this.train_no = anInt;
        this.isExpress = string;
    }


    public int getTrain_no() {
        return train_no;
    }

    public void setTrain_no(int train_no) {
        this.train_no = train_no;
    }

    public int getTrain_count() {
        return train_count;
    }

    public void setTrain_count(int train_count) {
        this.train_count = train_count;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getIsExpress() {
        return isExpress;
    }

    public void setIsExpress(String isExpress) {
        this.isExpress = isExpress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Train{" +
                "train_no=" + train_no +
                ", train_count=" + train_count +
                ", driver='" + driver + '\'' +
                ", isExpress='" + isExpress + '\'' +
                ", id=" + id +
                '}';
    }

    public String toString2(){
        return "Train{" +
                "train_no=" + train_no +
                ", isExpress='" + isExpress + '\'' +
                '}';
    }
}
