package intelligence;

import etc.WordArrays;
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

        if (!isQuestion(text).equals("")) {
            return "Du hast eine Frage gestellt";
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

    private boolean isGreeting(String text) {

        switch (text) {
            case "hi":
                return true;
            case "hallo":
                return true;
            case "hey":
                return true;
            case "jo":
                return true;
            case "tag":
                return true;
            case "tach":
                return true;
            case "morgen":
                return true;
            case "moinsen":
                return true;
            case "servus":
                return true;
            case "yo":
                return true;
            case "goedendag":
                return true;
            default:
                return false;
        }

    }

    private String questionWhy() {
        return "";
    }

    private String questionHow() {
        return "";
    }

    private String questionWhat() {
        return "";
    }

}
