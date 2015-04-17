package com.toomanycooksapp.mathapp;

import java.util.Random;


public class NumberGenerator {
    private static int limit;
    private static Random rand = new Random();

    public static void setLimit(int limit) {
        NumberGenerator.limit = limit;
    }

    public static int generateNumber() {
        return rand.nextInt(NumberGenerator.limit);
    }
}
