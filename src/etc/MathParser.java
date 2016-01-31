package etc;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MathParser {

    public static String solveEquation(String equ) throws ScriptException {

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = equ;

        return equ + "  =  " + engine.eval(foo).toString();

    }

}
