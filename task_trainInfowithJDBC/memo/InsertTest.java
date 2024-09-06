package space.jdbc.memo;

import java.util.Scanner;

public class InsertTest {
    public static void main(String[] args) {
        MemoDao memodao = new JdbcMemoDao();
        Memo insertMemo = new Memo();
        Scanner scanner = new Scanner(System.in);
        System.out.println("데이터베이스에 들어갈 내용을 입력해주세요.");
        String answer = scanner.nextLine();
        insertMemo.setContent(answer);
        int result = memodao.insert(insertMemo);
        System.out.println("메모 데이터 입력 완료.");
        System.out.println("몇줄 들어갔나요? :" + result + "줄");
    }
}
