package cn.roylion.magictower.magictower.util;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class StringUtils {

    static ScriptEngine js = new ScriptEngineManager().getEngineByName("JavaScript");

    public static int eval(String string){

        try {
            return (int) js.eval(string);
        } catch (ScriptException e) {
            e.printStackTrace();
            throw new RuntimeException("计算错误！");
        }
    }


    @Test
    public void aa() throws ScriptException {
        int eval = StringUtils.eval("2*5");
        System.out.println(eval);
    }
}
