package 진욱;

import java.util.List;

public interface ReviewDao {
    public List<Review> allReviewList();

    public int allReviewCount();

    public int insertReview(Review review);

    public int updateReview(Review review);

    public int deleteReview(int review_num);

    public int deleteReviewByClassNumber(int class_num);

    public void deleteReviewByMemberNum(int member_num);

    public Review getReview(int review_num);

    public List<Review> searchReview(int method, String query);

    public void displayTrainerReviewScore(int select);
}
