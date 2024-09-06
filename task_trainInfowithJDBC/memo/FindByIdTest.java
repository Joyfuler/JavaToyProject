package space.jdbc.memo;

import java.util.List;
import java.util.Scanner;

public class FindByIdTest {
    public static void main(String[] args) {
        MemoDao memoDao = new JdbcMemoDao();
        Scanner scanner = new Scanner(System.in);
        System.out.println("찾고자 하는 메모의 아이디를 입력해주세요.");
        int id = Integer.parseInt(scanner.nextLine());
        Memo findedMemo = memoDao.findById(id);
        System.out.println("검색하신 결과는 다음과 같습니다.");
        System.out.println(findedMemo);


    }
}
