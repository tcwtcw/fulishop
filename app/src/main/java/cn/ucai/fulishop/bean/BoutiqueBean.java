package cn.ucai.fulishop.bean;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class BoutiqueBean {

    /**
     * id : 265
     * title : 刘亦菲女神款，拥有它你就是明星！
     * description : 复合水+天然植物提取精华，令肌肤水分充足，水嫩盈润！
     * name : 金牌百搭精华，秒杀缺水肌！
     * imageurl : cat_image/boutique4.png
     */


    private int id;
    private String title;
    private String description;
    private String name;
    private String imageurl;

    public BoutiqueBean() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "BoutiqueBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
