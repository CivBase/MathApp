package com.toomanycooksapp.mathapp;

import java.util.Random;

/**
 * Created by Zach on 4/10/2015.
 */
public class NumberGenerator {

    private static int limit;
    private static Random random = new Random();

    public static void setLimit(int limit) {
        NumberGenerator.limit = limit;
    }

    public static int generateNumber() {
        return random.nextInt(NumberGenerator.limit);
    }

}
