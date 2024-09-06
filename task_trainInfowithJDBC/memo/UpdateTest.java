package space.jdbc.memo;

import java.util.List;
import java.util.Scanner;

public class UpdateTest {
    public static void main(String[] args) {
        MemoDao memoDao = new JdbcMemoDao();
        Scanner scanner = new Scanner(System.in);
        List<Memo> displayMemoList = memoDao.findAll();
        System.out.println("목록 출력");
        for (Memo m: displayMemoList){
            System.out.println(m);
        }
        System.out.println("바꿀 데이터의 id를 입력해주세요.");
        String index = scanner.nextLine();
        int indexNum = Integer.parseInt(index);
        System.out.println("바꿀 내용을 입력해주세요.");
        String content = scanner.nextLine();
        Memo updateMemo = new Memo(indexNum, content);
        int result = memoDao.update(updateMemo);
    }
}
