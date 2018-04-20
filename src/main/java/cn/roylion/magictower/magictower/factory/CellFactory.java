package cn.roylion.magictower.magictower.factory;

import cn.roylion.magictower.magictower.configuration.Img;
import cn.roylion.magictower.magictower.pojo.Cell;
import org.springframework.core.env.Environment;

import java.awt.*;

/**
 * Created by Administrator on 2018/4/20.
 */
public class CellFactory {

    public static Cell getInstance(String code, Environment env) {
        Cell cell = null;
        if (env.containsProperty(code)) {
            cell = new Cell();
            String attrs = env.getProperty(code);
            String[] str = attrs.split(" ");
            cell.setCode(code);
            cell.setName(str[0]);
            cell.setIswalk(Boolean.valueOf(str[1]));
            cell.setImages(Img.getImgs(code,Integer.valueOf(str[2])));
        }
        return cell;
    }

}
