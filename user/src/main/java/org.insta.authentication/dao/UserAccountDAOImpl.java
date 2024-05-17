package org.insta.authentication.dao;

import org.insta.authentication.exception.DatabaseOperationFailed;
import org.insta.authentication.exception.ProfileCreationFailedException;
import org.insta.authentication.exception.ProfileDeleteFailedException;
import org.insta.authentication.exception.ProfileRetrivalFailedException;
import org.insta.authentication.exception.ProfileUpdateFailedException;
import org.insta.authentication.exception.UserNotFoundException;
import org.insta.authentication.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.authentication.querystructureinjector.account.AccountSqlInjector;
import org.insta.authentication.querystructureinjector.address.AddressSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Manage user accounts.
 * </p>
 *
 * <p>
 * This class implements the {@link UserAccountDAO} interface to provide functionality for managing user accounts, including creating, retrieving, updating, and deleting user profiles.
 * </p>
 *
 * <p>
 * It interacts with the database to perform operations related to user accounts and handles exceptions that may occur during these operations.
 * </p>
 *
 * <p>
 * The methods provided by this class allow for creating user profiles, updating profile information, retrieving profiles by ID, and deleting profiles.
 * </p>
 *
 * <p>
 * This class also contains methods to check for existing user credentials such as name, mobile number, and email address to avoid duplication.
 * </p>
 *
 * <p>
 * Singleton pattern is used to ensure that only one instance of this class is created throughout the application.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see UserAccountDAO
 * @see User
 * @see DatabaseConnection
 * @see ProfileCreationFailedException
 * @see ProfileUpdateFailedException
 * @see ProfileRetrivalFailedException
 * @see ProfileDeleteFailedException
 */
public final class UserAccountDAOImpl implements UserAccountDAO {

    private static final Logger LOGGER = LogManager.getLogger(UserAccountDAOImpl.class);
    private final Connection connection;
    private final AccountSqlInjector accountSqlInjector;
    private final AddressSqlInjector addressSqlInjector;

