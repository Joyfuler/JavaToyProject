package 진욱;

import Gym.Logic.Logic.DAOManager;
import 민국.Trainer;
import 호영.Gym_Member;

public class Gym_LessonTest {
    public static void main(String[] args) {
        Gym_LessonDao gDao = DAOManager.getInstance().getlDao();

        System.out.println("============ 전체출력 =============");
        for (Gym_Lesson g1 : gDao.findAll()){
            System.out.println(g1);
        }

        System.out.println("==================================");

        System.out.println("====특정 트레이너 수강정보 출력=====");
        for (Gym_Lesson g2 : gDao.findByTrainer(1)){
            System.out.println(g2);
        }
        System.out.println("==================================");

        System.out.println("======== 수강정보 삽입=============");
        Trainer toInsertTrainer = new Trainer();
        toInsertTrainer.setTrainer_num(3);
        Gym_Member toInsertMember = new Gym_Member();
        toInsertMember.setMember_num(4);
        System.out.println(gDao.insertLesson(new Gym_Lesson(11, "하체와상체모두단련하는프로그램", toInsertTrainer, toInsertMember)) + "행 삽입되었습니다.");
        System.out.println("==================================");

        System.out.println("======== 수강정보 수정=============");
        System.out.println(gDao.updateLesson(new Gym_Lesson(11,"사실하체만단련하는프로그램", toInsertTrainer, toInsertMember)) + "행 수정됨.");
        System.out.println("==================================");

        System.out.println("======== 수강정보 삭제=============");
        System.out.println(gDao.deleteLesson(11));
        System.out.println("==================================");

    }
}
