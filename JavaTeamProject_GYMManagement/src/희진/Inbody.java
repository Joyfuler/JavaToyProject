package 희진;

import java.sql.Timestamp;

public class Inbody {
	// 인바디 DTO 클래스
	private int inbody_Num;
	private Timestamp m_date;
	private double weight;
	private double height;
	private double fat;
	private double muscle;
	private int body_age;
	private int body_score;
	private int member_num;
	
	Inbody(){}

	public Inbody(Timestamp m_date, double weight, double height, double fat, double muscle, int body_age,
			int body_score, int member_num) {
		super();
		this.m_date = m_date;
		this.weight = weight;
		this.height = height;
		this.fat = fat;
		this.muscle = muscle;
		this.body_age = body_age;
		this.body_score = body_score;
		this.member_num = member_num;
	}
	
	public int getInbody_Num() {
		return inbody_Num;
	}

	public void setInbody_Num(int inbodyNum) {
		this.inbody_Num = inbodyNum;
	}

	public Timestamp getM_date() {
		return m_date;
	}

	public void setM_date(Timestamp m_date) {
		this.m_date = m_date;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getMuscle() {
		return muscle;
	}

	public void setMuscle(double muscle) {
		this.muscle = muscle;
	}

	public int getBody_age() {
		return body_age;
	}

	public void setBody_age(int body_age) {
		this.body_age = body_age;
	}

	public int getBody_score() {
		return body_score;
	}

	public void setBody_score(int body_score) {
		this.body_score = body_score;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	@Override
	public String toString() {
		return "인바디 데이터 [inbody_Num=" + inbody_Num + "\n 측정일=" + m_date + "\n 몸무게=" + weight + "\n 신장=" + height
				+ "\n 지방량=" + fat + "\n 근골격량=" + muscle + "\n 신체연령=" + body_age + "살\n body_score=" + body_score
				+ "점\n 회원번호=" + member_num + "]";
	}
}	