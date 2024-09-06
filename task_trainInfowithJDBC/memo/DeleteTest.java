package space.jdbc.memo;

import java.util.List;
import java.util.Scanner;

public class DeleteTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MemoDao memoDao = new JdbcMemoDao();
        List<Memo> allMemoList =  memoDao.findAll();
        System.out.println("총 메모 목록");
        for (Memo m: allMemoList){
            System.out.println(m);
        }
        System.out.println("삭제할 인덱스를 선택해주세요.");
        String answer = scan.nextLine();
        int result = memoDao.deleteById(Integer.parseInt(answer));
        System.out.println(result + "행이 삭제되었습니다.");
    }
}
