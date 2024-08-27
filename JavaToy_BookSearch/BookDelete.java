package ch19.posBackup2.JavaToy_BookSearch;

import java.util.Map;
import java.util.Scanner;

public class BookDelete {

	private static BookDelete instance = null;
	private Scanner scanner = new Scanner(System.in);
	private BookDelete() {}
	
	public static BookDelete getInstance() {
		if (instance == null)
			instance = new BookDelete();		
		return instance; 		
	}
	
	public Map<Integer, Book> bookDelete() {
		Map<Integer, Book> allBookMap =
		BookListGenerator.GetInstance().getBookList();
		System.out.println(allBookMap);
		System.out.println("삭제할 책의 인덱스를 입력해주세요.");
		int index = Integer.parseInt(scanner.nextLine());
		Book toRemoveBook = BookListGenerator.GetInstance().getBookList().get(index);
		System.out.println("삭제하려는 책의 정보는 다음과 같습니다.");
		System.out.println(toRemoveBook);
		System.out.println("정말 삭제를 원하시면 1번을, 취소하시려면 2번을 눌러주세요.");	
		int select = Integer.parseInt(scanner.nextLine());
		if (select == 1) {
			allBookMap.remove(index);
		} else {
			System.out.println("삭제가 취소되었습니다.");			
		}
		
		return allBookMap;
	}
}
