package api;

public class JsonPost {
    private int userId;
    private int id;
    private String title;
    private String body;

    public JsonPost(int userId, int id, String title, String body) {
        this.userId =userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return this.userId;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getBody() {
        return this.body;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
