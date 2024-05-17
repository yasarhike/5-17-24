package org.insta.content.dao.reel.comment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.exception.reel.reelcomment.ReelCommentFailedException;
import org.insta.content.exception.reel.reelcomment.ReelCommentRemovalFailedException;
import org.insta.content.model.Comment;
import org.insta.content.dao.IdSetter;
import org.insta.content.sqlinjector.reel.comment.ReelCommentSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user reel comment.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelCommentDAO
 */
public final class ReelCommentDAOImpl implements ReelCommentDAO {

    private static final Logger LOGGER = LogManager.getLogger(ReelCommentDAOImpl.class);
    private static ReelCommentDAOImpl reelCommentDAOImpl;
    private final Connection connection;
    private final IdSetter idSetter;
    private final ReelCommentSqlInjector reelCommentSqlInjector;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelCommentDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        reelCommentSqlInjector = ReelCommentSqlInjector.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelCommentDAO reelCommentDAOImpl = new ReelCommentDAOImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelCommentDAO class.
     * </p>
     *
     * @return The singleton instance of ReelCommentDAO class.
     */
    public static ReelCommentDAO getInstance() {
        return InstanceHolder.reelCommentDAOImpl;
    }

    /**
     * <p>
     * Adds a comment for a particular reel.
     * </p>
     *
     * @param comment The comment to be added
     * @return The ID of the added comment, or 0 if unsuccessful
     */
    public Long addComment(final Comment comment) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                reelCommentSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, comment.getContentId());
            preparedStatement.setLong(2, comment.getUserId());
            preparedStatement.setString(3, comment.getComment());

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.getId(preparedStatement, comment);
            }

            return null;
        } catch (final SQLException exception) {
            LOGGER.error("Reel comment failed");
            throw new ReelCommentFailedException("Reel comment failed");
        }
    }

    /**
     * <p>
     * Deletes a comment for a particular reel.
     * </p>
     *
     * @param commentId The ID of the comment to be deleted
     * @return true if the comment is deleted successfully, otherwise false
     */
    public boolean deleteComment(final Long commentId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                reelCommentSqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, commentId);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException exception) {
            LOGGER.error("Reel comment removal failed");
            throw new ReelCommentRemovalFailedException("Reel comment removal failed");
        }
    }
}
