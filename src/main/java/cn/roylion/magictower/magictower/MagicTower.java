package cn.roylion.magictower.magictower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by Administrator on 2018/4/20.
 */
@Configuration
@ComponentScan
@PropertySource(value = "code.properties", encoding = "utf-8")
public class MagicTower {

    @Autowired
    public Environment env;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MagicTower.class);
    }
}
