package space.jdbc.memo;

import java.util.List;

public interface MemoDao {

    List<Memo> findAll();

    Memo findById(int id);

    int insert(Memo memo);

    int update(Memo memo);

    int deleteById(int id);

}
