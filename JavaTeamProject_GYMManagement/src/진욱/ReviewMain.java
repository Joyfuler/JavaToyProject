package 진욱;

import Gym.Logic.Common.Input;
import Gym.Logic.Logic.DAOManager;
import Gym.Logic.Logic.LoginManager;
import Gym.Logic.Logic.ShowManager;
import 민국.Trainer;
import 민국.TrainerDao;

import java.util.List;

public class ReviewMain {

    ReviewDao reviewDao = DAOManager.getInstance().getrDao();
    Gym_LessonDao gDao = DAOManager.getInstance().getlDao();
    TrainerDao tDao = DAOManager.getInstance().gettDao();

    public void reviewExecute(){
        System.out.println("리뷰 메뉴에서 원하는 기능을 선택해주세요.");
        boolean loop = true;
        while (loop){
            ShowManager.getInstance().showReviewMenu();
            int select = Input.intScan();
            switch (select){
                case 1:
                    showAllReview();
                    break;

                case 2:
                    writeReview();
                    break;

                case 3:
                    updateReview(1);
                    break;

                case 4:
                    updateReview(2);
                    break;

                case 5:
                    showMyTrainerScore();
                    break;
                case 9:
                    System.out.println("리뷰 관련 메뉴 출력을 종료합니다.");
                    loop = false;
                    break;

                default:
                    System.out.println("잘못 입력하셨습니다. 다시 한번 입력해주세요.");
                    break;
            }
        }
    }

    public void showAllReview(){
        System.out.println("전체 리뷰를 출력합니다.");
        System.out.println("총 리뷰 건수 : " + reviewDao.allReviewCount() + "건");
        System.out.println("=============== 리뷰 목록 ===============");
        for (Review r: reviewDao.allReviewList()){
            System.out.println(r);
        }

        System.out.println("========================================\n");
    }

    public void writeReview(){
        System.out.println("리뷰 작성을 선택하셨습니다. 회원님이 등록하신 수업을 출력합니다.");
        List<Gym_Lesson> lessonList = gDao.findByMember_loginId(LoginManager.getInstance().getCurrentMember().getLogin_id());
        if (lessonList.isEmpty()){
            System.out.println("현재 등록한 수업이 없습니다!");
        } else {
            for (Gym_Lesson g: lessonList){
                System.out.println(g);
            }
            System.out.println("리뷰를 작성할 수업의 번호를 선택해주세요.");
            int number = Input.intScan();
            Gym_Lesson toReviewLesson = gDao.getALesson(number);
            System.out.println("수업 평점을 선택해주세요! (1~5점 사이 정수입력)");
            int score = Input.intScan(1,5);
            System.out.println("리뷰 제목을 입력해주세요.");
            String title = Input.stringScan();
            System.out.println("리뷰 내용을 입력해주세요.");
            String content = Input.stringScan();
            Review toWriteReview = new Review(score, title, content, number);
            reviewDao.insertReview(toWriteReview);
            System.out.println("리뷰 작성이 완료되었습니다!");
        }
    }

    public void updateReview(int method){

        System.out.println("리뷰 수정/삭제를 선택하셨습니다. 회원님이 작성한 리뷰 리스트를 불러옵니다.");
        List<Review> userReviewList = reviewDao.searchReview(4, LoginManager.getInstance().getCurrentMember().getLogin_id());
        if (userReviewList.isEmpty()){
            System.out.println("현재 작성한 리뷰가 없습니다.");
        } else {
            for (Review r : userReviewList) {
                System.out.println(r);
            }
            if (method == 1) { // 1인 경우는 수정, 2인 경우는 삭제함.
                reviewContentModify();
            } else if (method == 2) {
                deleteReview();
            } else {
                System.out.println("잘못된 입력입니다. 초기 화면으로 돌아갑니다.");
            }
        }
    }

    private void reviewContentModify() {
        System.out.println("변경할 리뷰의 번호를 입력해주세요.");
        int number = Input.intScan();
        System.out.println("변경할 평점을 입력해주세요. 현재 평점: " + reviewDao.getReview(number).getScore() + "점");
        int score = Input.intScan();
        System.out.println("수정할 제목을 작성해주세요. 아무것도 입력하지 않으면 기존 제목이 반영됩니다.");
        String title = Input.stringScan();
        if (title.isEmpty())
            title = reviewDao.getReview(number).getTitle();
        System.out.println("수정할 내용을 작성해주세요. 아무것도 입력하지 않으면 기존 내용이 반영됩니다.");
        String content = Input.stringScan();
        if (content.isEmpty())
            content = reviewDao.getReview(number).getContent();
        reviewDao.updateReview(new Review(score, title, content, number));
        System.out.println("수정이 완료되었습니다!!");
    }

    public void deleteReview(){
        System.out.println("삭제할 리뷰의 번호를 입력해주세요.");
        int number = Input.intScan();
        if (reviewDao.deleteReview(number) == 1) {
            System.out.println("리뷰 삭제가 완료되었습니다!");
        } else {
            System.out.println("리뷰 삭제가 실패하였습니다. 다시 시도해 주세요.");
        }
    }

    public void showMyTrainerScore(){
        List<Trainer> trainerList = tDao.findAll();
        for (Trainer t : trainerList){
            System.out.println(t.getTrainer_num() + "번:\t" + t.getName());
        }
        System.out.println("담당 트레이너의 번호를 입력하세요.");
        int select = Input.intScan();
        reviewDao.displayTrainerReviewScore(select);
    }
}
