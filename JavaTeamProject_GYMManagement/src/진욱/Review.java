package 진욱;

import java.sql.Timestamp;

public class Review {
    private int review_num;
    private int score;
    private String title;
    private String content;
    private Timestamp write_date;
    private int class_num;
    private String class_detail;
    private String name;
    private String login_id;

    public Review() {
    }

    public Review(int review_num, int score, String title, String content, Timestamp write_date, int class_num, String class_detail, String name) {
        this.review_num = review_num;
        this.score = score;
        this.title = title;
        this.content = content;
        this.write_date = write_date;
        this.class_num = class_num;
        this.class_detail = class_detail;
        this.name = name;
    }

    public Review(int review_num, int score, String title, String content, Timestamp write_date, int class_num, String class_detail, String name, String login_id) {
        this.review_num = review_num;
        this.score = score;
        this.title = title;
        this.content = content;
        this.write_date = write_date;
        this.class_num = class_num;
        this.class_detail = class_detail;
        this.name = name;
        this.login_id = login_id;
    }

    public Review(int score, String title, String content, int class_num) {
        this.score = score;
        this.title = title;
        this.content = content;
        this.class_num = class_num;
    }

    public Review(int reviewNum, int score, String title, String content, Timestamp writeDate, int classNum) {
        this.review_num = reviewNum;
        this.title = title;
        this.content = content;
        this.write_date = writeDate;
        this.class_num = classNum;
        this.score = score;
    }





    public int getClass_num() {
        return class_num;
    }

    public void setClass_num(int class_num) {
        this.class_num = class_num;
    }

    public String getClass_detail() {
        return class_detail;
    }

    public void setClass_detail(String class_detail) {
        this.class_detail = class_detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReview_num() {
        return review_num;
    }

    public void setReview_num(int review_num) {
        this.review_num = review_num;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Timestamp write_date) {
        this.write_date = write_date;
    }

    @Override
    public String toString() {
        return review_num + "번\t" + name + "\t★" + score + "\t" + title + "\t" + (write_date == null? "1년 전 이상": write_date) + "\n" +
                "(과목) " + class_detail + "\n(내용) " + content + "\n";
    }
}
