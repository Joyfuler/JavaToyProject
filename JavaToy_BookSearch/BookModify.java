package ch20.task.bookSearch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;

public class BookModify {
	public void getBookInfoAndModify(int select) {
		BookListGenerator blg = new BookListGenerator();
		Map<Integer, Book> bookMap = blg.getBookList();
		
		// 선택한 bookMap의 정보 출력.
		System.out.println("당신이 선택한 도서 정보는 아래와 같습니다.");		
		System.out.println(bookMap.get(select));
		Scanner sc = new Scanner(System.in);
		System.out.println("수정하고 싶은 정보를 선택해주세요. 1. 제목 / 2. 출판사 / 3. 출판일 / 4. 가격");
		int answer = Integer.parseInt(sc.nextLine());
		switch (answer) {
		case 1:
			System.out.print("변경 전 제목:"+ bookMap.get(select).getName() + ", 변경 후:");
			String newTitle = sc.nextLine();
			bookMap.get(select).setName(newTitle);
			System.out.println("제목이 성공적으로 변경되었습니다.");
			break;
		case 2:
			System.out.print("변경 전 출판사: " + bookMap.get(select).getPublisher() + ", 변경 후:");
			String newPublisher = sc.nextLine();
			bookMap.get(select).setPublisher(newPublisher);
			System.out.println("출판사가 성공적으로 변경되었습니다.");
			break;
		case 3:
			System.out.print("변경 전 출판년도: " + bookMap.get(select).getReleaseDate() + ", 변경 후(0000-00-00 형식 입력:");
			String newReleaseDate = sc.nextLine();
			bookMap.get(select).setReleaseDate(newReleaseDate);
			System.out.println("발간일이 성공적으로 변경되었습니다.");
			break;
		case 4:
			System.out.print("변경 전 가격: " + bookMap.get(select).getPrice() + ", 변경 후 (숫자만 입력):");
			int newPrice = Integer.parseInt(sc.nextLine());					
			bookMap.get(select).setPrice(newPrice);
			System.out.println("가격이 성공적으로 변경되었습니다.");
			break;			
		}
		
		updateFile(bookMap);
		//sc.close();
		
		}
	
	// 위에 수정 반영된 bookMap을 실제 텍스트 파일로 저장.
	public void updateFile(Map<Integer, Book> bookMap) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\asiae\\eclipse-workspace\\com.lecture.practice\\src\\ch20\\task\\bookSearch\\Book.txt"))){
			for (Book book: bookMap.values()) {
				writer.write(book.getType()+ ";" 
							+ book.getName() + ";"
							+ book.getPublisher() + ";"
							+ book.getReleaseDate() + ";"
							+ String.valueOf(book.getPrice()) +";");
				writer.newLine(); // 매 줄이 끝날 때마다 줄바꿈.
				
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
