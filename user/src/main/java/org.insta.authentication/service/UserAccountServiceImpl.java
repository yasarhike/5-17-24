package org.insta.authentication.service;

import org.insta.authentication.dao.UserAccountDAO;
import org.insta.authentication.dao.UserAccountDAOImpl;
import org.insta.authentication.groups.UserCredentialsValidator;
import org.insta.authentication.model.User;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import java.util.List;

/**
 * <p>
 * Implementation of the UserService interface for user account service.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ObjectValidator
 * @see UserAccountDAOImpl
 */
public final class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountDAO userAccountDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Private constructor to restrict object creation outside the class
     * </p>
     */
    private UserAccountServiceImpl() {
        userAccountDAO = UserAccountDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     * Creates a singleton instance of UserAccountServiceImplementation class if it is not already created.
     * </p>
     *
     * @return the singleton instance of UserAccountServiceImplementation class.
     */
    public static UserAccountService getInstance() {
        return InstanceHolder.userAccountServiceImplementation;
    }

    /**
     * {@inheritDoc}
     *
     * @param user The user object containing the user data to be created.
     * @return A byte array representing the created user profile, or null if creation failed.
     */
    public byte[] createProfile(final User user) {
        final byte[] violations = objectValidator.validate(user, UserCredentialsValidator.class);

        if (violations.length > 0) {
            return violations;
        }
        final List<String> invalidCredentials = userAccountDAO.getCredentialsInvalidList(user);

        return !invalidCredentials.isEmpty() ? objectValidator.objectResponse(invalidCredentials) :
                objectValidator.responseWithID(userAccountDAO.createProfile(user), violations);
    }

    /**
     * {@inheritDoc}
     *
     * @param id The ID of the user profile to retrieve.
     * @return A byte array representing the retrieved user profile, or null if no profile found.
     */
    public byte[] getProfile(final Long id) {
        return objectValidator.objectResponse(userAccountDAO.getProfile(id));
    }

    /**
     * {@inheritDoc}
     *
     * @param user The user object containing the updated user data.
     * @return A byte array representing the updated user profile, or null if update failed.
     */
    public byte[] updateProfile(final User user) {
        final byte[] violations = objectValidator.validate(user, UserCredentialsValidator.class);

        if (violations.length > 0) {
            return violations;
        }

        return objectValidator.manualResponse(userAccountDAO.updateProfile(user));
    }

    /**
     * {@inheritDoc}
     *
     * @param id The ID of the user profile to delete.
     * @return A byte array representing the deleted user profile, or null if update failed.
     */
    public byte[] deleteProfile(final Long id) {
        return objectValidator.manualResponse(userAccountDAO.deleteProfile(id));
    }

    /**
     * <p>
     * Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final UserAccountService userAccountServiceImplementation = new UserAccountServiceImpl();
    }
}

