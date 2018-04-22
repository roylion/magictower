package cn.roylion.magictower.magictower.pojo;

import cn.roylion.magictower.magictower.util.StringUtils;

public class Prop extends Cell {

    protected String hp;
    protected String ack;
    protected String def;
    protected String gold;
    protected String exp;
    protected String lv;
    protected String yellowKey;
    protected String blueKey;
    protected String redKey;
    protected String remark;

    public boolean takeEffect(Role role){
        role.hp = StringUtils.eval(role.hp + hp);
        role.ack = StringUtils.eval(role.ack + ack);
        role.def = StringUtils.eval(role.def + def);
        role.gold = StringUtils.eval(role.gold + gold);
        role.exp = StringUtils.eval(role.exp + exp);
        role.lv = StringUtils.eval(role.lv + lv);

        int yellow = StringUtils.eval(role.yellowKey + yellowKey);
        if (yellow < 0) return false;
        role.yellowKey = yellow;

        int blue = StringUtils.eval(role.blueKey + blueKey);
        if (blue < 0) return false;
        role.blueKey = blue;

        int red = StringUtils.eval(role.redKey + redKey);
        if (red < 0) return false;
        role.redKey = red;

        return true;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getYellowKey() {
        return yellowKey;
    }

    public void setYellowKey(String yellowKey) {
        this.yellowKey = yellowKey;
    }

    public String getBlueKey() {
        return blueKey;
    }

    public void setBlueKey(String blueKey) {
        this.blueKey = blueKey;
    }

    public String getRedKey() {
        return redKey;
    }

    public void setRedKey(String redKey) {
        this.redKey = redKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
