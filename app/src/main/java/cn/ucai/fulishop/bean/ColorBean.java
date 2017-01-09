package cn.ucai.fulishop.bean;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public class ColorBean {

    /**
     * catId : 8514
     * colorId : 4
     * colorName : 绿色
     * colorCode : #59d85c
     * colorImg : 201309/1380064997570506166.jpg
     */

    private int catId;
    private int colorId;
    private String colorName;
    private String colorCode;
    private String colorImg;

    public ColorBean() {
        super();
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorImg() {
        return colorImg;
    }

    public void setColorImg(String colorImg) {
        this.colorImg = colorImg;
    }

    @Override
    public String toString() {
        return "ColorBean{" +
                "catId=" + catId +
                ", colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", colorImg='" + colorImg + '\'' +
                '}';
    }
}
