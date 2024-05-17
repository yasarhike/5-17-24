package org.insta.server.serverfactory;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

/**
 * <p>
 * Interface for a factory that creates JAX-RS server instances.
 * Implementations of this interface are responsible for adding service classes
 * to the server instance.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * ServerFactory factory = new ConcreteServerFactory();
 * JAXRSServerFactoryBean serverBean = factory.addServiceClass();
 * }</pre>
 * </p>
 *
 * @see JAXRSServerFactoryBean
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface ServerFactory {

    /**
     * <p>
     * Adds a service class to the JAX-RS server instance.
     * </p>
     *
     * @return {@link JAXRSServerFactoryBean} configured with the added service class.
     */
    JAXRSServerFactoryBean addServiceClass();
}
