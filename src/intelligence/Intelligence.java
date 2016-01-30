package intelligence;

import etc.WordArrays;
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

    /*
     @params text needs to be lower case
     */
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

        if (text.startsWith("was ")) {
            return questionWhat();
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
        return containsOneOf(text, WordArrays.greetings);
    }

    private String questionWhy() {
        return "Du fragst, warum";
    }

    private String questionHow() {
        return "Du fragst, wie";
    }

    private String questionWhat() {
        return "Du fragst, was";
    }

}
