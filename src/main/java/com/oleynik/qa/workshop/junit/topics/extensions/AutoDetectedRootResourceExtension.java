package com.oleynik.qa.workshop.junit.topics.extensions;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Registered automatically via {@code META-INF/services/org.junit.jupiter.api.extension.Extension} ServiceLoader
 * entry — no {@code @ExtendWith} is applied on any test class. Autodetection is opt-in and only enabled for the
 * {@code autodetect-extension-demo} Maven profile / {@code autodetectExtensionDemoTest} Gradle task, because once
 * enabled it applies to every Jupiter test class discovered in the same run, not just the demo classes.
 */
public class AutoDetectedRootResourceExtension implements BeforeAllCallback {

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(AutoDetectedRootResourceExtension.class);

    @Override
    @SuppressWarnings("resource") // RootResource lifecycle is managed by JUnit's store — close() is called by the root context at end-of-run
    public void beforeAll(ExtensionContext context) {
        context.getRoot()
                .getStore(NAMESPACE)
                .getOrComputeIfAbsent("autodetected-root-resource", key -> {
                    System.out.println("Before entire run via autodetected extension");
                    return new RootResource();
                }, RootResource.class);
    }

    static class RootResource implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("After entire run via autodetected extension");
        }
    }
}
