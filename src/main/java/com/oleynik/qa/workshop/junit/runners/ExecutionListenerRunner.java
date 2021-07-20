package com.oleynik.qa.workshop.junit.runners;

import com.oleynik.qa.workshop.junit.listeners.ExecutionListener;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class ExecutionListenerRunner extends BlockJUnit4ClassRunner {

    public ExecutionListenerRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    public static RunListener customListener = new ExecutionListener();

    @Override public void run(RunNotifier notifier){
        notifier.addListener(customListener);
        notifier.fireTestRunStarted(getDescription());
        super.run(notifier);
    }
}
