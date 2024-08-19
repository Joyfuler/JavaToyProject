package ch19.posBackup2;

import java.util.Objects;

public class MenuItem {
	private int itemNumber; 
	// public 이유는 : 다른 클래스에서 자유롭게 사용할 수 있게 위해 
	// itemNumber 해당 아이템의 갯수라는 의미를 코드 작성시 쉽게 이해하기 위해서. 
	// int로 설정한 이유는: 고객한테 scanner 값을 숫자로 쉽게 입력하게 하고, 
	// 그 결과로 메뉴 내용을 출력하기 위한 용도로 작성하였습니다.	
	private String name;
	private int price;	
	private int number;	
	private String type;
//	private boolean isBest;
//	private boolean isTakeOut;		
				
//	public MenuItem(int itemNumber, String name, int price) {	
//		this.itemNumber = itemNumber;
//		this.name = name;
//		this.price = price;
//	}
//	
	public MenuItem(String type, int itemNumber, String name, int price) {
		this.type = type;
		this.itemNumber = itemNumber;
		this.name = name;
		this.price = price;
	}
	
		
	public MenuItem(int itemNumber, String name, int price, int number, String type) {
		super();
		this.itemNumber = itemNumber;
		this.name = name;
		this.price = price;
		this.number = number;
		this.type = type;
	}
	public MenuItem(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public MenuItem(String name, int number, int price) {
		this.name = name;
		this.number = number;
		this.price = price;
	}				
	
	public int getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "타입 = " + type + " | 물품명=" + name + ", 가격=" + price + "원, 수량="+ number + "개";

	}	
	
}
