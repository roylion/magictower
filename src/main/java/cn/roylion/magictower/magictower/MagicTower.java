package cn.roylion.magictower.magictower;

import cn.roylion.magictower.magictower.configuration.Img;
import cn.roylion.magictower.magictower.pojo.Cell;
import cn.roylion.magictower.magictower.pojo.Prop;
import cn.roylion.magictower.magictower.pojo.Role;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/4/20.
 */
@Configuration
@ComponentScan
@PropertySource(value = "code.properties", encoding = "utf-8")
public class MagicTower implements BeanFactoryAware {

    @Autowired
    private Environment env;

    private BeanFactory beanFactory;

    @Bean
    public Role role (){
        Role role = new Role();
        role.setName("勇士");
        role.setHp(1500);
        role.setAck(15);
        role.setDef(15);
        role.setImages(Img.getImgs("role",4));
        role.setX(5);
        role.setY(10);
        role.setBlueKey(1);
        role.setRedKey(1);
        role.setYellowKey(1);
        return role;
    }



    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MagicTower.class);
        Role role = ctx.getBean("role", Role.class);
        Game game = ctx.getBean("game", Game.class);
        String[][] map = {
                {"c2","c2","c2","m1","m2","m1","c2","c2","c2","c2","c2",},// 1
                {"c1","c1","c1","c1","c1","c1","c1","c1","c1","c1","c2",},// 2
                {"p1","c2","c2","p8","c2","c1","p1","p5","p1","c1","c2",},// 3
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

            @Override
            public void paint(Graphics g) {
                int finalCount = game.finalCount;
                for (int y = 0 ; y < cells.length ; ++y){
                    for(int x = 0 ; x < cells[y].length ; ++x){
                        Cell cell = cells[y][x];

                        int index = finalCount % cell.getImages().length;
                        if(cell != null) {
                            g.drawImage(cell.getImages()[index], x * 32, y * 32, null);
                        }
                        g.drawImage(role.getImages()[role.getFace()], role.getX()*32, role.getY()*32,null);
                    }
                }
            }
        };

        JFrame frame = new JFrame("map");
        frame.add(jPanel);
        frame.setSize(352,352);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        KeyListener kl = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int y = role.getY();
                int x = role.getX();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        y--;
                        role.setFace(0);
                        break;
                    case KeyEvent.VK_DOWN:
                        y++;
                        role.setFace(1);
                        break;
                    case KeyEvent.VK_LEFT:
                        x--;
                        role.setFace(2);
                        break;
                    case KeyEvent.VK_RIGHT:
                        x++;
                        role.setFace(3);
                        break;
                    case KeyEvent.VK_Q:
                        System.exit(0);
                    default:
                }
                if(x>=11 || y>=11 || x<0 || y<0) {
                    return;
                }
                Cell cell = cells[y][x];
                if(cell instanceof Prop) {
                    Prop prop = (Prop) cell;
                    if(!prop.takeEffect(role)){
                        return;
                    }
                    System.out.println(prop.getRemark());
                    showDiag(frame,prop.getRemark());
                    cells[y][x]=ctx.getBean("c2",Cell.class);
                    System.out.println(role);
                    return;
                }
                if(!cells[y][x].isWalk()) {
                    return;
                }
                role.setX(x);
                role.setY(y);
                jPanel.repaint();
            }
        };
        jPanel.setFocusable(true);
        jPanel.addKeyListener(kl);
        jPanel.requestFocusInWindow();

        while(true) {
            jPanel.repaint();
           if( game.finalCount<Integer.MAX_VALUE){
               game.finalCount++;
           } else {
               game.finalCount=0;
           }
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @PostConstruct
    public void init(){
        loadCode(env,beanFactory);
    }

    public void loadCode(Environment env, BeanFactory beanFactory){
        Properties p = new Properties();
        try {
            p.load(MagicTower.class.getClassLoader().getResourceAsStream("code.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("code.properties is not found!");
        }
        Enumeration<?> enumeration = p.propertyNames();
        while (enumeration.hasMoreElements()){
            String code = enumeration.nextElement().toString();
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();

            String factory = "";
            if(code.startsWith("c")){
                factory = "cellFactory";
            } else if (code.startsWith("m")) {
                factory = "monsterFactory";
                beanDefinitionBuilder.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
            } else if (code.startsWith("p")) {
                factory = "propFactory";
            }

            beanDefinitionBuilder.setFactoryMethodOnBean("getInstance",factory);
            beanDefinitionBuilder.addConstructorArgValue(code);
            beanDefinitionBuilder.addConstructorArgValue(env);

            DefaultListableBeanFactory DLBeanFactory = (DefaultListableBeanFactory) beanFactory;
            DLBeanFactory.registerBeanDefinition(code,beanDefinitionBuilder.getBeanDefinition());
            System.out.println(code + "载入成功！");

        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public static void showDiag(JFrame frame,String message){
        JDialog d = new JDialog(frame, "", false);
        d.setSize(200,50);
        d.setBackground(Color.gray);
        d.setLocationRelativeTo(null);
        d.setUndecorated(true);
        d.setLayout(new BorderLayout());
        d.setResizable(false);
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        d.add(messageLabel,BorderLayout.CENTER);
        d.setVisible(true);

        SwingUtilities.invokeLater(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            d.dispose();
        });

    }
}
