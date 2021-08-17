package com.oleynik.qa.workshop.junit.extensions;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestWatcherExtension implements TestWatcher, AfterAllCallback {
    private List<TestResultStatus> testResultsStatuses = new ArrayList<>();

    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.printf("Test %s is Aborted by cause %s%n",
                context.getTestMethod().get().getName(),
                cause.getMessage());
        testResultsStatuses.add(TestResultStatus.ABORTED);
    }

    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.printf("Test %s is Disabled with reason : %s%n",
                context.getTestMethod().get().getName(),
                reason.orElse("No reason"));
        testResultsStatuses.add(TestResultStatus.DISABLED);
    }

    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.printf("Test %s Failed by cause %s%n",
                context.getTestMethod().get().getName(),
                cause.getMessage());
        testResultsStatuses.add(TestResultStatus.FAILED);
    }

    public void testSuccessful(ExtensionContext context) {
        System.out.printf("Test %s is Successful%n",
                context.getTestMethod().get().getName());
        testResultsStatuses.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Map<TestResultStatus, Long> summary = testResultsStatuses.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.printf("Test result summary for %s: %s%n",
                context.getTestClass().get().getName(),
                summary.toString());
    }
}
