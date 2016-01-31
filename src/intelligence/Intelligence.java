package intelligence;

import etc.MathParser;
import etc.RecognizableWords;
import etc.WordArrays;
import javax.script.ScriptException;
import lib.Random;

public class Intelligence {

    public static final String prefix = "KI:\t";

    public Intelligence() {

    }

    public String newInput(String text) {

        String input = text.toLowerCase();
        String output = process(input);
        return !output.isEmpty() ? prefix + output : prefix + "Ich kann hierauf leider noch nichts antworten.";

    }

    public String process(String text) {

        if (isGreeting(text)) {
            return oneOf(WordArrays.greetings);
        }

        String questionTmp = isQuestion(text); //much faster than searching for an answer twice later

        if (!questionTmp.isEmpty()) {
            return questionTmp;
        }

        return "";
    }

    private String isQuestion(String text) {

        if (text.equals("exit")) {
            System.exit(0);
        }

        if (text.startsWith("was ")) {
            return questionWhat(text);
        }

        if (text.startsWith("wie ")) {
            return questionHow();
        }

        if (text.startsWith("warum ")) {
            return questionWhy();
        }

        if (text.startsWith("wieso ")) {
            return questionWhy();
        }

        return "";
    }

    private String oneOf(String[] strArr) {
        Random rnd = new Random();
        return strArr[rnd.getRandom(0, strArr.length - 1)];
    }

    private boolean containsOneOf(String text, String[] strArr) {

        for (String s : strArr) {
            if (text.equals(s)) {
                return true;
            }
        }

        return false;
    }

    private boolean isGreeting(String text) {
        return containsOneOf(text, RecognizableWords.greetings);
    }

    private String questionWhy() {
        return "Du fragst, warum";
    }

    private String questionHow() {
        return "Du fragst, wie";
    }

    private String questionWhat(String text) {

        String tmpText = text.substring(4);

        if (tmpText.startsWith("gibt") || tmpText.startsWith("ist") || tmpText.startsWith("ergibt")) {

            try {
                String tmp = tmpText.replace("gibt", "");
                tmp = tmp.replace("ist", "");
                tmp = tmp.replace("ergibt", "");
                tmp = tmp.replace(" ", "");

                return MathParser.solveEquation(tmp);
            } catch (ScriptException e) {
            }

        }

        return "Du fragst, was";
    }

}
