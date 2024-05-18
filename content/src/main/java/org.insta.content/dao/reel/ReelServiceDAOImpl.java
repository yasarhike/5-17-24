package org.insta.content.dao.reel;

import org.insta.content.dao.IdSetter;
import org.insta.content.exception.reel.ReelCreationFailedException;
import org.insta.content.exception.reel.ReelException;
import org.insta.content.exception.reel.ReelRemovalFailedException;
import org.insta.content.exception.reel.ReelRetrivalFailedException;
import org.insta.content.model.reel.Reel;
import org.insta.content.sqlinjector.reel.ReelSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelServiceDAO
 * @see IdSetter
 * @see DatabaseConnection
 */
public final class ReelServiceDAOImpl implements ReelServiceDAO {

    private static final Logger LOGGER = LogManager.getLogger(ReelServiceDAOImpl.class);
    private final ReelSqlInjector reelSqlInjector;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelServiceDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        reelSqlInjector = ReelSqlInjector.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelServiceDAO reelServiceDAOImpl = new ReelServiceDAOImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelServiceDAOImpl class.
     * </p>
     *
     * @return The singleton instance of ReelServiceDAOImpl class.
     */
    public static ReelServiceDAO getInstance() {
        return InstanceHolder.reelServiceDAOImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param reel The reel to be added.
     * @return The ID of the added reel if successful, otherwise 0.
     */
    @Override
    public Long addReel(final Reel reel) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                reelSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(true);
            preparedStatement.setLong(1, reel.getUserId());
            preparedStatement.setString(2, reel.getCaption());
            preparedStatement.setString(3, reel.getDuration());

            if (preparedStatement.executeUpdate() > 0) {
                reel.setReelId(idSetter.getId(preparedStatement));

                return reel.getReelId();
            }

            return null;
        } catch (SQLException ignored) {
            LOGGER.error("Reel creation failed");
            throw new ReelCreationFailedException("Reel creation failed");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param reelId The ID of the reel to be deleted.
     * @return True if the reel is deleted successfully, otherwise false.
     */
    public boolean removeReel(final Long reelId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                reelSqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, reelId);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException ignored) {
            LOGGER.error("Reel removal failed");
            throw new ReelRemovalFailedException("Reel removal failed");
        }
    }

    /**
     * <p>
     * Sets the reel ID based on the generated keys in the PreparedStatement.
     * </p>
     *
     * @param preparedStatement The PreparedStatement containing the generated keys.
     * @param reel              {@link Reel} belong to a user.
     * @return True if the user ID is successfully set, otherwise false.
     */
    public boolean setReelId(final PreparedStatement preparedStatement, final Reel reel) {
        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

            if (resultSet != null && resultSet.next()) {
                reel.setReelId(resultSet.getLong("id"));

                return true;
            }

            return false;
        } catch (SQLException ignored) {
            LOGGER.error("Resultset insertion in local object failed exception");
            throw new ReelException("Resultset insertion in local object failed exception");
        }
    }

    /**
     * <p>
     * Retrieves a reel based on its ID.
     * </p>
     *
     * @param reelId The ID of the reel to be retrieved.
     * @return The retrieved reel, or null if not found.
     */
    public Reel getReel(final Long reelId) {
        final Reel reel = new Reel();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                reelSqlInjector.getSelectQuery())) {
            preparedStatement.setLong(1, reelId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            return setReelUnique(reel, resultSet);
        } catch (Exception exception) {
            LOGGER.error("Reel retrival failed");
            throw new ReelRetrivalFailedException("Reel retrival failed");
        }
    }

    /**
     * <p>
     * Sets unique properties of a reel based on the retrieved ResultSet.
     * </p>
     *
     * @param reel      The reel object to be populated with unique properties.
     * @param resultSet The ResultSet containing reel data.
     * @return The populated reel object if successful, otherwise null.
     */
    private Reel setReelUnique(final Reel reel, final ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                reel.setReelId(resultSet.getLong(1));
                reel.setUserId(resultSet.getLong(2));
                reel.setUserName(resultSet.getString(3));
                reel.setCaption(resultSet.getString(4));
                reel.setPrivate(resultSet.getBoolean(5));
                reel.setTimestamp(resultSet.getTimestamp(6));
                reel.setDuration(resultSet.getString(7));
            }

            return reel;
        } catch (Exception exception) {
            LOGGER.error("Resultset insertion in object failed exception");
            throw new ReelException("Resultset insertion in object failed exception");
        }
    }
}
