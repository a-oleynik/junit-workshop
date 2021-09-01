package com.oleynik.qa.workshop.junit.ddt;

import com.oleynik.qa.workshop.junit.model.MyDoubleWrapper;
import com.oleynik.qa.workshop.junit.model.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValueSourceTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 9, -3, 115})
    void value_source_test(int x) {
        assertTrue(new MyDoubleWrapper(x).isOdd());
    }

    @ParameterizedTest
    @NullSource
    void null_source_test(User user) {
        assertNull(user);
    }

    @ParameterizedTest
    @EmptySource
    void empty_source_test(List<User> users) {
        assertEquals(0, users.size());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void null_and_empty_source_test(String input) {
        assertTrue(StringUtils.isBlank(input));
    }

    @ParameterizedTest
    @EnumSource(Month.class)
    void enum_source_test(Month month) {
        int monthNumber = month.getValue();
        assertTrue(monthNumber > 0 && monthNumber < 13);
    }
}
