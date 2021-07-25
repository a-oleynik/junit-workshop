package com.oleynik.qa.workshop.junit.rules;

import com.oleynik.qa.workshop.junit.annotations.Retry;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.concurrent.atomic.AtomicInteger;

import static com.oleynik.qa.workshop.junit.Utils.checkMaxTries;

//It is preferred to implement TestRule instead of MethodRule
public class RetryMethodRule implements MethodRule {

    private final AtomicInteger maxTries;

    public RetryMethodRule() {
        this(3);
    }

    public RetryMethodRule(int maxTries) {
        super();
        checkMaxTries(maxTries);
        this.maxTries = new AtomicInteger(maxTries);
    }

    @Override
    public Statement apply(final Statement base, final FrameworkMethod method, Object target) {
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
                                method.getAnnotation(Retry.class) != null) {
                            caughtThrowable = t;
                            System.err.println(
                                    method.getName() +
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
