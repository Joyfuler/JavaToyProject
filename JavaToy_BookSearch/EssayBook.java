package ch19.posBackup2.JavaToy_BookSearch;

public class EssayBook extends Book{

	protected static final String ESSAY_TYPE = "수필";
	
	public EssayBook(String type, String name, String publisher, String releaseDate, int price) {
		super(ESSAY_TYPE, name, publisher, releaseDate, price);
		// TODO Auto-generated constructor stub
	}
	
	public EssayBook(String name, String publisher, String releaseDate, int price) {
		super(ESSAY_TYPE, name, publisher, releaseDate, price);
		// TODO Auto-generated constructor stub
	}
	
	public EssayBook() {
		this.type = ESSAY_TYPE;
	}

}
