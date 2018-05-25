package cn.roylion.magictower.magictower.core;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2018/5/4.
 */
@Component
public class GameMap {
    String[][][] map;

    @PostConstruct
    public void init(){
        map = new String[25][][];
        map[0]=new String[][]{};
    }

}
