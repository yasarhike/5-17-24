package org.insta.authentication.dao;

import org.insta.authentication.model.User;

import java.util.List;

/**
 * <p>
 * Manage user accounts.
 * </p>
 *
 * <p>
 * This interface defines methods for managing user accounts, including creating, retrieving, updating, and deleting user profiles.
 * </p>
 *
 * <p>
 * Implementations of this interface provide the actual logic for interacting with the user account data store.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see User
 */
public interface UserAccountDAO {

    /**
     * <p>
     * Creates a user profile.
     * </p>
     *
     * @param user The {@link User} object containing the user data.
     * @return The ID of the created user profile.
     */
    Long createProfile(final User user);

    /**
     * <p>
     * Retrieves a user profile by ID.
     * </p>
     *
     * @param id The ID of the user profile to retrieve.
     * @return The user profile if found, null otherwise.
     */
    User getProfile(final Long id);

    /**
     * <p>
     * Updates a user profile.
     * </p>
     *
     * @param user The {@link User} object containing the updated user data.
     * @return True if the user profile is successfully updated, otherwise false.
     */
    boolean updateProfile(final User user);

    /**
     * <p>
     * Deletes a user profile by ID.
     * </p>
     *
     * @param id The ID of the user profile to delete.
     * @return True if the user profile is successfully deleted, otherwise false.
     */
    Boolean deleteProfile(final Long id);

    /**
     * <p>
     * Retrieves a list of invalid credentials for the given user.
     * </p>
     *
     * @param user The {@link User} object for which to retrieve the list of invalid credentials.
     * @return The list of invalid credentials.
     */
    List<String> getCredentialsInvalidList(final User user);
}
