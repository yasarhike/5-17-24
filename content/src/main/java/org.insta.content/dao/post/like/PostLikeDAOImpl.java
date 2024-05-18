package org.insta.content.dao.post.like;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.exception.post.postlike.PostLikeFailedException;
import org.insta.content.exception.post.postlike.PostUnlikeFailedException;
import org.insta.content.dao.IdSetter;
import org.insta.content.sqlinjector.post.like.PostLikeSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user post like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostLikeDAOImpl implements PostLikeDAO {

    private static final Logger LOGGER = LogManager.getLogger(PostLikeDAOImpl.class);
    private final IdSetter idSetter;
    private final Connection connection;
    private final PostLikeSqlInjector postLikeSqlInjector;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostLikeDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        postLikeSqlInjector = PostLikeSqlInjector.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostLikeDAO postLikeDAOImpl = new PostLikeDAOImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of PostLikeDAO class.
     * </p>
     *
     * @return The singleton instance of POstLikeDAO class.
     */
    public static PostLikeDAO getInstance() {
        return InstanceHolder.postLikeDAOImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param postId Refers the postId for the user.
     * @return True if the like is added successfully, otherwise false.
     */
    public Long postLike(final Long userId, final Long postId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                postLikeSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, postId);
            preparedStatement.setLong(2, userId);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.getId(preparedStatement);
            }

            return null;
        } catch (SQLException exception) {
            LOGGER.error("Post like failed");
            throw new PostLikeFailedException("Post like failed");
        }
    }

    /**
     * <p>
     * Unlike a particular post
     * </p>
     *
     * @param postId Refers the postId for the post.
     * @return True if the like is added successfully, otherwise false.
     */
    public boolean postUnlike(final Long postId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                postLikeSqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, postId);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            LOGGER.error("Post unlike failed");
            throw new PostUnlikeFailedException("Post unlike failed");
        }
    }
}
