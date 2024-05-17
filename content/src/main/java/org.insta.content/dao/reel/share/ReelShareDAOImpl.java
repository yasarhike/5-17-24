package org.insta.content.dao.reel.share;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.exception.post.postshare.PostUnshareFailedException;
import org.insta.content.exception.reel.reelshare.ReelShareFailedException;
import org.insta.content.dao.IdSetter;
import org.insta.content.sqlinjector.reel.share.ReelShareSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user reel share.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class ReelShareDAOImpl implements ReelShareDAO {

    private static final Logger LOGGER = LogManager.getLogger(ReelShareDAOImpl.class);
    private final Connection connection;
    private final IdSetter idSetter;
    private final ReelShareSqlInjector reelShareSqlInjector;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelShareDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        reelShareSqlInjector = ReelShareSqlInjector.getInstance();
    }


    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelShareDAO reelShareDAOImpl = new ReelShareDAOImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelShareDAO class.
     * </p>
     *
     * @return The singleton instance of ReelShareDAO class.
     */
    public static ReelShareDAO getInstance() {
        return InstanceHolder.reelShareDAOImpl;
    }

    /**
     * <p>
     * Adds a user who shared the reel.
     * </p>
     *
     * @param userId The ID of the user who shared the reel.
     * @param reelId The ID of the reel being shared.
     * @return The ID of the share record if the user is successfully added as a sharer, otherwise 0.
     */
    public Long reelShare(final Long userId, final Long reelId) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(reelShareSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, reelId);
            preparedStatement.setLong(2, userId);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.getId(preparedStatement);
            }

            return null;
        } catch (final SQLException exception) {
            LOGGER.error("Reel share failed exception");
            throw new ReelShareFailedException("Reel share failed exception");
        }
    }

    /**
     * <p>
     * Removes a user who unshared the reel.
     * </p>
     *
     * @param id The ID of the share record to be removed.
     * @return True if the user is successfully removed as a sharer, otherwise false.
     */
    public boolean removeShare(final Long id) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(reelShareSqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException exception) {
            LOGGER.error("Reel unshare failed exception");
            throw new PostUnshareFailedException("Reel unshare failed exception");
        }
    }
}
