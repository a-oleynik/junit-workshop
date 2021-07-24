package com.oleynik.qa.workshop.junit.retry;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetryRepeatedTest {
    @RepeatedTest(value = 2, name = RepeatedTest.LONG_DISPLAY_NAME)
    public void repeated_test() {
        System.out.println("Multiplication test.");
        assertEquals(10, 2 * 5, "Multiplication result is wrong");// 2*6
    }

    @RepeatedTest(value = 2, name = "My custom name {currentRepetition}/{totalRepetitions}")
    void repeated_test_with_custom_display_name(TestInfo testInfo) {
        assertEquals(2, Math.addExact(1, 1), "1 + 1 should equal 2");
    }

    @RepeatedTest(4)
    void repetition_info_for_repeated_test(RepetitionInfo repetitionInfo) {
        System.out.println("Repetition " + repetitionInfo.getCurrentRepetition());
        assertEquals(4, repetitionInfo.getTotalRepetitions());
    }
}
