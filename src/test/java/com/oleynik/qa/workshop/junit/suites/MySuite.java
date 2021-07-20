package com.oleynik.qa.workshop.junit.suites;

import com.oleynik.qa.workshop.junit.general.AssertTest;
import com.oleynik.qa.workshop.junit.general.ExecutionListenerRunnerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AssertTest.class, ExecutionListenerRunnerTest.class})
public class MySuite {
}
