package 진욱;

import Gym.Logic.Logic.DAOManager;

public class ReviewTest {
    public static void main(String[] args) {

        ReviewDao reviewDao = DAOManager.getInstance().getrDao();

        System.out.println("============ 전체출력 =============");
        for (Review r: reviewDao.allReviewList()){
            System.out.println(r);
        }

        System.out.println("============ 특정글쓴이 검색 출력(ex: '홍' 검색) =============");
        for (Review r2: reviewDao.searchReview(1, "홍")){
            System.out.println(r2);
        }

        System.out.println("============ 데이터 삽입 테스트 ===============");
        Review review = new Review(5, "최고의 수업입니다.", "너무 많은걸 배웠습니다 ^^", 1);
        System.out.println(reviewDao.insertReview(review) + "행 삽입 완료되었습니다.");


        System.out.println("============ 데이터 수정 테스트 ==============");
        Review review2 = new Review(4, "두번째로 좋은 수업입니다.", "나름 배웠습니다", 3);
        System.out.println(reviewDao.updateReview(review2) + "행이 수정되었습니다.");

        //System.out.println("============ 데이터 삭제 테스트 ==============");
        //System.out.println(reviewDao.deleteReview(4) + "행이 삭제되었습니다.");

        System.out.println("특정 트레이너의 평가인원과 평균평점을 확인");
        reviewDao.displayTrainerReviewScore(1);
    }
}
