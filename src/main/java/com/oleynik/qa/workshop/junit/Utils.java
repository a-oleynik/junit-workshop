package com.oleynik.qa.workshop.junit;

import java.util.concurrent.TimeUnit;

public class Utils {
    public static void waitFor(int timeoutInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(timeoutInSeconds);
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
