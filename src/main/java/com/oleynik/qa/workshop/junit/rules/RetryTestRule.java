package com.oleynik.qa.workshop.junit.rules;

import com.oleynik.qa.workshop.junit.annotations.Retry;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.concurrent.atomic.AtomicInteger;

import static com.oleynik.qa.workshop.junit.Utils.checkMaxTries;

public class RetryTestRule implements TestRule {

    private final AtomicInteger maxTries;

    public RetryTestRule() {
        this(3);
    }

    public RetryTestRule(int maxTries){
        super();
        checkMaxTries(maxTries);
        this.maxTries = new AtomicInteger(maxTries);
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable caughtThrowable = null;

                while (maxTries.getAndDecrement() > 0) {
                    try {
                        base.evaluate();
                        return;
                    } catch (Throwable t) {
                        if (maxTries.get() > 0 &&
                                description.getAnnotation(Retry.class)!= null) {
                            caughtThrowable = t;
                            System.err.println(
                                    description.getDisplayName() +
                                            ": Failed, " +
                                            maxTries.toString() +
                                            "retries remain");
                        } else {
                            throw caughtThrowable;
                        }
                    }
                }
            }
        };
    }
}
