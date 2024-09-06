package space.jdbc.memo;

import java.util.List;

public class DisplayTest {
    public static void main(String[] args) {
        MemoDao memoDao = new JdbcMemoDao();
        List<Memo> memoList = memoDao.findAll();
        System.out.println("메모다오 출력결과");
        for (Memo m: memoList){
            System.out.println(m);
        }

    }
}
