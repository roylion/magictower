package cn.roylion.magictower.magictower.pojo;

import java.awt.*;

/**
 * Created by Administrator on 2018/4/20.
 */
public class Cell {

    protected String name;
    protected boolean walk;
    protected Image[] images;
    protected String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWalk() {
        return walk;
    }

    public void setWalk(boolean walk) {
        this.walk = walk;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
