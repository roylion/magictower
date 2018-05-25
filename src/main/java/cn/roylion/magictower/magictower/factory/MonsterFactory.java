package cn.roylion.magictower.magictower.factory;

import cn.roylion.magictower.magictower.configuration.Img;
import cn.roylion.magictower.magictower.pojo.MonsterImpl;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/4/20.
 */
@Component
public class MonsterFactory {

    public MonsterImpl getInstance(String code, Environment env) {
        MonsterImpl monster = null;
        if(env.containsProperty(code)) {
            monster = new MonsterImpl();
            String attrs = env.getProperty(code);
            String[] str = attrs.split(" ");
            monster.setName(str[0]);
            monster.setHp(Integer.valueOf(str[1]));
            monster.setAck(Integer.valueOf(str[2]));
            monster.setDef(Integer.valueOf(str[3]));
            monster.setGold(Integer.valueOf(str[4]));
            monster.setExp(Integer.valueOf(str[5]));
            monster.setImages(Img.getImgs(code,Integer.valueOf(str[6])));
        }

        return monster;
    }
}
