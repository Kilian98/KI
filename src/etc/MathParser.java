package etc;

import java.util.ArrayList;
import java.util.Iterator;
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
//        solveEquationOwn("17+(54*714)/22");

        List<String> liste = new ArrayList<>();

        liste.add("0");
        liste.add("1");
        liste.add("2");
        liste.add("3");
        liste.add("4");
        liste.add("5");
        liste.add("6");
        liste.add("7");

        replaceListEntries(2, 4, "richtig", liste);
    }

    //ready, works fine, no Bugs, no need to edit
    private static double solveEquationOwn(String equ) {

        List<String> tiles = new ArrayList<>();
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

    //not ready, edit in progress
    private static double solveEquationOwn(List<String> tiles) {

        //find the brackets and solve them recursively
        List<String> tmpBrackets = new ArrayList<>();

        int brackets = 0;
        int firstBracket;
        boolean foundBrackets = false;
        int from = 0;
        int to;

        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("(")) {
                brackets++;

                if (!foundBrackets) {
                    from = i;
                }

                foundBrackets = true;
            }

            if (tiles.get(i).equals(")")) {
                brackets--;
                if (brackets == 0) {
                    to = i;
                    replaceListEntries(from, to - 1, solveEquationOwn(tmpBrackets) + "", tiles);
                }
            }

            if (brackets > 0) {
                tmpBrackets.add(tiles.get(i));
            }

        }

        //real solving algorithm
        if (!foundBrackets) {
            solveEquationWithoutBrackets(tiles);
        }

        return Double.parseDouble(tiles.get(0));
    }

    //real solving algorithm
    private static double solveEquationWithoutBrackets(List<String> tiles) {

        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("*")) {
                double one = Double.parseDouble(tiles.get(i - 1));
                double two = Double.parseDouble(tiles.get(i + 1));

                replaceListEntries(i - 1, i + 1, (one * two) + "", tiles);
            }

        }

        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("/")) {
                double one = Double.parseDouble(tiles.get(i - 1));
                double two = Double.parseDouble(tiles.get(i + 1));

                replaceListEntries(i - 1, i + 1, (one / two) + "", tiles);
            }

        }

        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("+")) {
                double one = Double.parseDouble(tiles.get(i - 1));
                double two = Double.parseDouble(tiles.get(i + 1));

                replaceListEntries(i - 1, i + 1, (one + two) + "", tiles);
            }

        }

        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("-")) {
                double one = Double.parseDouble(tiles.get(i - 1));
                double two = Double.parseDouble(tiles.get(i + 1));

                replaceListEntries(i - 1, i + 1, (one - two) + "", tiles);
            }

        }

        return Double.parseDouble(tiles.get(0));
    }

    static void replaceListEntries(int startIndex, int endIndex, String value, List<String> liste) {

        for (int i = startIndex; i <= endIndex; i++) {

            liste.remove(startIndex);

        }
        liste.add(startIndex, value);
    }

}
