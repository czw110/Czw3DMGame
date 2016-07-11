package my.qq.com.czw3dmgame.uitls;

/**
 * Created by czw on 2016/7/5  19:28.
 */
public class News {
    private int id;
    private String typeid;
    private String title;
    private String shorttitle;
    private String litpic;
    private String senddate;
    private String weight;
    private String arcurl;
    private String description;

    public News(int id, String typeid, String title, String shorttitle, String litpic, String senddate, String weight, String arcurl, String description) {
        this.id = id;
        this.typeid = typeid;
        this.title = title;
        this.shorttitle = shorttitle;
        this.litpic = litpic;
        this.senddate = senddate;
        this.weight = weight;
        this.arcurl = arcurl;
        this.description = description;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", typeid='" + typeid + '\'' +
                ", title='" + title + '\'' +
                ", shorttitle='" + shorttitle + '\'' +
                ", litpic='" + litpic + '\'' +
                ", senddate='" + senddate + '\'' +
                ", weight='" + weight + '\'' +
                ", arcurl='" + arcurl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getArcurl() {
        return arcurl;
    }

    public void setArcurl(String arcurl) {
        this.arcurl = arcurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
