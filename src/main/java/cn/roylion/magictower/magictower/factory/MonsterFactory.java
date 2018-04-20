package cn.roylion.magictower.magictower.factory;

import cn.roylion.magictower.magictower.configuration.Img;
import cn.roylion.magictower.magictower.pojo.SoldierImpl;
import org.springframework.core.env.Environment;

import java.awt.*;

/**
 * Created by Administrator on 2018/4/20.
 */
public class MonsterFactory {

    public static SoldierImpl getInstance(String code, Environment env) {
        SoldierImpl soldoier = null;
        if(env.containsProperty(code)) {
            soldoier = new SoldierImpl();
            String attrs = env.getProperty(code);
            String[] str = attrs.split(" ");
            soldoier.setName(str[0]);
            soldoier.setHp(Integer.valueOf(str[1]));
            soldoier.setAck(Integer.valueOf(str[2]));
            soldoier.setDef(Integer.valueOf(str[3]));
            soldoier.setGold(Integer.valueOf(str[4]));
            soldoier.setExp(Integer.valueOf(str[5]));
            Image[] images = new Image[str.length - 6];
            for (int i = 6; i < str.length; i++) {
                images[i - 6] = Img.getImg(str[i]);
            }
            soldoier.setImages(images);
        }

        return soldoier;
    }
}
