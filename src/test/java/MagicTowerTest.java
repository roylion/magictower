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
                {"c0002","c0002","c0002","m0001","m0002","m0001","c0002","c0002","c0002","c0002","c0002",},// 1
                {"c0001","c0001","c0001","c0001","c0001","c0001","c0001","c0001","c0001","c0001","c0002",},// 2
                {"c0002","c0002","m0004","c0002","c0002","c0001","c0002","c0002","c0002","c0001","c0002",},// 3
                {"c0002","m0004","c0002","c0001","c0002","c0001","c0002","c0002","c0002","c0001","c0002",},// 4
                {"c0001","c0002","c0001","c0001","c0002","c0001","c0001","c0001","m0003","c0001","c0002",},// 5
                {"c0002","m0005","c0002","c0001","c0002","c0002","m0012","m0001","m0007","c0001","c0002",},// 6
                {"c0002","c0002","c0002","c0001","c0002","c0001","c0001","c0001","c0001","c0001","c0002",},// 7
                {"c0001","c0002","c0001","c0001","c0002","c0002","c0002","c0002","c0002","c0002","c0002",},// 8
                {"c0002","m0005","c0002","c0001","c0001","c0002","c0001","c0001","c0001","c0002","c0001",},// 9
                {"c0002","c0002","c0002","c0001","c0002","c0002","c0002","c0001","c0002","m0010","c0002",},// 10
                {"c0002","c0002","c0002","c0001","c0002","c0002","c0002","c0001","c0002","c0002","c0002",}};// 11

        Cell[][] cells= new Cell[11][11];
        for (int y = 0 ; y < map.length ; ++y) {
            for (int x = 0; x < map[y].length; ++x) {
                cells[y][x] = ctx.getBean(map[y][x], Cell.class);
            }
        }

        JPanel jPanel = new JPanel() {
            int finalCount = 0;
            @Override
            public void paint(Graphics g) {
                for (int y = 0 ; y < cells.length ; ++y){
                    for(int x = 0 ; x < cells[y].length ; ++x){
                        Cell cell = cells[y][x];

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
                TimeUnit.MILLISECONDS.sleep(500);
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
        Cell bean = ctx.getBean("c0001", Cell.class);
        Cell bean2 = ctx.getBean("c0001", Cell.class);
        Cell bean3 = ctx.getBean("m0001", Cell.class);
        Cell bean4 = ctx.getBean("m0001", Cell.class);
        System.out.println(bean);
    }
}
