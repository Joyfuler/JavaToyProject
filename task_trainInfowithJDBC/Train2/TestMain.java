package space.jdbc.train;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TrainDao trainDao = new TrainDaoImplements();
        while (true) {
            System.out.println("원하는 기능을 선택해주세요. 1. 전체조회 2. 데이터 입력 3. 데이터 수정 4. 특정 열차조회 5. 데이터 삭제 6. 운행정보출력 7.프로그램 종료");
            String answer = sc.nextLine();
            switch (answer) {
                case "1":
                    List<Train> allTrainList = trainDao.getAllTrain();
                    System.out.println("전체 리스트를 출력합니다.");
                    System.out.println("번호\t 열차수\t 운행자 \t 급행여부 \t 호선");
                    for (Train t : allTrainList) {
                        System.out.println(t.getTrain_no() + "\t\t" + t.getTrain_count() + "\t\t" + t.getDriver() + "\t\t\t" + (t.getIsExpress().equals("Y")? "급행":"안급행") + "\t\t" + t.getLine().getId() + "호선");
                    }

                    break;

                case "2":
                    System.out.println("데이터 입력을 선택하셨습니다. 총 4단계로 이루어져 있습니다.");
                    System.out.println("1단계 - 기차 번호 (ex: 8호선의 경우 8001로 입력)");
                    String train_no = sc.nextLine();
                    Train findedTrain = trainDao.findTrain(Integer.parseInt(train_no));
                    if (findedTrain!= null){
                        System.out.println("해당 기차번호는 이미 등록되어 있습니다. 입력을 취소합니다.");
                        break;
                    }
                    System.out.println("2단계 - 차량수 (ex: 일반 기차는 보통 10호차에서 11호차로 구성)");
                    String train_count = sc.nextLine();
                    System.out.println("3단계 - 운전수의 이름을 입력해주세요.");
                    String driver = sc.nextLine();
                    System.out.println("4단계 - 몇 호선 차량인지 입력해주세요. (1 ~ 8)");
                    String id = sc.nextLine();
                    Train insertTrain = new Train(Integer.parseInt(train_no), Integer.parseInt(train_count), driver, new Line(Integer.parseInt(id)));
                    int result = trainDao.insertTrain(insertTrain);
                    System.out.println(result + "행 입력 완료되었습니다.");
                    break;

                case "3":
                    System.out.println("데이터 수정을 선택했습니다.");
                    System.out.println("확인을 위해 전체 리스트를 출력합니다.");
                    allTrainList = trainDao.getAllTrain();
                    System.out.println("번호\t 열차수\t 운전자 \t 급행여부 \t 호선");
                    for (Train t : allTrainList) {
                        System.out.println(t.getTrain_no() + "\t\t" + t.getTrain_count() + "\t\t" + t.getDriver() + "\t\t\t" + (t.getIsExpress().equals("Y")? "급행":"안급행") + "\t\t" + t.getLine().getId() + "호선");
                    }

                    System.out.println("수정하고 싶은 번호를 입력해주세요. (총 4단계)");
                    int number = Integer.parseInt(sc.nextLine());
                    Train t1 = trainDao.findTrain(number);
                    System.out.println("원본 데이터는 아래와 같습니다.");
                    System.out.println("번호\t 열차수\t 운행자 \t 운행여부 \t 호선");
                    System.out.println(t1.getTrain_no() + "\t\t" + t1.getTrain_count() + "\t" + t1.getDriver() + "\t\t\t" + t1.getIsExpress() + "\t\t" + t1.getLine().getId() + "호선");
                    System.out.println("1단계 - 차량수 (ex: 일반 기차는 보통 10호차에서 11호차로 구성)");
                    train_count = sc.nextLine();
                    System.out.println("2단계 - 운전수의 이름을 입력해주세요.");
                    driver = sc.nextLine();
                    System.out.println("3단계 - 몇 호선 차량인지 입력해주세요. (1 ~ 8)");
                    id = sc.nextLine();
                    System.out.println("마지막 단계 - 급행 여부를 입력해주세요. 1을 입력하면 급행, 2를 입력하면 안급행");
                    String isExpress = sc.nextLine();
                    if (isExpress.equals("1")){
                        isExpress = "Y";
                    } else {
                        isExpress = "N";
                    }
                    result = trainDao.updateTrain(new Train(number, train_count, driver, isExpress, new Line(id)));
                    System.out.println(result + "행만큼 수정이 완료되었습니다.");
                    break;

                case "4":
                    System.out.println("특정 번호의 열차 정보를 조회합니다.");
                    System.out.println("확인하고 싶은 열차의 번호를 입력해주세요.");
                    String input = sc.nextLine();
                    Train t2 = trainDao.findTrain(Integer.parseInt(input));
                    System.out.println("검색 결과 출력");
                    System.out.println("번호\t 열차수\t 운행자 \t 급행여부 \t 호선");
                    System.out.println(t2.getTrain_no() + "\t\t" + t2.getTrain_count() + "\t" + t2.getDriver() + "\t\t\t" + t2.getIsExpress() + "\t\t" + t2.getLine().getId() + "호선");
                    break;

                case "5":
                    System.out.println("특정 열차의 데이터를 삭제합니다.");
                    System.out.println("삭제하고 싶은 열차의 번호를 입력해주세요.");
                    int input2 = Integer.parseInt(sc.nextLine());
                    Train t3 = trainDao.findTrain(input2);
                    System.out.println("검색 결과 출력");
                    System.out.println("번호\t 열차수\t 운행자 \t 급행여부 \t 호선");
                    if (t3 == null){
                        System.out.println("검색 결과가 없습니다.");
                    } else {
                        System.out.println(t3.getTrain_no() + "\t\t" + t3.getTrain_count() + "\t" + t3.getDriver() + "\t\t\t" + (t3.getIsExpress().equals("Y") ? "급행" : "안급행") + "\t\t" + t3.getLine().getId() + "호선");
                        System.out.println("정말 삭제하시겠습니까? 삭제하려면 1, 취소하시려면 2를 눌러주세요.");
                        int input3 = Integer.parseInt(sc.nextLine());
                        if (input3 == 1) {
                            result = trainDao.deleteTrain(input2);
                            System.out.println(result + "행 삭제되었습니다.");
                        } else {
                            System.out.println("초기 화면으로 돌아갑니다.");
                        }
                    }
                        break;

                case "6":
                    System.out.println("1호선 운행 정보를 출력하겠습니다.");
                    List<Train> drivingList = trainDao.getDrivingInfo(1);
                    System.out.println("열차번호 \t 운행여부");
                    for (Train t: drivingList){
                        System.out.println(t.getTrain_no() + "\t\t" +  (t.getLine().getIsDrive().equals("Y")? "운행중": "안운행중"));
                    }
                    break;
                case "7":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(1);
                    break;
            }
        }
    }
}
