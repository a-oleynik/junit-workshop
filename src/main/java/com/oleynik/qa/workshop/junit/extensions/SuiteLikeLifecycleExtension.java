package com.oleynik.qa.workshop.junit.extensions;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SuiteLikeLifecycleExtension implements BeforeAllCallback {

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(SuiteLikeLifecycleExtension.class);

    @Override
    @SuppressWarnings("resource") // SuiteCleanupResource lifecycle is managed by JUnit's store — close() is called by the root context at end-of-run
    public void beforeAll(ExtensionContext context) {
        context.getRoot()
                .getStore(NAMESPACE)
                .computeIfAbsent("suite-like-resource", key -> {
                    System.out.println("Before entire run via extension");
                    return new SuiteCleanupResource();
                }, SuiteCleanupResource.class);
    }

    static class SuiteCleanupResource implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("After entire run via extension");
        }
    }
}
