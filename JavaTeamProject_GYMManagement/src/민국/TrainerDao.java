package 민국;

import java.util.List;

public interface TrainerDao {
    boolean insert(Trainer trainer);
    List<Trainer> findAll();
    Trainer findByIndex(int trainerNum);
    boolean findById(String login_id);
    boolean update(Trainer trainer);
    boolean deleteById(int id);
    boolean tryLogin(String login_id, String login_pw);
}
