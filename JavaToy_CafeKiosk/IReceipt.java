package ch19.posBackup2.JavaToy_CafeKiosk;

import java.util.List;

public interface IReceipt {
	public void printReceipt(List<MenuItem> mi, int select); // 영수증(주문한 메뉴리스트) 출력
}
