package my.qq.com.czw3dmgame.uitls;

/**
 * Created by czw on 2016/7/5  19:28.
 */
public class News {
    private String id;
    private String title;
    private String shorttitle;
    private String litpic;
    private String senddate;
    private String feedback;

    public News(String id, String title, String shorttitle, String litpic, String senddate, String feedback) {
        this.id = id;
        this.title = title;
        this.shorttitle = shorttitle;
        this.litpic = litpic;
        this.senddate = senddate;
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", shorttitle='" + shorttitle + '\'' +
                ", litpic='" + litpic + '\'' +
                ", senddate='" + senddate + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShorttitle() {
        return shorttitle;
    }

    public void setShorttitle(String shorttitle) {
        this.shorttitle = shorttitle;
    }

    public String getLitpic() {
        return litpic;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
