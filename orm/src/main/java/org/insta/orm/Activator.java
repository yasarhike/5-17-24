package org.insta.orm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * <p>
 * Bundle activator class for ORM.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class Activator implements BundleActivator {

    private static final Logger LOGGER = LogManager.getLogger(Activator.class);

    /**
     * <p>
     * Called when the bundle is stopped.
     * </p>
     *
     * @param context {@link BundleContext} The context of the bundle.
     */
    @Override
    public void start(final BundleContext context) {
        LOGGER.info("ORM bundle started successfully");
    }

    /**
     * <p>
     * Called when the bundle is stopped.
     * </p>
     *
     * @param context {@link BundleContext} The context of the bundle.
     */
    @Override
    public void stop(final BundleContext context) {
        LOGGER.info("ORM bundle stopped successfully");
    }
}

