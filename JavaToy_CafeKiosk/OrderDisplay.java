package ch19.posBackup2.JavaToy_CafeKiosk;

import java.util.List;

public class OrderDisplay implements IDisplay {

	@Override
	public void displayMenuList(List<MenuItem> mi) {		
		System.out.println("============메뉴화면===============");		
		mi.stream().forEach(x -> System.out.println(x));		
		System.out.println("=================================");		
	}
	
	@Override
	public void displayClientOrderList(List<MenuItem> mi) { // 회원이 추가한 주문목록을 출력하는 메소드.
		System.out.println("===============현재 주문목록===============");
		mi.stream().forEach(x -> System.out.println(x));
		int sum = mi.stream().mapToInt(x -> x.getNumber() * x.getPrice()).sum();
		System.out.println("총합 : " + sum + "원");
		System.out.println("========================================");		
	}			

}
