package ch20.task.bookSearch;

public class EducationBook extends Book{
	
	protected static final String EDUCATION_TYPE = "교육";

	public EducationBook(String type, String name, String publisher, String releaseDate) {
		super(EDUCATION_TYPE, name, publisher, releaseDate);
	}
	
	public EducationBook(String name, String publisher, String releaseDate, int price) {
		super(EDUCATION_TYPE, name, publisher, releaseDate, price);
	}
	
	public EducationBook() {
		super();
		this.type = EDUCATION_TYPE;
	}
	
}
