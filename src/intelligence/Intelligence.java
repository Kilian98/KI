package intelligence;

import etc.WordArrays;
import etc.recocnizableWords;
import lib.Random;

public class Intelligence {

    public Intelligence() {

    }

    public String newInput(String text) {

        String output = divideIntoSections(text.toLowerCase());

        if (!output.equals("")) {
            return "KI:\t" + output;
        }

        return "KI:\tIch kann dir hierauf leider noch nichts antworten :/";
    }

    public String divideIntoSections(String text) {

        if (isGreeting(text)) {
            return oneOf(WordArrays.greetings);
        }

        String questionTmp = isQuestion(text); //will be much faster than twice searching for an answer later
        
        if (!questionTmp.equals("")) {
            return questionTmp;
        }

        return "";
    }

    private String isQuestion(String text) {

        if (text.startsWith("was")) {
            return questionWhat();
        }

        if (text.startsWith("wie")) {
            return questionHow();
        }

        if (text.startsWith("warum")) {
            return questionWhy();
        }

        if (text.startsWith("wieso")) {
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
        return containsOneOf(text, recocnizableWords.greetings);
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
