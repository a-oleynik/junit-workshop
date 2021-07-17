package com.dataart.qa.workshop.suites;

import com.dataart.qa.workshop.test.AssertTest;
import com.dataart.qa.workshop.test.MyRunnerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AssertTest.class, MyRunnerTest.class})
public class MySuite {
}
