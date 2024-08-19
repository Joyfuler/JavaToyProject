package ch19.posBackup2.JavaToy_BookSearch;

public class Book {
	protected String type;
	protected String name;
	protected String publisher;
	protected String releaseDate;
	protected int price;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
		
	public Book() {
		
	}
	public Book(String type, String name, String publisher, String releaseDate) {
		this.type = type;
		this.name = name;
		this.publisher = publisher;
		this.releaseDate = releaseDate;
	}
	
	
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Book(String type, String name, String publisher, String releaseDate, int price) {
		super();
		this.type = type;
		this.name = name;
		this.publisher = publisher;
		this.releaseDate = releaseDate;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "[타입: " + type + ", 이름: " + name + ", 출판사: " + publisher + ", 발매일: " + releaseDate
				+ ", 가격: " + price + "원]\n";
	}
	
}
