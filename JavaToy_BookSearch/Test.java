package ch19.posBackup2.JavaToy_BookSearch;

import java.util.Map;

public class Test {
	public static void main(String[] args) {
		BookListGenerator blg = new BookListGenerator();
		Map<Integer, Book> allList = blg.getBookList();
		allList.forEach((key, value) -> {
			System.out.println(key + "번:" + value);
		});
		
		
		
		
	}
}
