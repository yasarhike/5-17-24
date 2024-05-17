package org.insta.content;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * The Activator class for the CONTENT bundle.
 * </p>
 *
 * <p>
 * This class implements the BundleActivator interface to start and stop the bundle.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 */
public final class Activator implements BundleActivator {

    private static final Logger LOGGER = LogManager.getLogger(Activator.class);

    /**
     * <p>
     * Called when the bundle is started.
     * </p>
     *
     * @param bundleContext the BundleContext for the bundle
     */
    @Override
    public void start(final BundleContext bundleContext) {
        LOGGER.info("Content bundle started successfully");
    }

    /**
     * <p>
     * Called when the bundle is stopped.
     * </p>
     *
     * @param bundleContext the BundleContext for the bundle
     */
    @Override
    public void stop(final BundleContext bundleContext) {
        LOGGER.info("Content bundle stopped successfully");
    }
}
