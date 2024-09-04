package ch19.posBackup2.JavaToy_BookSearch;

import java.util.Map;
import java.util.Scanner;

public class BookMain {
	public static void main(String[] args) {		
		System.out.println("어서오세요. 도서정보 관리 시스템입니다.");
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("원하시는 기능을 선택해주세요. 1. 총 도서 리스트 확인 2.도서 등록 3. 도서 수정 4. 도서 검색 5. 도서 삭제 6. 프로그램 종료");
			int select = Integer.parseInt(sc.nextLine());
			switch (select) {
			case 1:			
				Map<Integer, Book> allBookMap = BookListGenerator.GetInstance().getBookList();
				System.out.println(allBookMap);		
				System.out.println("총 [" + allBookMap.size() +"] 건의 도서가 검색되었습니다.");
				break;
			case 2:
				System.out.println("도서 등록을 선택하셨습니다.");
				allBookMap = AddBook.getInstance().registerBook();
				BookWrite.getInstance().registerToFile(allBookMap);				
				break;			
			case 3:
				allBookMap = BookListGenerator.GetInstance().getBookList();
				System.out.println(allBookMap);
				System.out.println("도서 수정 메뉴를 선택하셨습니다. 위 목록 중 수정하고 싶은 도서의 인덱스를 선택해주세요.");
				select = Integer.parseInt(sc.nextLine());
				BookModify.getInstance().getBookInfoAndModify(select);				
				break;
			case 4:
				System.out.println("도서 검색을 선택하셨습니다.");
				BookSearch.getInstance().bookSearch();				
				break;
			
			case 5:
				System.out.println("도서 삭제를 선택하셨습니다.");
				Map<Integer, Book> deleteBookMap = BookDelete.getInstance().bookDelete();
				BookWrite.getInstance().removeAndUpdateToFile(deleteBookMap);
				break;				
			case 6:
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
