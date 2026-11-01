package com.oleynik.qa.workshop.junit.topics.extensions;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Shares a resource between classes that register this extension with {@code @ExtendWith}.
 * Each class triggers {@code beforeAll()}, but the root store creates the resource only once
 * in that Jupiter engine execution. JUnit closes the resource when the root context closes.
 *
 * @see <a href="https://docs.junit.org/current/extensions/keeping-state-in-extensions.html">Extension stores and resource cleanup</a>
 */
public class SuiteLikeLifecycleExtension implements BeforeAllCallback {

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(SuiteLikeLifecycleExtension.class);

    @Override
    @SuppressWarnings("resource") // JUnit closes the stored resource when its root context closes.
    public void beforeAll(ExtensionContext context) {
        context.getRoot()
                .getStore(NAMESPACE)
                .getOrComputeIfAbsent("suite-like-resource", key -> {
                    System.out.println("Created shared resource in the Jupiter root context");
                    return new SuiteCleanupResource();
                }, SuiteCleanupResource.class);
    }

    static class SuiteCleanupResource implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("Closed shared resource from the Jupiter root context");
        }
    }
}
