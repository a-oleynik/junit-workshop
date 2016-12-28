package com.dataart.qa.workshop.suites;

import com.dataart.qa.workshop.test.AssertTest;
import com.dataart.qa.workshop.test.Example1Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AssertTest.class, Example1Test.class})
public class MySuite {
}
