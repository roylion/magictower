import cn.roylion.magictower.magictower.MagicTower;
import cn.roylion.magictower.magictower.pojo.Cell;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MagicTower.class)
public class MagicTowerTest implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Test
    public void test(){
        String[][] map = {
                {"0002","0002","0003","0002","0002","0002","0002","0002","0002","0002","0002",},// 1
                {"0001","0001","0001","0001","0001","0001","0001","0001","0001","0001","0002",},// 2
                {"0002","0002","0002","0002","0002","0001","0002","0002","0002","0001","0002",},// 3
                {"0002","0002","0002","0001","0002","0001","0002","0002","0002","0001","0002",},// 4
                {"0001","0002","0001","0001","0002","0001","0001","0001","0002","0001","0002",},// 5
                {"0002","0002","0002","0001","0002","0002","0002","0002","0002","0001","0002",},// 6
                {"0002","0002","0002","0001","0002","0001","0001","0001","0001","0001","0002",},// 7
                {"0001","0002","0001","0001","0002","0002","0002","0002","0002","0002","0002",},// 8
                {"0002","0002","0002","0001","0001","0002","0001","0001","0001","0002","0001",},// 9
                {"0002","0002","0002","0001","0002","0002","0002","0001","0002","0002","0002",},// 10
                {"0002","0002","0002","0001","0002","0002","0002","0001","0002","0002","0002",}};// 11


        JPanel jPanel = new JPanel() {
            int finalCount = 0;
            @Override
            public void paint(Graphics g) {
                for (int y = 0 ; y < map.length ; ++y){
                    for(int x = 0 ; x < map[y].length ; ++x){
                        Cell cell = ctx.getBean(map[y][x],Cell.class);

                        int index = finalCount % cell.getImages().length;
                        if(cell != null)
                            g.drawImage(cell.getImages()[index],x*32,y*32,null);
                        else
                            g.drawImage(cell.getImages()[index],x*32,y*32,null);
                        finalCount++;
                    }
                }
            }
        };
        JFrame game = new JFrame("map");
        game.add(jPanel);
        game.setSize(352,352);
        game.setLocationRelativeTo(null);
        game.setResizable(false);
        game.setUndecorated(true);
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setVisible(true);
        while(true){
            jPanel.repaint();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }

    @Test
    public void test2(){
        Cell bean = ctx.getBean("0003", Cell.class);
        System.out.println(bean);
    }
}
