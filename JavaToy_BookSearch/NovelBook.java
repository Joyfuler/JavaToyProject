package ch19.posBackup2.JavaToy_BookSearch;

public class NovelBook extends Book{
	
	protected static final String NOVEL_TYPE = "소설";
	
	public NovelBook() {
		super();
		this.type = NOVEL_TYPE;
	}
	
	public NovelBook(String name, String publisher, String releaseDate, int price) {
		super(NOVEL_TYPE, name, publisher, releaseDate, price);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
