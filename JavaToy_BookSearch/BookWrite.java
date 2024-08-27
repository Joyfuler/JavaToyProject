package ch19.posBackup2.JavaToy_BookSearch;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class BookWrite {
	
	private static BookWrite instance = null;
	private BookWrite() {}
	
	public static BookWrite getInstance() {
		if (instance == null) {
			instance = new BookWrite();
		}
		
		return instance;	
	}
	
	public void removeAndUpdateToFile(Map<Integer, Book> bookMap) {
		String path = "C:\\Users\\asiae\\eclipse-workspace\\com.lecture.practice\\src\\ch19\\posBackup2\\JavaToy_BookSearch\\Book.txt";
		try {
			
			
			OutputStream outputStream = new FileOutputStream(path);
			for (Integer key: bookMap.keySet()) {
				Book toRegisterBook = bookMap.get(key); // bookMap의 모든 Map의 값들을 반복해서 불러온다.
				String text = toRegisterBook.getType() + ";" +
				toRegisterBook.getName() + ";" +
				toRegisterBook.getPublisher() + ";" +
				toRegisterBook.getReleaseDate() + ";" + 
				toRegisterBook.getPrice() + ";\n";
				outputStream.write(text.getBytes()); // 위 text 내용을 추가.
			}
			
			
			outputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}	
	
	
	
	public void registerToFile(Map<Integer, Book> bookMap) {
		String path = "C:\\Users\\asiae\\eclipse-workspace\\com.lecture.practice\\src\\ch19\\posBackup2\\JavaToy_BookSearch\\Book.txt";
		try {			
			OutputStream outputStream = new FileOutputStream(path, true);
			// false인 경우에는 매번 새로 작성됨. true로 하는 경우에는 기존 내용에 새로운 내용이 추가됨.
			for (Integer key: bookMap.keySet()) {
				Book toRegisterBook = bookMap.get(key); // bookMap의 모든 Map의 값들을 반복해서 불러온다.
				String text = toRegisterBook.getType() + ";" +
				toRegisterBook.getName() + ";" +
				toRegisterBook.getPublisher() + ";" +
				toRegisterBook.getReleaseDate() + ";" + 
				toRegisterBook.getPrice() + ";\n";
				outputStream.write(text.getBytes()); // 위 text 내용을 추가.
				System.out.println(bookMap.get(key).getName() + "책 등록 완료.");
			}
			
			outputStream.close();
					
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
		System.out.println("정상적으로 완료되었습니다.");
	}
}
