package my.qq.com.czw3dmgame.uitls;

/**
 * Created by czw on 2016/7/5  19:28.
 */
public class News {
    private String id;
    private String typeid;
    private String title;
    private String shorttitle;
    private String litpic;
    private String senddate;
    private String feedback;
    private String arcurl;

    public News(String id, String typeid, String title, String shorttitle, String litpic, String senddate, String feedback, String arcurl) {
        this.id = id;
        this.typeid = typeid;
        this.title = title;
        this.shorttitle = shorttitle;
        this.litpic = litpic;
        this.senddate = senddate;
        this.feedback = feedback;
        this.arcurl = arcurl;
    }

    @Override
    public String toString() {
        return "News{" +
                "id='" + id + '\'' +
                ", typeid='" + typeid + '\'' +
                ", title='" + title + '\'' +
                ", shorttitle='" + shorttitle + '\'' +
                ", litpic='" + litpic + '\'' +
                ", senddate='" + senddate + '\'' +
                ", feedback='" + feedback + '\'' +
                ", arcurl='" + arcurl + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
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

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl;
    }
}
