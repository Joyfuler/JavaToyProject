package space.jdbc.train;

public class Line {
    private int id;
    private String isDriving;

    public Line(int id, String isDriving) {
        this.id = id;
        this.isDriving = isDriving;
    }

    public Line (int id){
        this.id = id;
        this.isDriving = "N";
    }

    public Line(String isDriving){
        this.isDriving = isDriving;
    }

    public int getId() {
        return id;
    }

    public String getIsDrive() {
        return isDriving;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsDriving() {
        return isDriving;
    }

    public void setIsDriving(String isDriving) {
        this.isDriving = isDriving;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", isDriving='" + isDriving + '\'' +
                '}';
    }
}
