package cn.roylion.magictower.magictower.pojo;


import java.util.Arrays;

public class Role extends Person {

    protected int lv;
    protected int yellowKey;
    protected int blueKey;
    protected int redKey;
    /** 记录主角的面向，控制显示图片 */
    protected int face;
    protected int x;
    protected int y;

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getYellowKey() {
        return yellowKey;
    }

    public void setYellowKey(int yellowKey) {
        this.yellowKey = yellowKey;
    }

    public int getBlueKey() {
        return blueKey;
    }

    public void setBlueKey(int blueKey) {
        this.blueKey = blueKey;
    }

    public int getRedKey() {
        return redKey;
    }

    public void setRedKey(int redKey) {
        this.redKey = redKey;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Role{" +
                "lv=" + lv +
                ", \nname='" + name + '\'' +
                ", \nhp=" + hp +
                ", \nack=" + ack +
                ", \ndef=" + def +
                ", \ngold=" + gold +
                ", \nexp=" + exp +
                ", \nyellowKey=" + yellowKey +
                ", \nblueKey=" + blueKey +
                ", \nredKey=" + redKey +
                '}';
    }
}
