package cn.roylion.magictower.magictower.configuration;

import cn.roylion.magictower.magictower.factory.CellFactory;
import cn.roylion.magictower.magictower.pojo.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
}
