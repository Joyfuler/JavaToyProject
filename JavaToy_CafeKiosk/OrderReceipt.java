package ch19.posBackup2.JavaToy_CafeKiosk;

import java.util.List;

public class OrderReceipt implements IReceipt{
	@Override
	public void printReceipt(List<MenuItem> mi, int select) {		
		System.out.println("======================영수증======================");
		System.out.println("주문번호:" + Order.orderNumber);
		System.out.println("주문유형: " + (Order.takeOut == true? "포장주문": "매장식사"));
		System.out.println("물품명\t\t 수량\t\t 가격");		
		for (MenuItem item: mi) {
			System.out.println(item.getName() + "\t\t " + item.getNumber() + "\t\t " + item.getPrice());
		
		}
		System.out.println("총액 : " + Order.getTotal(mi) + "원");
		System.out.println("결제방식 : " + (select == 1 ? "카드결제" : "현금결제"));
		System.out.println("======== 이용해 주셔서 감사합니다. 또 들러주세요!========");
		System.out.println();
	}
	
}
