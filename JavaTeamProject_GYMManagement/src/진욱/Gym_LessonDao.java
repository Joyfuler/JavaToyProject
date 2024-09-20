package 진욱;

import java.util.List;

public interface Gym_LessonDao {
    public List<Gym_Lesson> findAll();
    public List<Gym_Lesson> findByTrainer(int number); // 특정 트레이너의 수업 리스트를 리턴함.
    public List<Gym_Lesson> findByMember_loginId(String login_id);
    public int insertLesson(Gym_Lesson classList);
    public int updateLesson (Gym_Lesson classList);
    public int deleteLesson (int class_num);
    public void deleteLessonByMemberNum(int member_num);
    public Gym_Lesson getALesson (int class_num); // 인덱스를 통해 특정 한 수업만 리턴함.
}
