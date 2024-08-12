# 클래스 구조
![KakaoTalk_20240811_212443424](https://github.com/user-attachments/assets/5c801474-5058-4372-8c48-8c7f43f22b1a)

# ----- 인터페이스 -----
본 프로그램에서 사용하게 될 메소드명을 미리 정의.
본래는 Kiosk 한 개의 인터페이스를 사용하였으나, 이를 구현한 Order 클래스의 책임이 커졌기에 최종적으로는 큰 기능을 다섯 개로 나누고, 인터페이스도 다섯 개로 쪼개개 됨.
이로 인해 Order를 다소나마 경량화할 수 있었던 점은 메리트였다고 생각한다.

Q. 추상클래스를 사용하지 않은 이유?
추상 클래스는 자식 클래스 타입을 대표하는 성질을 가지고, 그 성질을 그대로 상속하는 용도로 쓰인다.
키오스크의 기능은 주문이지만, 주문 = 키오스크가 아니므로 상속하여 사용하지 않음.
인터페이스는 구현받은 클래스의 기능을 정의한 것이므로 추상클래스 대신 인터페이스를 사용하였다.
그 외의 이유로는 상속과는 달리 오버라이드 받은 과정에서 유연하게 변경할 수 있는 점이 장점임.



# ---- MenuItem 관련 클래스들 -----------

MenuItem --> 메뉴판에 추가될 메뉴들을 객체로 생성하기 위해 만든 클래스.
변수로는 해당 메뉴의 인덱스 (1번메뉴, 2번메뉴...) / 메뉴명(name) / 가격 (price) / 수량 (number) 존재.
실수로 해당 값들을 직접 조작하여 변경하지 못하도록 외부 클래스에서 직접 접근하지 못하도록 private 설정. get/set으로 접근

BeverageItem --> MenuItem을 상속. 
음료 메뉴들은 모두 메뉴에 속해 있으므로 상속을 사용. MenuItem에 있는 변수는 재사용하고, 간식 메뉴를 출력하는 toString은 오버라이딩을 통해 재정의했음. (간식메뉴: ~~~)

CoffeeMenuItem --> MenuItem을 상속.
위와 동일. toString시 커피메뉴: 로 출력되도록 오버라이딩.

DesertItem --> MenuItem을 상속.
위와 동일.


# ------- MenuGenerator ------

위의 MenuItem을 상속받는 각 Item들의 객체를 생성하는 역할을 담당한다.
Coffee / Beverage / Desert 3개의 타입의 객체를 각각 2개씩 생성하여, 총 6개의 객체를
MenuItem 타입의 리스트로 담아 이를 호출한 곳에 리턴한다.
호출한 클래스에서는 이 배열을 리턴받아 전체 메뉴판을 출력할 수 있게 된다.


# --------- KioskMain ------------
Order 객체를 호출하고, execute 메소드를 실행만 하는 역할. 각 클래스의 역할이 너무 많지 않도록,
main은 단순히 실행만 하는 역할을 부여하였음. 또 main의 코드가 너무 길면 그만큼 main클래스가
길어지고 코드를 수정하거나 기능을 확인하기 어렵기에(가독성) Order에 기능 구현 부분을 분산하였음.
또 새로운 기능을 추가할 때 클래스를 추가해 붙이는 방법을 사용할 수 있으므로 확장성이 오른다.

# ------------------- Order -------
키오스크에서 일어나는 과정을 기능으로 정리한 클래스. main의 기능을 분산하기 위해 추가한 클래스다.
Kiosk에서 정의한 기능을 사용하기 위해 메소드를 전부 오버라이딩하여 구현하였음.

각 변수 소개 - 처음 실행하면 포장 / 매장내 식사 여부를 스캐너를 통해 사용자에게 입력받는다.
만일 포장인 경우에는 takeOut이 true가 되고, 매장 내 식사인 경우 takeOut은 false가 된다.
영수증을 출력할 때 이 takeOut의 값에 따라 포장 / 식사가 다르게 출력된다.

orderNumber는 영수증에 출력될 실제 주문번호로, 실제 가게에서 음식이 나왔을 때 해당 번호를 호출하는
상황을 가정하고 지은 변수.
static인 이유는 order 기능을 나눈 SaleOrder나 CancelOrder에서도 객체를 생성하지 않고, 변수를 공유해서
사용하고 싶기 때문이다. 결제를 완료한 후 orderNumber는 자동으로 +1씩 되며 어느 클래스에서 사용하든
변화된 값을 공유해 사용할 수 있다.


# ---- 프로그램 흐름-----------------

매장과 식사를 구분하여 사용자에게 입력받게 한다.
이후에는 displayMenuList 메소드를 호출해 모든 메뉴판이 출력되게 한다.

손님의 주문 목록을 담을 배열(registeredList)을 우선 new로 초기화하고, 이를 매개변수로 넣어 register 메소드를 호출.
앞서 출력된 메뉴에서 고르고 싶은 메뉴의 번호와 그 수량을 입력받고, 이를 매개변수로 받은 주문 목록에
추가한 후 리턴한다. 첫 주문 시에는비어 있는 registeredList에 직접 리턴받은 배열을 대입하고,
물품을 추가한 이후에는 switch 문을 이용하여 추가주문 / 일부 물품 주문 취소 / 전체 취소 / 결제
중 어떤 기능을 이용할 지 사용자에게 입력받는다. 
추가 주문 / 특정 메뉴 삭제는 여러 번 반복할 수 있으므로 while문 안에서 반복하게 하고,
전체삭제나 결제 화면은 함수가 종료되면 초기 화면으로 돌아가도록 배열을 초기화 (new ArrayList)하고
execute를 처음부터 호출한다.

(case 1) 물품 추가
추가 주문 시에는 배열 뒤에 addAll을 사용하여 기존 registeredList 뒤에 주문 데이터를 추가한다.

(case 2) 특정메뉴 취소
특정 상품을 취소하고 싶은 경우, 주문목록에 있는 번호 (배열의 인덱스) 를 우선 확인하고,
사용자에게 인덱스 번호를 입력받은 후 인덱스에 해당하는 배열의 내용을 삭제한다.

(case 3) 전체취소
registeredList를 new로 초기화하고 execute를 재실행한다.

(case 4) 결제
결제에는 현금 결제와 카드 결제 중 어떤 것을 선택할 지 사용자에게 입력받는다.

1) 카드 결제는 임의의 값을 입력받으면 카드를 삽입한 것으로 간주하고 결제 중입니다... 메시지 표시 후
3초가 지난 뒤 '결제가 완료되었습니다'가 출력된다.
(실제 결제를 진행하는 메소드는 아니며, 단순히 첫 메시지 출력 후 3초 뒤에 완료 메시지가 출력되는 것)
완료 후 영수증을 출력하는 메소드를 호출한다. 
출력 시에는 자신의 주문번호와 포장 / 매장 내 식사 여부 / 주문한 물품들의 이름과 수량 / 총 가격이 표시된다.

