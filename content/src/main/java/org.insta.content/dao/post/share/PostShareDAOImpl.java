package org.insta.content.dao.post.share;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.exception.post.postshare.PostShareFailedException;
import org.insta.content.exception.post.postshare.PostUnshareFailedException;
import org.insta.content.dao.IdSetter;
import org.insta.content.sqlinjector.post.share.PostShareSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Data Access Object interface for managing post shares.
 * </p>
 *
 * <p>
 * This interface provides methods for sharing and unsharing posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostShareDAO
 */
public final class PostShareDAOImpl implements PostShareDAO {

    private static final Logger LOGGER = LogManager.getLogger(PostShareDAOImpl.class);
    private final PostShareSqlInjector postShareSqlInjector;
    private final Connection connection;
    private final IdSetter idSetter;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostShareDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        postShareSqlInjector = PostShareSqlInjector.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostShareDAO postShareDAOImpl = new PostShareDAOImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of PostShareDAOImpl class.
     * </p>
     *
     * @return The singleton instance of PostShareDAOImpl class.
     */
    public static PostShareDAO getInstance() {
        return InstanceHolder.postShareDAOImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param postId the ID of the post to be shared
     * @param userId the ID of the user sharing the post
     * @return the ID of the added share, or 0 if unsuccessful
     */
    public Long postShare(final Long postId, final Long userId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                postShareSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, postId);
            preparedStatement.setLong(2, userId);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.getId(preparedStatement);
            }

            return null;
        } catch (SQLException exception) {
            LOGGER.debug("Post shared failed");
            throw new PostShareFailedException("Post shared failed");
        }
    }

    /**
     *{@inheritDoc}
     *
     * @param shareId the ID of the share to be removed
     * @return true if the share is removed successfully, otherwise false
     */
    public boolean removeShare(final Long shareId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
               postShareSqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, shareId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            LOGGER.error("Post unshare failed");
            throw new PostUnshareFailedException("Post unshare failed");
        }
    }
}
