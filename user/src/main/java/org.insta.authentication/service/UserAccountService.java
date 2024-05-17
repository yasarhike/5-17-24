package org.insta.authentication.service;

import org.insta.authentication.model.User;

/**
 * <p>
 * Represents the UserService interface for managing user accounts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see UserAccountServiceImplementation
 */
public interface UserAccountService {

    /**
     * <p>
     * Creates a new user profile.
     * </p>
     *
     * @param user The user object containing the user data to be created.
     * @return A byte array representing the created user profile, or null if creation failed.
     */
    byte[] createProfile(final User user);

    /**
     * Retrieves a user profile based on the provided user ID.
     *
     * @param profileId The ID of the user profile to retrieve.
     * @return A byte array representing the retrieved user profile, or null if no profile found.
     */
    byte[] getProfile(final Long profileId);

    /**
     * <p>
     * Updates an existing user profile.
     * </p>
     *
     * @param user The user object containing the updated user data.
     * @return A byte array representing the updated user profile, or null if update failed.
     */
    byte[] updateProfile(final User user);

    /**
     * <p>
     * Deletes a user profile based on the provided user ID.
     * </p>
     *
     * @param profileId The ID of the user profile to delete.
     * @return A byte array representing the deleted user profile, or null if update failed.
     */
    byte[] deleteProfile(final Long profileId);
}
