package ch19.posBackup2.JavaToy_CafeKiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderRegister implements IRegister{
		
	Scanner sc = new Scanner(System.in);
	MenuGenerator mg = new MenuGenerator();
	List<MenuItem> allMenuList = mg.getMenuList(); // 카페 내 제공되는 모든 메뉴 리스트.
	
	@Override
	public List<MenuItem> register(List<MenuItem> registeredList) {
		System.out.println("추가할 메뉴의 번호를 입력해주세요.");		
		int select = Integer.parseInt(sc.nextLine().trim());
		ArrayList<MenuItem> addedList = new ArrayList<MenuItem>(); 
		MenuItem selectedItem = null;
		int count = 0;		
		for (MenuItem item : allMenuList) { // 모든 메뉴의 번호에서 입력한 번호와 동일한 주문정보(객체)를 가져온다.
			if (select == item.getItemNumber()) {					
				System.out.println(item.getName() + " 메뉴를 선택하셨습니다. 주문 수량을 입력해주세요.");
				selectedItem = item;				
			}
		}	
		
		count = Integer.parseInt(sc.nextLine()); 		
		addedList.add(new MenuItem(selectedItem.getItemNumber(), selectedItem.getName(), selectedItem.getPrice(), count, selectedItem.getType()));		
		return addedList;
	}	
}
