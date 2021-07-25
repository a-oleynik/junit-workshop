package com.oleynik.qa.workshop.junit.general;

import org.junit.*;

public class FixturesTest {
    private static final String ACTUAL = "Actual string";
    private static final String EXPECTED = "Actual string";// "Expected string"

    @BeforeClass
    public static void globalSetUp(){
        System.out.println("Set up class.");
    }

    @AfterClass
    public static void globalTearDown(){
        System.out.println("tear down class.");
    }

    @Before
    public void setUp(){
        System.out.println("Set up method.");
    }

    @After
    public void tearDown(){
        System.out.println("Test down method.");
    }

    @Test
    public void fixtures_the_first_test(){
        System.out.println("The first test.");
        Assert.assertEquals("Wrong string found", EXPECTED, ACTUAL);
    }

    @Test
    public void fixtures_the_second_test(){
        System.out.println("The second test.");
        Assert.assertEquals("Wrong string found", EXPECTED, ACTUAL);
    }
}
