package ch19.posBackup2.JavaToy_BookSearch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddBook {
	
	private static AddBook instance = null;
	private AddBook() {}
	
	public static AddBook getInstance() {
		if (instance == null) {
			instance = new AddBook();
		}
		
		return instance;
	}
	
	
	public Map<Integer, Book> registerBook(){
		Map<Integer, Book> registeredMap = BookListGenerator.GetInstance().getBookList();
		Map<Integer, Book> newBookMap = new HashMap<Integer, Book>();	
		// 새롭게 등록할 책 정보를 담을 Map 객체.
		Book newBook = null;
		Scanner scanner = new Scanner(System.in);
		int size = registeredMap.isEmpty()? 0: Collections.max(registeredMap.keySet()) +1 ;		
		System.out.println("새로 등록될 정보의 인덱스: " + size); // 현재 등록된 map의 key 중 가장 큰 값을 가져온다. 다음 값에는 가장 큰 값에 +1을 하여 key값을 대입한다.
		System.out.println("등록할 책의 타입을 숫자로 입력해주세요 : 1.교육 2. 수필 3. 소설");
		String answer = scanner.nextLine();
		if (Integer.parseInt(answer) == 1) {
			newBook = new EducationBook();
		} else if (Integer.parseInt(answer) == 2) {
			newBook = new EssayBook();
		} else {
			newBook = new NovelBook();
		}
		
		System.out.println("등록할 책의 이름은?");
		String answer2 = scanner.nextLine();
		System.out.println("출판사를 입력해주세요.");
		String answer3 = scanner.nextLine();
		System.out.println("발매일을 입력해주세요. 형식은 0000-00-00 으로 입력해주세요.");
		String answer4 = scanner.nextLine();
		System.out.println("책의 가격을 입력해주세요(숫자만 입력)");
		int answer5 = Integer.parseInt(scanner.nextLine());		
		newBook.setName(answer2);
		newBook.setPublisher(answer3);
		newBook.setReleaseDate(answer4);
		newBook.setPrice(answer5);
		newBookMap.put(size, newBook); //키값은 기존 Map의 인덱스의 가장 마지막. 그 후 입력한 Book객체를 대입.			
		return newBookMap;		
	}
}
