package etc;

import Exceptions.DividedByCeroException;
import intelligence.Intelligence;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MathParser {

    public static String solveEquation(String equ) throws ScriptException, DividedByCeroException {

        //<editor-fold defaultstate="collapsed" desc="only to qualify if own-solution is correct and will be removed in the future">
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        double solution;
        long one;
        long two;

        try {
            one = System.nanoTime();
            solution = Double.parseDouble(engine.eval(equ).toString());
            one = System.nanoTime() - one;
            System.out.println("Java Algorithm needs: " + one + " nano Sekonds");
        } catch (Exception e) {
            System.err.println("Math-lib error");

            try {
                return equ + "  =  " + solveEquationOwn(equ) + "";
            } catch (Exception ex) {
                return "so einen schmarrn rechne ich nich";
            }

        }
//</editor-fold>

        two = System.nanoTime();
        double ownSolution = solveEquationOwn(equ);
        two = System.nanoTime() - two;
        System.out.println("Own Algorithm needs: " + two + " nano Sekonds");
        System.out.println("own Algorithm faster: " + (one > two));
        System.out.println("Faktor: " + ((int) Math.floor((double) one / (double) two)) + "x");
        if (solution != ownSolution) {
            System.err.println("Your Math-Parser had a failture:");
            System.err.println("Right solution: " + solution);
            System.err.println("Your Solution: " + ownSolution);
            return equ + "  =  " + solution + "";
        }

        return equ + "  =  " + ownSolution;

    }

    public static void main(String[] args) throws DividedByCeroException {
//        solveEquationOwn("17+(54*714)/22");
//
//        List<String> liste = new ArrayList<>();
//
//        liste.add("0");
//        liste.add("1");
//        liste.add("2");
//        liste.add("3");
//        liste.add("4");
//        liste.add("5");
//        liste.add("6");
//        liste.add("7");
//
//        replaceListEntries(2, 4, "richtig", liste);

        System.out.println(solveEquationOwn("(5+5*(3+3))*2+8"));

    }

    //todo
    public static String detectCalculation(String text) {

        int beginIndex = -1;
        int endIndex = -1;

        if (Intelligence.containsOneOf(text, WordArrays.numbers) && Intelligence.containsOneOf(text, WordArrays.math)) {

            text = text.replace(" ", "");

            for (int i = 0; i < text.length(); i++) {

                boolean foundSomething = false;
                char tmp = text.charAt(i);

                for (char s : WordArrays.numbers) {
                    if (tmp == s) {
                        foundSomething = true;
                        break;
                    }
                }

                for (char s : WordArrays.math) {
                    if (tmp == s) {
                        foundSomething = true;
                        break;
                    }
                }

                if (beginIndex != -1 && !foundSomething) {
                    endIndex = i;
                } else if (beginIndex == -1 && foundSomething) {
                    beginIndex = i;
                }

            }

        }

        if (endIndex == -1) {
            endIndex = text.length();
        }

        if (beginIndex == -1) {
            return "false";
        } else {
            System.out.println(text.substring(beginIndex, endIndex));
            return text.substring(beginIndex, endIndex);
        }
    }

    //ready, works fine, no Bugs, no need to edit
    private static double solveEquationOwn(String equ) throws DividedByCeroException {

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
                case '^':
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

    //division in Brackets, handling each bracket alone
    private static double solveEquationOwn(List<String> tiles) throws DividedByCeroException {

        //find the brackets and solve them recursively
        List<String> tmpBrackets = new ArrayList<>();

        int brackets = 0;
        boolean foundBrackets = false;
        int from = 0;
        int to;

        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("(")) {
                brackets++;

                if (!foundBrackets) {
                    from = i;
                    foundBrackets = true;
                    continue;
                }

            }

            if (tiles.get(i).equals(")")) {
                brackets--;
                if (brackets == 0) {
                    to = i;
                    replaceListEntries(from, to, solveEquationOwn(tmpBrackets) + "", tiles);
                    foundBrackets = false;
                    tmpBrackets.clear();
                    i = 0;
                }
            } //(((2+2)*3+4)/80)*(((2+2)*3+4)/80) //sample

            if (brackets > 0) {
                tmpBrackets.add(tiles.get(i));
            }

        }

        //real solving algorithm
        solveEquationWithoutBrackets(tiles);

        return Double.parseDouble(tiles.get(0));
    }

    //real solving algorithm
    private static double solveEquationWithoutBrackets(List<String> tiles) throws DividedByCeroException {

//        for (int i = 0; i < tiles.size(); i++) {
//
//            if (tiles.get(i).equals("^")) {
//                double one = Double.parseDouble(tiles.get(i - 1));
//                double two = Double.parseDouble(tiles.get(i + 1));
//
//                replaceListEntries(i - 1, i + 1, (times(one, two)) + "", tiles);
//                i--;
//            }
//
//        }
        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("/")) {
                double one = Double.parseDouble(tiles.get(i - 1));
                double two = Double.parseDouble(tiles.get(i + 1));

                if (two == 0) {
                    throw new DividedByCeroException();
                }

                replaceListEntries(i - 1, i + 1, (one / two) + "", tiles);
                i--;
            }

        }

        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("*")) {
                double one = Double.parseDouble(tiles.get(i - 1));
                double two = Double.parseDouble(tiles.get(i + 1));

                replaceListEntries(i - 1, i + 1, (one * two) + "", tiles);
                i--;
            }

        }

        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("-")) {
                double one;
                double two;

                try {

                    one = Double.parseDouble(tiles.get(i - 1));
                    two = Double.parseDouble(tiles.get(i + 1));
                } catch (ArrayIndexOutOfBoundsException e) {
                    one = 0;
                    two = (-1) * Double.parseDouble(tiles.get(i + 1));
                    replaceListEntries(i, i + 1, two + "", tiles);
                    continue;
                }

                replaceListEntries(i - 1, i + 1, (one - two) + "", tiles);
                i--;
            }

        }

        for (int i = 0; i < tiles.size(); i++) {

            if (tiles.get(i).equals("+")) {
                double one = Double.parseDouble(tiles.get(i - 1));
                double two = Double.parseDouble(tiles.get(i + 1));

                replaceListEntries(i - 1, i + 1, (one + two) + "", tiles);
                i--;
            }

        }

        return Double.parseDouble(tiles.get(0));
    }

    //bsp: 4² ist times(4, 2)
    private static double times(double one, double two) {

        double solution = 1;

        for (int i = 0; i < two; i++) {
            solution = solution * one;
        }

        return solution;

    }

    static void replaceListEntries(int startIndex, int endIndex, String value, List<String> liste) {

        for (int i = startIndex; i <= endIndex; i++) {

            liste.remove(startIndex);

        }
        liste.add(startIndex, value);
    }

}
