package com.oleynik.qa.workshop.junit.runners;

import com.oleynik.qa.workshop.junit.annotations.RetryConfiguration;
import org.junit.AssumptionViolatedException;
import org.junit.jupiter.api.Disabled;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.util.Arrays;

import static com.oleynik.qa.workshop.junit.Utils.checkMaxTries;

public class RetryRunner extends BlockJUnit4ClassRunner {

    private int retryCount = 3;
    private int failedAttempts = 1;

    public RetryRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(final RunNotifier notifier) {
        EachTestNotifier testNotifier = new EachTestNotifier(notifier,
                getDescription());
        Statement statement = classBlock(notifier);
        try {

            statement.evaluate();
        } catch (AssumptionViolatedException e) {
            testNotifier.fireTestIgnored();
        } catch (StoppedByUserException e) {
            throw e;
        } catch (Throwable e) {
            retry(testNotifier, statement, e);
        }
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        failedAttempts = 1;
        if (Arrays.stream(method.getAnnotations())
                .anyMatch(RetryConfiguration.class::isInstance)) {
            RetryConfiguration annotation = method.getAnnotation(RetryConfiguration.class);
            retryCount = annotation.maxTries();
            checkMaxTries(retryCount);
        } else {
            retryCount = 3;
        }
        Description description = describeChild(method);
        if (method.getAnnotation(Disabled.class) != null) {
            notifier.fireTestIgnored(description);
        } else {
            runTestUnit(methodBlock(method), description, notifier);
        }
    }

    protected final void runTestUnit(Statement statement, Description description,
                                     RunNotifier notifier) {
        EachTestNotifier eachNotifier = new EachTestNotifier(notifier, description);
        eachNotifier.fireTestStarted();
        try {
            statement.evaluate();
        } catch (AssumptionViolatedException e) {
            eachNotifier.addFailedAssumption(e);
        } catch (Throwable e) {
            retry(eachNotifier, statement, e);
        } finally {
            eachNotifier.fireTestFinished();
        }
    }

    public void retry(EachTestNotifier notifier, Statement statement, Throwable currentThrowable) {
        Throwable caughtThrowable = currentThrowable;
        while (retryCount > failedAttempts) {
            try {
                statement.evaluate();
                return;
            } catch (Throwable t) {
                failedAttempts++;
                caughtThrowable = t;
            }
        }
        notifier.addFailure(caughtThrowable);
    }
}
