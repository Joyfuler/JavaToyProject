package ch19.posBackup2.JavaToy_BookSearch;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookSearch {
	Scanner scanner = new Scanner(System.in);
	BookListGenerator blg = new BookListGenerator();
	Map<Integer, Book> allBookList = blg.getBookList();
	
	
	public void bookSearchByWord() {		
		System.out.println("1. 타입으로 검색 2. 제목으로 검색 3.출판사로 검색 4.발매일로 검색");
		int select = Integer.parseInt(scanner.nextLine());
		switch (select) {
		case 1:
			bookSearchByType();
			break;
			
		case 2:
			bookSearchByTitle();
			break;
			
		case 3:
			bookSearchByPublisher();
			break;
			
		case 4:
			bookSearchByReleaseDate();
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			break;
		}
	}

	public void bookSearchByReleaseDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("검색을 원하는 기간을 입력해주세요. (2020-01-20 형식으로 입력) 시작 일자:");
		String releaseStartDate = scanner.nextLine();
		System.out.println("종료 일자를 입력해주세요. (2020-02-20 형식으로 입력) :");
		String releaseEndDate = scanner.nextLine();		
		
		try {			
			Date startDate = sdf.parse(releaseStartDate);
			Date endDate = sdf.parse(releaseEndDate);
			int count = 0;
			for (Map.Entry<Integer, Book> entry : allBookList.entrySet()) {				
				Date bookDate = sdf.parse(entry.getValue().releaseDate);				
				if ((bookDate.equals(startDate) || bookDate.after(startDate)) &&
					(bookDate.equals(endDate) || bookDate.before(endDate))) {
					count++;
					System.out.print(entry);					
				}
			}
			
			System.out.println("총 [" +count + "] 건 검색 완료.");
			
		} catch (ParseException e) {
			System.out.println("날짜 변환 중 예외 발생. 데이터를 확인해주세요.");
			System.out.println(e.getLocalizedMessage());
		}
		
	}
	
	public void bookSearchByPublisher() {
		System.out.println("검색하려는 출판사 명을 입력해주세요.");
		String query = scanner.nextLine();
		int count = 0;
		for (Map.Entry<Integer, Book> entry: allBookList.entrySet()) {
			if (entry.getValue().getPublisher().contains(query)) {
				count++;
				
			}
		}
		System.out.println("총 [" + count + "]건 검색됨\n");		
	}

	public void bookSearchByTitle() {
		System.out.println("검색하려는 책 이름을 입력해주세요.");
		String query = scanner.nextLine();
		int count = 0;
		for (Map.Entry<Integer, Book> entry: allBookList.entrySet()) {
			if (entry.getValue().getName().contains(query)) {
				count++;
				System.out.print(entry.toString());
			}
		}
		
		System.out.println("총 [" + count + "]건 검색됨\n");
		
	}

	public void bookSearchByType() {
		System.out.println("검색하고 싶은 타입을 숫자로 입력해주세요. 1. 교육 / 2. 수필 / 3.소설");
		int select2 = Integer.parseInt(scanner.nextLine());
		switch (select2) {
		case 1:
			int count = 0;			
			for (int i=0; i<allBookList.size(); i++) {
				if (allBookList.get(i).type.equals("교육")) {
					count++;
					System.out.print(allBookList.get(i));
				}
			}			
			System.out.println("총 [" + count + "]건 검색됨.");
			break;
			
		case 2:
			int count2 = 0;
			for (int i=0; i<allBookList.size(); i++) {
				if (allBookList.get(i).type.equals("수필")) {
					System.out.println(allBookList.get(i));
					count2++;
				}
			}
			System.out.println("총 [" + count2 + "]건 검색됨.");
			break;
			
		case 3:
			int count3 = 0;
			for (int i=0; i<allBookList.size(); i++) {
				if (allBookList.get(i).type.equals("소설")) {
					count3++;
					System.out.print(allBookList.get(i));	
				}
			}
			System.out.println("총 [" + count3 + "]건 검색됨.");
			break;
			
		default:
			System.out.println("잘못된 입력입니다.");
			break;
		}
	}
	
	public Map<Integer, Book> sortByValue(HashMap<Integer, Book> bookMap){
		List<Map.Entry<Integer, Book>> bookList = new LinkedList<Map.Entry<Integer, Book>>(bookMap.entrySet());
		Collections.sort(bookList, new Comparator<Map.Entry<Integer, Book>>() {
			public int compare(Map.Entry<Integer, Book> o1, Map.Entry<Integer, Book> o2) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date o1Date = null;
				Date o2Date = null;
				try {
					o1Date = sdf.parse(o1.getValue().getReleaseDate());
					o2Date = sdf.parse(o2.getValue().getReleaseDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return (o1Date.compareTo(o2Date));
			}
		});
		
		HashMap<Integer, Book> temp = new LinkedHashMap<Integer, Book>();
			for (Map.Entry<Integer, Book> book : bookList) {
				temp.put(book.getKey(), book.getKey());
		}
		
		return bookMap;
	}
	
}
