package cn.roylion.magictower.magictower.factory;

import cn.roylion.magictower.magictower.configuration.Img;
import cn.roylion.magictower.magictower.pojo.Prop;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropFactory {

    public Prop getInstance(String code, Environment env) {
        Prop prop = null;
        if(env.containsProperty(code)) {
            prop = new Prop();
            String attrs = env.getProperty(code);
            String[] str = attrs.split(" ");
            prop.setName(str[0]);
            prop.setRemark(str[1]);
            prop.setImages(Img.getImgs(code,Integer.valueOf(str[2])));
            for(int i=3; i < str.length; i++){
                String s = str[i];
                if(s.startsWith("hp")) {
                    prop.setHp(s.substring(2));
                }
                if(s.startsWith("ack")) {
                    prop.setAck(s.substring(3));
                }
                if(s.startsWith("def")) {
                    prop.setDef(s.substring(3));
                }
                if(s.startsWith("gold")) {
                    prop.setGold(s.substring(4));
                }
                if(s.startsWith("exp")) {
                    prop.setExp(s.substring(3));
                }
                if(s.startsWith("lv")) {
                    prop.setLv(s.substring(2));
                }
                if(s.startsWith("yellow")) {
                    prop.setYellowKey(s.substring(6));
                }
                if(s.startsWith("blue")) {
                    prop.setBlueKey(s.substring(4));
                }
                if(s.startsWith("red")) {
                    prop.setRedKey(s.substring(3));
                }
            }

        }
        return prop;
    }
}
