package org.insta.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.server.serverfactory.ServerFactory;
import org.insta.server.serverfactory.ServerFactoryImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.apache.cxf.endpoint.Server;

import java.util.Objects;

/**
 * <p>
 * Bundle activator class for starting and stopping the server within an OSGi environment.
 * </p>
 *
 * <p>
 * The activator initializes the server using a {@link ServerFactory} implementation and starts it
 * when the bundle is started. It stops the server when the bundle is stopped.
 * </p>
 *
 * @see Server
 * @see ServerFactory
 * @see ServerFactoryImpl
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class Activator implements BundleActivator {

    private static final Logger LOGGER = LogManager.getLogger(Activator.class);

    private Server server;
    private final ServerFactory serverFactoryImpl;

    /**
     * <p>
     * Constructs an Activator instance and initializes the server factory implementation.
     * </P>
     */
    public Activator() {
        serverFactoryImpl = ServerFactoryImpl.getInstance();
    }

    /**
     * <p>
     * Starts the server when the bundle is started.
     * </p>
     *
     * @param context {@link BundleContext} The context of the bundle.
     */
    @Override
    public void start(final BundleContext context) {
        if (Objects.isNull(server)) server = serverFactoryImpl.addServiceClass().create();
       LOGGER.info("Server bundle started successfully");
    }

    /**
     * <p>
     * Stops the server when the bundle is stopped.
     * </p>
     *
     * @param context {@link BundleContext} The context of the bundle.
     */
    @Override
    public void stop(final BundleContext context) {
        if (Objects.nonNull(server)) server.destroy();
        LOGGER.info("Server bundle stopped successfully");
    }
}
