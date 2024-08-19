package ch20.task.bookSearch;

import java.util.Map;
import java.util.Scanner;

public class BookMain {
	public static void main(String[] args) {
		
		AddBook ab = new AddBook();
		BookWrite bw = new BookWrite();
		BookListGenerator blg = new BookListGenerator();
		//Map<Integer, Book> allBookMap = blg.getBookList();
		BookModify bm = new BookModify();		
		BookSearch bs = new BookSearch();
		
		
		System.out.println("어서오세요. 도서정보 관리 시스템입니다.");
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("원하시는 기능을 선택해주세요. 1. 도서 리스트 확인 2.도서 등록 3. 도서 수정 4. 도서 검색 5. 프로그램 종료");
			int select = Integer.parseInt(sc.nextLine());
			switch (select) {
			case 1:			
				Map<Integer, Book> allBookMap = blg.getBookList();
				System.out.println(allBookMap);				
				break;
			case 2:
				System.out.println("도서 등록을 선택하셨습니다.");
				allBookMap = ab.registerBook();
				bw.registerToFile(allBookMap);				
				break;			
			case 3:
				allBookMap = blg.getBookList();
				System.out.println(allBookMap);
				System.out.println("도서 수정 메뉴를 선택하셨습니다. 위 목록 중 수정하고 싶은 도서의 인덱스를 선택해주세요.");
				select = Integer.parseInt(sc.nextLine());
				bm.getBookInfoAndModify(select);
				break;
			case 4:
				System.out.println("도서 검색을 선택하셨습니다.");
				
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				System.exit(1);
				break;
			default:
				System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
				break;
			}
		}		
	}
}
