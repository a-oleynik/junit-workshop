package com.dataart.qa.workshop.test;

import com.dataart.qa.workshop.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TimeoutTest {
    @Before
    public void setUp(){
        Utils.waitFor(1);
        System.out.println("Set up.");
        System.out.println("Set up id " + Thread.currentThread().getId());
    }

    @After
    public void tearDown(){
        Utils.waitFor(1);
        System.out.println("Tear down.");
        System.out.println("Tear down id " + Thread.currentThread().getId());
    }

    @Test(timeout = 6000)
    public void timeout_test(){
        System.out.println("Timeout test.");
        System.out.println("Test id " + Thread.currentThread().getId());
        Utils.waitFor(2);
    }

    @Test(timeout = 1000)
    public void timeout_test2(){
        System.out.println("Timeout test.");
        //System.out.println("Test id " + Thread.currentThread().getId());
        Utils.waitFor(10);
    }
}
