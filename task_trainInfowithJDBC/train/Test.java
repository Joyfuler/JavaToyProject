package space.jdbc.train;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        TrainDao trainDao = new TrainDaoImplements();
        List<Train> allTrainList = trainDao.getAllTrain();
        System.out.println("1. AllList Display Test");
        for (Train t: allTrainList){
            System.out.println(t);
        }

        System.out.println("==========================");
        System.out.println("2. Insert Test");
        int result = trainDao.insertTrain(new Train(3333, 15, "D", "Y", new Line(4)));
        System.out.println(result + " 행이 삽입되었습니다.");

        System.out.println("==========================");
        System.out.println("3. Update Test");
        int result2 = trainDao.updateTrain(new Train(3333, 20, "E", "N", new Line(4)));
        System.out.println(result2 + " 행이 업데이트 되었습니다.");

        System.out.println("==========================");
        System.out.println("4. Delete Test");
        int result3 = trainDao.deleteTrain(3333);
        System.out.println("3333번 데이터 삭제 완료");

        System.out.println("==========================");
        System.out.println("5. Driving Info Display Test");
        List<Train> trainInfo = trainDao.getDrivingInfo(2);
        System.out.println("2호선 운행 정보 출력");
        System.out.println(trainInfo);

        System.out.println("==========================");
        System.out.println("6. Search Test");
        System.out.println("2222번 열차 검색 - 정보 출력");
        Train searchTrain = trainDao.findTrain(2222);
        System.out.println(searchTrain);

    }
}
