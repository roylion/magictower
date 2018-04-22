import cn.roylion.magictower.magictower.MagicTower;
import cn.roylion.magictower.magictower.pojo.Cell;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Properties;
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
                {"c2","c2","c2","m1","m2","m1","c2","c2","c2","c2","c2",},// 1
                {"c1","c1","c1","c1","c1","c1","c1","c1","c1","c1","c2",},// 2
                {"p1","c2","m4","p8","c2","c1","p1","p5","p1","c1","c2",},// 3
                {"p5","m4","p3","c1","c2","c1","p1","p5","p1","c1","c2",},// 4
                {"c1","p8","c1","c1","c2","c1","c1","c1","m3","c1","c2",},// 5
                {"p5","m5","c2","c1","c2","p8","m12","m1","m7","c1","c2",},// 6
                {"p4","c2","p6","c1","c2","c1","c1","c1","c1","c1","c2",},// 7
                {"c1","p8","c1","c1","c2","c2","c2","c2","c2","c2","c2",},// 8
                {"c2","m5","c2","c1","c1","p10","c1","c1","c1","p8","c1",},// 9
                {"p1","p2","p5","c1","c2","c2","c2","c1","p5","m10","p6",},// 10
                {"p1","c2","p5","c1","c2","c2","c2","c1","p5","p5","p5",}};// 11

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
                TimeUnit.MILLISECONDS.sleep(300);
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
        Cell bean = ctx.getBean("c1", Cell.class);
        Cell bean2 = ctx.getBean("c1", Cell.class);
        Cell bean3 = ctx.getBean("m1", Cell.class);
        Cell bean4 = ctx.getBean("m1", Cell.class);
        System.out.println(bean);
    }

    @Test
    public void test3() throws Exception {
        Properties p = new Properties();
        p.load(new FileInputStream("D:\\Roylion\\workspace\\magictower\\src\\main\\resources\\code.properties"));

        Enumeration<?> enumeration = p.propertyNames();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement());
        }

    }

    public void makeMap (){

    }
}
