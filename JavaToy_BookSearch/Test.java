package ch19.posBackup2.JavaToy_BookSearch;

import java.util.Map;

public class Test {
	public static void main(String[] args) {		
		Map<Integer, Book> allList = BookListGenerator.GetInstance().getBookList();
		allList.forEach((key, value) -> {
			System.out.println(key + "번:" + value);
		});
		
	}
}
