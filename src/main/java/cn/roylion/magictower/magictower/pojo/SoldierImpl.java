package cn.roylion.magictower.magictower.pojo;

import cn.roylion.magictower.magictower.identity.Soldier;

/**
 * Created by Administrator on 2018/4/20.
 */
public class SoldierImpl extends Person implements Soldier {

    @Override
    public void fight(Person person) {
        int damage = this.ack - person.def;
        person.hp -= damage;
    }
}
