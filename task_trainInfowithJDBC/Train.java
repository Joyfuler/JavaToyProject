package space.jdbc.train;

public class Train {
    private int train_no;
    private int train_count;
    private String driver;
    private String isExpress;

    private Line line;

    public Train(){}


    public Train(int train_no, int train_count, String driver, String isExpress, Line line) {
        this.train_no = train_no;
        this.train_count = train_count;
        this.driver = driver;
        this.isExpress = isExpress;
        this.line = line;
    }

    public Train(int train_no, int train_count, String driver, Line line) {
        this.train_no = train_no;
        this.train_count = train_count;
        this.driver = driver;
        this.line = line;
    }

    public Train(int number, String trainCount, String driver, Line line) {
        this.train_no = number;
        this.train_count = Integer.parseInt(trainCount);
        this.driver = driver;
        this.line = line;
    }

    public Train(String driver, int train_no, String isexpress, Line line){
        this.driver = driver;
        this.train_no = train_no;
        this.isExpress = isexpress;
        this.line = line;


    }

    public Train(int anInt, Line line) {
        this.train_no = anInt;
        this.line = line;
    }

    public Train(int number, String trainCount, String driver, String isExpress, Line line) {
        this.line = line;
        this.train_count = Integer.parseInt(trainCount);
        this.isExpress = isExpress;
        this.driver = driver;
        this.train_no = number;
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

    public Line getLine(){
        return line;
    }

    @Override
    public String toString() {
        return "Train[" +
                "열차번호 = " + train_no +
                ", 차량수 = " + train_count +
                ", 운전자 = '" + driver + '\'' +
                ", 급행여부 =" + (isExpress.equals("Y")? "급행":"안급행") +
                ", 호선 = " + line.getId() + "호선" +
                ", 운행여부 = " + (line.getIsDriving() == null? "조인안해서운행정보 없음": (line.getIsDriving().equals("Y")? "운행중": "안운행중")) +
                ']';
    }

}
