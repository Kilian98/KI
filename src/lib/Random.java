package lib;

public class Random {

    public int getRandom(int min, int max) {

        if (max < min) { //swap values if min is larger than max
            int tmp;
            tmp = max;
            max = min;
            min = tmp;
        }

        return ((int) (min + Math.random() * (max + 1 - min)));
    }

}
