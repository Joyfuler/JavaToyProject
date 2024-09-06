package space.jdbc.memo;

public class Memo {
    private long id; // 메모 일련번호 (pk)
    private String content; // 메모 내용

    public Memo() {
        // super() 생략되어 있음.
    }

    public Memo(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Memo [" +
                "id=" + id +
                ", content='" + content + '\'' + "]";
    }
}
