package ch19.posBackup2;

public class DesertItem extends MenuItem{	
	
	public String type = "간식";
		
	public DesertItem(String type, int itemNumber, String name, int price) {
		super(type, itemNumber, name, price);
	}	
	
	@Override
	public String toString() {
		return "음료메뉴: ("+ getItemNumber() +") "+ getName() + " / 가격 : " + getPrice();
	}	
	
	
}
