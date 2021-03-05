package pw.saber.framework.math;

import java.util.concurrent.ThreadLocalRandom;

public class Range {
    private int min;

    private int max;

    private double chanceToAdd;

    public Range(int min, int max, double chanceToAdd) {
        this.min = min;
        this.max = max;
        this.chanceToAdd = chanceToAdd;
    }

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static int between(int min, int maxInclusive) {
        int dif = maxInclusive - min;
        if (dif <= 0)
            return min;
        return min + ThreadLocalRandom.current().nextInt(dif + 1);
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public double getChanceToAdd() {
        return this.chanceToAdd;
    }

    public int getRandom() {
        return between(this.min, this.max);
    }
}

