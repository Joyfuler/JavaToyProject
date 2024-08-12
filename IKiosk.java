package ch19.posBackup2;

import java.util.List;

public interface IKiosk {	
	public void execute(); // 메인 클래스에서 호출하는 용도			
	// public int getTotal(List<MenuItem> mi); // --> 영수증 클래스에서도 사용하기 위해 static으로 변경하였음.	
	public void payMethod(List<MenuItem> mi, int sum); // 현금결제 or 카드결제를 선택 후, 결제진행.
}
