package 진욱;

import Gym.Logic.Common.Input;
import Gym.Logic.Logic.DAOManager;
import Gym.Logic.Logic.ShowManager;
import 민국.Trainer;
import 민국.TrainerDao;
import 호영.Gym_Member;
import 호영.Gym_MemberDao;

public class Gym_LessonMain {
    Gym_LessonDao glDao = DAOManager.getInstance().getlDao();
    Gym_MemberDao gmDao = DAOManager.getInstance().getmDao();
    TrainerDao tDao = DAOManager.getInstance().gettDao();
    ReviewDao rDao = DAOManager.getInstance().getrDao();

    public void lessonExecute(){

        System.out.println("수업 메뉴에서 원하는 기능을 선택해주세요.");

        boolean loop = true;
        while (loop){
            ShowManager.getInstance().showMemberLessonMenu();
            int select = Input.intScan();
            switch (select){
                case 1:

                    System.out.println("전체 수업 내용을 출력합니다.");

                    for (Gym_Lesson gl : glDao.findAll()){
                        System.out.println(gl);
                    }
                    break;

                case 2:
                    findTrainerLesson();
                    break;
                case 3:
                    modifyLessonInfo();
                    break;
                case 4:
                    deleteLesson();
                    break;
                case 9:

                    System.out.println("초기 화면으로 돌아갑니다.");

                    loop = false;
                    break;
                default:

                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");

                    break;
            }
        }
    }

    public void findTrainerLesson() {
        System.out.println("수강정보 검색을 원하는 트레이너명을 입력하세요.");
        System.out.println("==============트레이너 목록==============");
        for (Trainer t: tDao.findAll()){
            System.out.println(t);
        }
        int number = Input.intScan();
        System.out.println(glDao.findByTrainer(number).get(0).getTrainer().getName() + "트레이너에 대한 수강 정보는 아래와 같습니다.");
        for (Gym_Lesson gl2 : glDao.findByTrainer(number)){
            System.out.println(gl2);
        }
    }

    public void modifyLessonInfo() {
        for (Gym_Lesson gl3: glDao.findAll()){
            System.out.println(gl3);
        }

        System.out.println("수정을 원하는 수강정보의 번호를 입력해주세요.");
        int number = Input.intScan();

        System.out.println("선택하신 수강정보는 아래와 같습니다.");

        System.out.println((glDao.getALesson(number) == null? "수강 정보가 없습니다!" : glDao.getALesson(number)));

        System.out.println("수정할 수강 과목명을 입력해주세요. ex) 다이어트 집중 수업");
        String class_detail = Input.stringScan();

        for (Trainer t : tDao.findAll()){ // 번호 입력 전 트레이너 전체 목록을 출력함.
            System.out.println(t);
        }

        System.out.println("수정할 담당 트레이너가 누구인지 번호를 입력해주세요.");
        int trainer_num = Input.intScan();

        Trainer modifyTrainer = tDao.findByIndex(trainer_num);
        for (Gym_Member m : gmDao.findAll()){
            System.out.println(m);
        }

        System.out.println("어떤 회원으로 수정할 지 번호를 입력해주세요. (회원 목록 출력)");
        int member_num = Input.intScan();

        Gym_Member modifyMember = gmDao.findByMember_Num(member_num);
        int result = glDao.updateLesson(new Gym_Lesson(number, class_detail, modifyTrainer, modifyMember));

        System.out.println(result + "건 변경이 완료되었습니다.");
    }

    public void deleteLesson(){
        for (Gym_Lesson gl3: glDao.findAll()){
            System.out.println(gl3);
        }

        System.out.println("삭제하고자 하는 강의의 번호를 입력해주세요. (삭제 시 관련 리뷰도 함께 삭제됩니다.");
        int number = Input.intScan();

        int reviewDeleteResult = rDao.deleteReviewByClassNumber(number);
        int classDeleteResult = glDao.deleteLesson(number);

        if (reviewDeleteResult != 0 && classDeleteResult != 0) {
            System.out.println("해당 강의 정보 및 그 리뷰가 삭제되었습니다.");
        }
    }
}
