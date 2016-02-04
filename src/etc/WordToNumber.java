package etc;

public class WordToNumber {

    static String[] numbers = {"null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht", "neun", "zehn", "elf",
        "zwölf", "dreizehn", "vierzehn", "fünfzehn", "sechzehn", "siebzehn", "achtzehn", "neunzehn", "zwanzig"}; //nearly nobody will type more than twenty, but feature will
    //be added lateron to higher numbers

    public static void main(String[] args) {
        System.out.println(format("eins zwei drei zwanzig zweiundzwanzig"));
    }

    static public String format(String s) {

        String edited = s;

        for (int i = 0; i <= 20; i++) {

            edited = edited.replace(" " + numbers[i] + "", " " +  i );
        }
        
        edited = edited.replace(" mal ", "*");
        edited = edited.replace(" durch ", "/");
        edited = edited.replace(" plus ", "+");
        edited = edited.replace(" minus ", "-");
        

        return edited;
    }

}
