package etc;

import java.util.LinkedList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MathParser {

    public static String solveEquation(String equ) throws ScriptException { //BETA until own method works

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = equ;

        return equ + "  =  " + engine.eval(foo).toString();

    }

    public static void main(String[] args) {
        solveEquationOwn("17+(54*714)/22");
    }

    public static double solveEquationOwn(String equ) {

        List<String> tiles = new LinkedList<>();
        String tmp = "";

        equ = equ.replace(" ", "");

        for (int i = 0; i < equ.length(); i++) {

            boolean tmp2 = true;
            
            switch (equ.charAt(i)) {
                case '*':
                case '/':
                case '+':
                case '-':
                case '(':
                case ')':
                    if (!"".equals(tmp)) {
                        tiles.add(tmp);
                    }
                    tiles.add(equ.charAt(i) + "");
                    tmp = "";
                    tmp2 = false;
            }
            if (tmp2) {
                tmp += equ.charAt(i);
            }

        }
        
        tiles.add(tmp);

        for (String s : tiles) {
            System.out.println(s);

        }

        return solveEquationOwn(tiles);
    }

    public static double solveEquationOwn(List<String> tiles) {

        return 0.0;
    }

}
