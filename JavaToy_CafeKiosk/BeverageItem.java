package ch19.posBackup2.JavaToy_CafeKiosk;

public class BeverageItem extends MenuItem {	
	
	public String type = "음료";

	public BeverageItem(String type, int itemNumber, String name, int price) {
		super(type, itemNumber,name, price);
	}	
	
	@Override
	public String toString() {
		return "음료메뉴: ("+ getItemNumber() +") " + getName() + " / 가격 : " + getPrice();
	}	
	
}
