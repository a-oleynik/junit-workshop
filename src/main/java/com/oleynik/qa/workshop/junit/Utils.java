package com.oleynik.qa.workshop.junit;

public class Utils {
    public static void waitFor(int timeoutInSeconds) {
        try {
            Thread.sleep(timeoutInSeconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void checkMaxTries(int maxTries){
        if (maxTries < 1) {
            throw new IllegalArgumentException("Impossible number of maximum tries");
        }
    }
}