2) 현금 결제는 결제해야 할 액수를 우선 표시하고, 사용자가 숫자를 입력할 때마다
결제해야 하는 액수가 입력한 액수만큼 감소한다.
조건문을 통해 100원 이상, 100원 단위로만 숫자를 입력할 수 있도록 하고,
반복 입력한 액수의 합이 결제해야 할 액수와 같으면 결제가 완료되고 영수증을 출력한다.
반복 입력한 액수의 합이 결제액보다 크다면 그 차액을 '거스름돈 ~원이 나옵니다' 로 출력한다.



#--------------------- 기타 메소드 설명 -----------------------
displayClientOrderList -->
물품의 주문 추가가 끝날 때마다 registeredList 배열 내 객체들의 정보를 출력한다.
이 메소드에서는 stream을 사용. 기본형인 경우에는 stream보다 for문이 빠르지만,
배열 순회에서의 속도는 비슷하기 때문. 람다식을 사용해 가독성을 높이고, 코드 수를 줄이고 싶었음

displayMenuList --> 물품 더 추가하기(switch case 1 선택시) 모든 메뉴판을 출력하는 용도의 함수

getTotal --> registeredList 배열 내 Item의 가격과 수량을 곱한 값을 모두 합친 값을 반환하는 sum 변수로 반환하는 메소드.
sum 변수는 주문을 새로 추가할 때마다 현재 가격을 표시하고, 결제 시에 결제해야 하는 액수를 표시하는
용도로 사용한다. 역시 코드를 간결하게 표시하기 위해 for문 대신 stream을 사용하였음.
(만일 데이터가 많고 속도를 늘리고 싶다면 stream 대신 parallel을 통해 처리속도를 올릴 수 있다)

printReceipt --> payMethod 메소드에서 결제가 완료되었을 때 영수증 메시지를 출력하는 용도로 사용.
영수증에는 registered 객체 정보와 총액을 출력한다.

register --> registeredList에 추가할 addedList를 따로 선언한다.
모든 주문목록을 담은 allMenuList를 우선 출력하고, 사용자가 입력한 번호와 메뉴번호를 비교하여
같은 것이 배열에 추가된다.

주문목록이 추가된 배열은 다시 registeredList에 addAll 메소드를 이용해 배열 뒤에 내용을 추가한다.

cancelRegister --> 첫 switch 문에서 3번을 골랐을 겨우 실행되는 주문취소 기능.
Order가 아닌 자식 클래스 cancelOrder에서실행되는데, Order가 가진 책임이 너무 많아
이를 분산하기 위해 따로 선언하였음. 향후 주문 취소와 관련된 메소드가 더 추가될 경우 CancelOrder
클래스에 기능을 추가하여 카테고리에 따라 기능을 찾기 쉬워지는 장점이 있음.
payMethod --> 첫 switch 문에서 4번을 골랐을 경우 실행되는 결제 기능.
자식 클래스의 객체를 만들어 실행한 이유는 cancelRegister와 동일.

객체를 추가하지 않고 기존 객체 수량 변경 - 객체 수량을 변경하는 과정에서 pararelInput 에러 발생..
 --> 일단은 객체는 무조건 생성하는 방식으로 수정하였음. 향후 고치고 싶은 기능입니다.

