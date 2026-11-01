package com.oleynik.qa.workshop.junit.topics.extensions;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Lets Jupiter find this extension via ServiceLoader, without {@code @ExtendWith} on test classes.
 * The provider name is listed in {@code META-INF/services/org.junit.jupiter.api.extension.Extension}.
 *
 * <p>When autodetection is enabled, {@code beforeAll()} runs before each test class.
 * The first class creates a resource in the root {@link ExtensionContext} store;
 * subsequent classes in the same Jupiter engine execution reuse it.
 * JUnit calls {@code close()} when that root context closes. A separate execution has a new root context.
 *
 * <p>The {@code autodetect-extension-demo} Maven profile and {@code autodetectExtensionDemoTest} Gradle task
 * enable autodetection and select only the demo classes. Enabling it for a broader test selection
 * would register this extension for those other Jupiter classes too.
 *
 * @see <a href="https://docs.junit.org/current/extensions/registering-extensions.html#automatic-extension-registration">Automatic extension registration</a>
 * @see <a href="https://docs.junit.org/current/extensions/keeping-state-in-extensions.html">Extension stores and resource cleanup</a>
 */
public class AutoDetectedRootResourceExtension implements BeforeAllCallback {

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(AutoDetectedRootResourceExtension.class);

    @Override
    @SuppressWarnings("resource") // JUnit closes the stored resource when its root context closes.
    public void beforeAll(ExtensionContext context) {
        context.getRoot()
                .getStore(NAMESPACE)
                .getOrComputeIfAbsent("autodetected-root-resource", key -> {
                    System.out.println("Created shared resource in the Jupiter root context");
                    return new RootResource();
                }, RootResource.class);
    }

    static class RootResource implements AutoCloseable {
        @Override
        public void close() {
            System.out.println("Closed shared resource from the Jupiter root context");
        }
    }
}
