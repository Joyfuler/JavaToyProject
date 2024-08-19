package ch19.posBackup2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order implements IKiosk {	
	public static boolean takeOut = false; // 첫 주문시 선택할 수 있는 포장 / 매장 식사 여부.
	public static int orderNumber = 1; // 주문번호표. 두 변수는 다른 클래스에서도 객체 생성 없이 사용하도록 static화.	

	MenuGenerator mg = new MenuGenerator();
	List<MenuItem> allMenuList = mg.getMenuList(); // 카페 내 제공되는 모든 메뉴 리스트 객체.
	Scanner sc = new Scanner(System.in);
	
	// 이하 다른 인터페이스 & 클래스에서 선언된 메소드를 사용하기 위한 객체 생성
	OrderDisplay orderDisplay = new OrderDisplay(); // 전체메뉴 / 선택메뉴 등 출력 역할 담당 클래스
	OrderRegister orderRegister = new OrderRegister(); // 등록 / 메뉴 등록(배열 추가) 역할 담당
	OrderReceipt orderReceipt = new OrderReceipt();	// 영수증 / 총 주문 메뉴와 결제액 / 결제방식 등을 출력함. 
	OrderCancel orderCancel = new OrderCancel();
	
	@Override
	public void execute() {
		System.out.println("손님 어서오세요!");		
		System.out.print("1. 매장에서 식사 / 2. 포장 주문 : ");
		takeOut = (Integer.parseInt(sc.nextLine()) == 1? false : true);
		if (!takeOut) {
			System.out.println("매장 식사 주문을 선택하셨습니다.");
		} else {
			System.out.println("포장 주문을 선택하셨습니다.");
		}
		
		orderDisplay.displayMenuList(allMenuList);				
		List<MenuItem> registeredList = orderRegister.register(new ArrayList<MenuItem>());
		orderDisplay.displayClientOrderList(registeredList);	 
		while (true) {			
			System.out.println("1. 물품 더 추가하기 2. 특정 메뉴 취소하기 3. 전체 취소하기 4. 결제");
			int select = Integer.parseInt(sc.nextLine());			
			switch (select) {			
				case 1: //주문추가 선택
					System.out.println("주문 목록에 추가를 선택하셨습니다.");	
					orderDisplay.displayMenuList(allMenuList);
					registeredList.addAll(orderRegister.register(registeredList));
					orderDisplay.displayClientOrderList(registeredList);
					int sum = getTotal(registeredList); 
					System.out.println("총 결제 가격 : " + sum + "원");					
					break;
				case 2:	// 특정 메뉴 주문 취소 선택				
					System.out.println("주문 리스트 중 취소할 메뉴를 선택해주세요.");
					registeredList.remove(orderCancel.cancelRegister(registeredList));
					break;
				case 3: // 전체 취소 선택
					System.out.println("주문을 취소하고 초기 화면으로 돌아갑니다.");
					registeredList = new ArrayList<MenuItem>();
					execute();
					break;
				case 4: // 결제 선택
					System.out.println("결제 화면으로 이동합니다.");
					sum = getTotal(registeredList);
					payMethod(registeredList, sum);	
					registeredList = new ArrayList<MenuItem>();
					execute();
					break;	
				default:
					System.out.println("잘못된 입력입니다.");
					registeredList = new ArrayList<MenuItem>();
					execute();
					break;
				}		
			}	
		}	

	public static int getTotal(List<MenuItem> mi) {
		int sum = 
		mi.stream().mapToInt(x -> x.getPrice() * x.getNumber()).sum(); // 모든 가격 * 수량의 합계액		
		return sum;
	}	
	
	@Override
	public void payMethod(List<MenuItem> mi, int sum) {
		orderDisplay.displayClientOrderList(mi);		
		System.out.println("총 결제액은 " + sum + "원 입니다.");
		System.out.println("결제 방식을 선택해주세요. 1. 신용카드결제 2. 현금결제");
		int select = Integer.parseInt(sc.nextLine());
		switch (select) {
			case 1:
				System.out.println("신용카드를 삽입해주세요.");
				boolean cardInserted = false;
				String input = sc.nextLine();
				if (!input.isEmpty()) {
					cardInserted = true;
				}
				if (cardInserted) {
					System.out.println("결제 중입니다....");
					try {
						Thread.sleep(3000);						
					} catch (InterruptedException e) {						
						System.out.println(e.getMessage());
					} // 3초간 대기한 후 이동					
					System.out.println("결제가 완료되었습니다.");	
					orderReceipt.printReceipt(mi, select);
				}	
				execute();
				break;
			case 2:
				System.out.println("현금 결제를 선택하셨습니다. 현금을 투입구에 넣어주세요");
				int totalInput = 0;
				int credit = sum;
				while (true) {										
					int inputMoney = Integer.parseInt(sc.nextLine());
					if (inputMoney <100 || inputMoney % 100 !=0) {
						System.out.println("현금은 100원 단위 이상으로 투입해주세요.");
						continue;
					}					
					totalInput += inputMoney;
					if (totalInput < credit) {	
						System.out.println("현재까지 투입한 금액 :" + totalInput);
						System.out.println("현재 남은 금액 : " + (credit - totalInput));						
						continue;
					} else if (totalInput == credit) {						
						System.out.println("결제가 완료되었습니다.");
						orderReceipt.printReceipt(mi, select);
						break;
					} else if (totalInput > credit){
						System.out.println("거스름돈 " + (totalInput - credit) + "을 받아주세요.");
						System.out.println("결제가 완료되었습니다.");
						orderReceipt.printReceipt(mi, select);
						Order.orderNumber++;
						break;
					}	
					break;
				}
				
				default:
					System.out.println("잘못된 입력입니다.");
					break;
			}			
		}	
}
