package cn.roylion.magictower.magictower.configuration;

import cn.roylion.magictower.magictower.factory.CellFactory;
import cn.roylion.magictower.magictower.factory.MonsterFactory;
import cn.roylion.magictower.magictower.pojo.Cell;
import cn.roylion.magictower.magictower.pojo.SoldierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

/**
 * Created by Administrator on 2018/4/20.
 */
@Configuration
public class beancfg {

    @Autowired
    private Environment env;

    @Bean("0001")
    public Cell wall(){
        return CellFactory.getInstance("0001",env);
    }

    @Bean("0002")
    public Cell floor(){
        return CellFactory.getInstance("0002",env);
    }

    @Bean("0003")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SoldierImpl sold0003(){
        return MonsterFactory.getInstance("0003",env);
    }
}
