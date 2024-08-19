package ch19.posBackup2.JavaToy_CafeKiosk;

import java.util.ArrayList;
import java.util.List;

public class MenuGenerator {
	
	public List<MenuItem> getMenuList() {
		// 전체 메뉴판을 출력하기 위해 본 메소드에서 객체배열 생성 후 리턴
		List<MenuItem> menuList = new ArrayList<MenuItem>();		
		MenuItem c1 = new CoffeeItem("커피", 1, "아메리카노", 1000);
		MenuItem c2 = new CoffeeItem("커피", 2, "카페라떼", 3000);
		
		menuList.add(c1);
		menuList.add(c2);
		
		MenuItem b1 = new BeverageItem("음료", 3, "아이스티", 3000);
		MenuItem b2 = new BeverageItem("음료", 4, "얼그레이", 3000);
		
		menuList.add(b1);
		menuList.add(b2);
		
		MenuItem d1 = new DesertItem("간식", 5, "샌드위치", 3500);
		MenuItem d2 = new DesertItem("간식", 6, "초코칩쿠키", 4500);
		
		menuList.add(d1);
		menuList.add(d2);
		
		return menuList;	
	};
}
