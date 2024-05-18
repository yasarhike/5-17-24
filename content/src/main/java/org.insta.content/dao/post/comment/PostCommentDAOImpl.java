package org.insta.content.dao.post.comment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.exception.post.postcomment.PostCommentFailedException;
import org.insta.content.exception.post.postcomment.PostUncommentFailedException;
import org.insta.content.model.Comment;
import org.insta.content.dao.IdSetter;
import org.insta.content.sqlinjector.post.comment.PostCommentSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing post comments.
 * </p>
 *
 * <p>
 * This interface provides methods for adding and deleting comments for a post.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostCommentDAO
 */
public final class PostCommentDAOImpl implements PostCommentDAO {

    private static final Logger LOGGER = LogManager.getLogger(PostCommentDAOImpl.class);
    private final Connection connection;
    private final IdSetter idSetter;
    private final PostCommentSqlInjector postCommentSqlInjector;

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private PostCommentDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        postCommentSqlInjector = PostCommentSqlInjector.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostCommentDAO postCommentDAOImpl = new PostCommentDAOImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of PostCommentDAO class.
     * </p>
     *
     * @return The singleton instance of PstCommentDAO class.
     */
    public static PostCommentDAO getInstance() {
        return InstanceHolder.postCommentDAOImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param comment the comment to be added
     * @return the ID of the added comment, or 0 if unsuccessful
     */
    public Long postComment(final Comment comment) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                postCommentSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, comment.getContentId());
            preparedStatement.setLong(2, comment.getUserId());
            preparedStatement.setString(3, comment.getComment());
            preparedStatement.executeUpdate();

            return idSetter.getId(preparedStatement, comment);
        } catch (final SQLException exception) {
            LOGGER.error("Post comment failed");
            throw new PostCommentFailedException("Post comment failed");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param id Refers the commentId for the post.
     * @return True if the comment is deleted successfully, otherwise false.
     */
    public boolean deleteComment(final Long id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                postCommentSqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException exception) {
            LOGGER.error("Post comment removal failed");
            throw new PostUncommentFailedException("Post comment removal failed");
        }
    }
}
