package ch19.posBackup2;

import java.util.List;
import java.util.Scanner;

public class OrderCancel implements ICancel{
	
	Scanner sc = new Scanner(System.in);
	
	@Override
	public int cancelRegister(List<MenuItem> mi) {
		System.out.println("현재 주문목록입니다. 취소할 번호를 선택해주세요.");
		for (int i=0; i< mi.size(); i++) {
			System.out.println((i + 1) + "번 선택:" + mi.get(i));			
		}
		int select = Integer.parseInt(sc.nextLine()) -1;
		return select;	
	}
}
