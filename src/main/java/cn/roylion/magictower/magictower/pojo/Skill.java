package cn.roylion.magictower.magictower.pojo;

/**
 * Created by Administrator on 2018/4/20.
 */
public abstract class Skill {

    protected int type;

    /**
     * 发动技能效果
     * @param person1 发动技能者
     * @param person2 承受技能者
     */
    public abstract void launch(Person person1,Person person2);
}
