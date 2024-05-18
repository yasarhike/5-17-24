package org.insta.authentication.controller;

import org.insta.authentication.model.User;
import org.insta.authentication.service.UserAccountService;
import org.insta.authentication.service.UserAccountServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>
 * Managing user accounts provides REST endpoints to create, retrieve, update, and delete user profiles.
 * </p>
 *
 * <p>
 * This class represents a RESTful controller for managing user accounts. It exposes endpoints for handling
 * operations such as creating new user profiles, retrieving user profiles by ID, updating user profiles,
 * and deleting user profiles. The actual implementation of these operations is delegated to the
 * {@link UserAccountServiceImpl} class.
 * </p>
 *
 * <p>
 * This class is annotated with JAX-RS annotations to specify the path and HTTP methods for each endpoint,
 * as well as the input and output media types.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see UserAccountServiceImpl
 */
@Path("/authentication")
public final class UserAccountControllerRest {

    private final UserAccountService userAccountServiceImplementation;

    /**
     * Restrict object creation outside the class.
     */
    private UserAccountControllerRest() {
        userAccountServiceImplementation = UserAccountServiceImpl.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final UserAccountControllerRest userAccountControllerRest = new UserAccountControllerRest();
    }

    /**
     * <p>
     * Returns the singleton instance of UserAccountControllerRest class.
     * </p>
     *
     * @return Singleton instance of UseAccountControllerRest class.
     */
    public static UserAccountControllerRest getInstance() {
        return InstanceHolder.userAccountControllerRest;
    }

    /**
     * <p>
     * Create a new user profile.
     * </p>
     *
     * @param user The {@link User} object representing the user profile to be created.
     * @return The profile data as a byte array if the profile is successfully created, otherwise null.
     */
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public byte[] createUser(final User user) {
        return userAccountServiceImplementation.createProfile(user);
    }

    /**
     * <p>
     * Deletes the user profile associated with the given ID.
     * </p>
     *
     * @param id The unique identifier of the user profile to be deleted.
     * @return The profile data as a byte array if the profile is successfully deleted, otherwise null.
     */
    @DELETE
    @Path("/remove/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public byte[] deleteProfile(@PathParam("id") final Long id) {
        return userAccountServiceImplementation.deleteProfile(id);
    }

    /**
     * <p>
     * Updates the user profile with the provided information.
     * </p>
     *
     * @param user The {@link User} object containing updated profile information.
     * @return The validated user profile data as a byte array if the profile is successfully updated, otherwise null.
     */
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public byte[] updateDetails(final User user) {
        return userAccountServiceImplementation.updateProfile(user);
    }

    /**
     * <p>
     * Retrieves the user profile based on the provided ID.
     * </p>
     *
     * @param id The unique identifier of the user profile.
     * @return The profile data as a byte array if found, otherwise null.
     */
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public byte[] getProfile(@PathParam("id") final Long id) {
        return userAccountServiceImplementation.getProfile(id);
    }
}
