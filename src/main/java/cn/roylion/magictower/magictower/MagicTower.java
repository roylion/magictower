package cn.roylion.magictower.magictower;

import cn.roylion.magictower.magictower.factory.CellFactory;
import cn.roylion.magictower.magictower.factory.MonsterFactory;
import cn.roylion.magictower.magictower.pojo.Cell;
import cn.roylion.magictower.magictower.pojo.SoldierImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

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

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MagicTower.class);

        Object m0001 = ctx.getBean("m0019");
        System.out.println(111);
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
            throw new RuntimeException("code.properties id not found!");
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
}
