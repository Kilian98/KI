package lib;

public class Random {

    public int getRandom(int min, int max) {

        if (max < min) {
            int max1;
            max1 = max;
            max = min;
            min = max1;
        }

        return ((int) (min + Math.random() * (max + 1 - min)));
    }

}