    /**
     * <p>
     * Prevent instantiation from outside the class.
     * </p>
     */
    private UserAccountDAOImpl() {
        connection = DatabaseConnection.get();
        addressSqlInjector = AddressSqlInjector.getInstance();
        accountSqlInjector = AccountSqlInjector.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of UserAccountDAOImpl.
     * </p>
     *
     * @return Singleton instance of UserAccountDAOImpl.
     */
    public static UserAccountDAO getInstance() {
        return InstanceHolder.userAccountDAOImpl;
    }

    /**
     * <p>
     * Creates a user profile.
     * </p>
     *
     * @param user The {@link User} object containing the user data.
     * @return The ID of the created user profile.
     * @throws DatabaseOperationFailed If the profile creation operation fails due to a database error.
     */
    public Long createProfile(final User user) {

        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(accountSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            if (preparedStatement.executeUpdate() > 0) {
                setUserId(preparedStatement, user);
                connection.commit();
                return createAddress(user);
            }

            return null;
        } catch (SQLException exception) {
            connectionRollback(connection);
            LOGGER.error("Account creation failed");
            throw new DatabaseOperationFailed("Account creation failed");
        }
    }

    /**
     * <p>
     * Creates a user address.
     * </p>
     *
     * @param user The {@link User} object containing the user data.
     * @return The ID of the created user address.
     */
    private Long createAddress(final User user) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                addressSqlInjector.getCreateQuery())) {

            preparedStatement.setInt(1, user.getAddress().getDoorNumber());
            preparedStatement.setString(2, user.getAddress().getState());
            preparedStatement.setLong(3, user.getUserId());

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
            }
            return user.getUserId();
        } catch (Exception ignored) {
            LOGGER.error("Address creation failed");
            throw new ProfileCreationFailedException("Address creation failed");
        }
    }

    /**
     * <p>
     * Updates a user profile.
     * </p>
     *
     * @param receivedObject The {@link User} object containing the updated user data.
     * @return True if the user profile is successfully updated, otherwise false.
     * @throws ProfileUpdateFailedException If the profile update operation fails.
     */
    public boolean updateProfile(final User receivedObject) {
        if (receivedObject.getUserId() <= 0) return false;

        final User tableObject = getProfile(receivedObject.getUserId());
        final User updatedObject = createUpdatedObject(receivedObject, tableObject);

        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                accountSqlInjector.getUpdateQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setString(1, updatedObject.getName());
            preparedStatement.setString(2, updatedObject.getMobileNumber());
            preparedStatement.setString(3, updatedObject.getEmail());
            preparedStatement.setString(4, updatedObject.getPassword());
            preparedStatement.setLong(5, updatedObject.getUserId());

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException sqlException) {
            LOGGER.error("Profile creation failed");
            throw new ProfileUpdateFailedException("Profile creation failed");
        }
        return false;
    }

    /**
     * <p>
     * Updates a user profile.
     * </p>
     *
     * @param receivedObject The {@link User} object containing the updated user data.
     * @param tableObject    Refers the current record in the storable.
     * @return True if the user profile is successfully updated, otherwise false.
     * @throws ProfileUpdateFailedException If the profile update operation fails.
     */
    public User createUpdatedObject(final User receivedObject, final User tableObject) {
        if (!Objects.isNull(receivedObject.getName())) tableObject.setName(receivedObject.getName());
        if (!Objects.isNull(receivedObject.getEmail())) tableObject.setEmail(receivedObject.getEmail());
        if (!Objects.isNull(receivedObject.getMobileNumber()))
            tableObject.setMobileNumber(receivedObject.getMobileNumber());
        if (!Objects.isNull(receivedObject.getPassword())) tableObject.setPassword(receivedObject.getPassword());

        return receivedObject;
    }

    /**
     * <p>
     * Retrieves a user profile by ID.
     * </p>
     *
     * @param id The ID of the user profile to retrieve.
     * @return The user profile if found, null otherwise.
     * @throws ProfileRetrivalFailedException If the profile retrieval operation fails.
     */
    public User getProfile(final Long id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                accountSqlInjector.getRetrieveQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, id);

            final ResultSet resultSet = preparedStatement.executeQuery();

            return setUser(resultSet);
        } catch (SQLException exception) {
            LOGGER.error("Profile retrival failed");
            throw new ProfileRetrivalFailedException("Profile retrival failed");
        }
    }

    /**
     * <p>
     * Deletes a user profile by ID.
     * </p>
     *
     * @param id The ID of the user profile to delete.
     * @return True if the user profile is successfully deleted, otherwise false.
     * @throws ProfileDeleteFailedException If the profile
     */
    public Boolean deleteProfile(final Long id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                String.join("", addressSqlInjector.getDeleteQuery(), ";",
                        accountSqlInjector.getDeleteQuery()))) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, id);

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }

            return false;
        } catch (SQLException sqlException) {
            connectionRollback(connection);
            LOGGER.error("Profile deletion failed");
            throw new ProfileDeleteFailedException("Profile deletion failed");
        }
    }

    /**
     * <p>
     * Checks if the given name is already registered in the database.
     * </p>
     *
     * @param name The name to check.
     * @return {@code true} if the name is already registered, {@code false} otherwise.
     * @throws ProfileCreationFailedException If an error occurs during the database operation.
     */
    private boolean checkNameRegistered(final String name) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                accountSqlInjector.getSelectForName(), Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, name);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {

                return resultSet.next();
            }
        } catch (SQLException sqlException) {
            LOGGER.error("Profile Creation failed");
            throw new ProfileCreationFailedException("Profile Creation failed");
        }
    }

    /**
     * <p>
     * Checks if the given mobile number is already registered in the database.
     * </p>
     *
     * @param mobile The mobile number to check.
     * @return {@code true} if the mobile number is already registered, {@code false} otherwise.
     * @throws ProfileCreationFailedException If an error occurs during the database operation.
     */
    private boolean checkMobileRegistered(final String mobile) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                accountSqlInjector.getSelectForMobile(), Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, mobile);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {

                return resultSet.next();
            }
        } catch (SQLException sqlException) {
            LOGGER.error("Profile creation failed");
            throw new ProfileCreationFailedException("Profile creation failed");
        }
    }

    /**
     * <p>
     * Checks if the given email address is already registered in the database.
     * </p>
     *
     * @param email The email address to check.
     * @return {@code true} if the email address is already registered, {@code false} otherwise.
     * @throws ProfileCreationFailedException If an error occurs during the database operation.
     */
    private boolean checkEmailRegistered(final String email) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                accountSqlInjector.getSelectForEmail(), Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, email);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {

                return resultSet.next();
            }
        } catch (SQLException sqlException) {
            LOGGER.error("Profile creation failed");
            throw new ProfileCreationFailedException("Profile creation failed");
        }
    }

    /**
     * <p>
     * Generates a list of invalid credentials based on the provided user object.
     * </p>
     *
     * @param user The user object containing the credentials to check.
     * @return A list of strings representing invalid credentials.
     */
    public List<String> getCredentialsInvalidList(final User user) {
        final List<String> credentialsInvalidList = new ArrayList<>();

        if (checkNameRegistered(user.getName())) credentialsInvalidList.add("Name already registered");
        if (checkMobileRegistered(user.getMobileNumber())) credentialsInvalidList.add("Mobile already registered");
        if (checkEmailRegistered(user.getEmail())) credentialsInvalidList.add("Email already registered");

        return credentialsInvalidList;
    }

    /**
     * <p>
     * Rolls back the database connection in case of a failed account creation.
     * </p>
     *
     * @param connection The database connection to roll back.
     * @throws ProfileCreationFailedException If rolling back the connection fails.
     */
    private void connectionRollback(final Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException sqlException) {
            LOGGER.error("Roll back failed at account creation");
            throw new ProfileCreationFailedException("Roll back failed at account creation");
        }
    }

    /**
     * <p>
     * Sets the user ID based on the generated keys in the PreparedStatement.
     * </p>
     *
     * @param preparedStatement The PreparedStatement containing the generated keys.
     * @param user              {@link User} The User object to set the user ID.
     * @return The generated user ID if set successfully, otherwise 0.
     */
    public Long setUserId(final PreparedStatement preparedStatement, final User user) {
        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                user.setUserId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setMobileNumber(resultSet.getString("mobile"));
                user.setEmail(resultSet.getString("email"));

                return user.getUserId();
            }

            return user.getUserId();
        } catch (SQLException ignored) {
            LOGGER.error("profile creation failed");
            throw new ProfileCreationFailedException("profile creation failed");
        }
    }

    /**
     * <p>
     * Sets the user details based on the ResultSet.
     * </p>
     *
     * @param resultSet The ResultSet containing the generated data.
     * @return The User object with details set from the ResultSet.
     */
    public User setUser(final ResultSet resultSet) {
        User user = null;

        try {
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setMobileNumber(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.getAddress().setDoorNumber(resultSet.getInt(7));
                user.getAddress().setState(resultSet.getString(8));
                user.getAddress().setStreetName(resultSet.getString(10));
                return user;
            }
        } catch (final SQLException exception) {
            LOGGER.error("User not found");
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    /**
     * <p>
     * Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final UserAccountDAO userAccountDAOImpl = new UserAccountDAOImpl();
    }
}
