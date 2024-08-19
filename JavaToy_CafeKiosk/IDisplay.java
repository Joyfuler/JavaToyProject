package ch19.posBackup2;

import java.util.List;

public interface IDisplay {
	public void displayMenuList(List<MenuItem> allMenuItem); // 메뉴판 전체를 출력
	public void displayClientOrderList(List<MenuItem> mi); // 손님이 추가한 리스트를 표시하는 용도
}
