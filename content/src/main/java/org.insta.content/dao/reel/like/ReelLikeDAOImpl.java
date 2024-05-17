package org.insta.content.dao.reel.like;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.exception.reel.reellike.ReelLikeCreationFailedException;
import org.insta.content.exception.reel.reellike.ReelLikeRemovalFailedException;
import org.insta.content.dao.IdSetter;
import org.insta.content.sqlinjector.reel.like.ReelLikeSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user reel like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelLikeDAO
 */
public final class ReelLikeDAOImpl implements ReelLikeDAO {

    private static final Logger LOGGER = LogManager.getLogger(ReelLikeDAOImpl.class);
    private final Connection connection;
    private final IdSetter idSetter;
    private final ReelLikeSqlInjector reelLikeSqlInjector;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelLikeDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        reelLikeSqlInjector = ReelLikeSqlInjector.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelLikeDAO reelLikeDAOImpl = new ReelLikeDAOImpl();
    }


    /**
     * <p>
     * Returns the singleton instance of ReelLikeDAO class.
     * </p>
     *
     * @return The singleton instance of ReelLikeDAO class.
     */
    public static ReelLikeDAO getInstance() {
        return InstanceHolder.reelLikeDAOImpl;
    }

    /**
     * <p>
     * Adds a like for a particular reel.
     * </p>
     *
     * @param reelId The ID of the reel to like
     * @param userId The ID of the user performing the like
     * @return The ID of the added like, or 0 if unsuccessful
     */
    public Long reelLike(final Long reelId, final Long userId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                reelLikeSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, reelId);
            preparedStatement.setLong(2, userId);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.getId(preparedStatement);
            }

            return null;
        } catch (final SQLException exception) {
            LOGGER.error("Reel like failed");
            throw new ReelLikeCreationFailedException("Reel like failed");
        }
    }

    /**
     * <p>
     * Unlikes a particular reel.
     * </p>
     *
     * @param id The ID of the reel to unlike
     * @return true if the reel is unliked successfully, otherwise false
     */
    public boolean reelUnlike(final Long id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                reelLikeSqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException exception) {
            LOGGER.error("Reel like removal failed");
            throw new ReelLikeRemovalFailedException("Reel like removal failed");
        }
    }
}
