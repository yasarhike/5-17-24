package org.insta.content.dao.post;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.exception.post.PostCreationFailedException;
import org.insta.content.exception.post.PostException;
import org.insta.content.exception.post.PostRemovalFailedException;
import org.insta.content.exception.post.PostRetrivalFailedException;
import org.insta.content.dao.IdSetter;
import org.insta.content.sqlinjector.post.PostSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;
import org.insta.content.model.post.Post;
import org.insta.content.model.home.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Implementation of the PostServiceDAO interface for managing user posts.
 * </p>
 *
 * <p>
 * This class provides methods to add, remove, update, and retrieve posts for users.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostServiceDAO
 */
public final class PostServiceDAOImpl implements PostServiceDAO {

    private static final Logger LOGGER = LogManager.getLogger(PostServiceDAOImpl.class);
    private final Connection connection;
    private final IdSetter idSetter;
    private final PostSqlInjector postSqlInjector;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostServiceDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        postSqlInjector = PostSqlInjector.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostServiceDAO postServiceDAOImpl = new PostServiceDAOImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of PostServiceDAOImpl class.
     * </p>
     *
     * @return The singleton instance of PostServiceDAOImpl class.
     */
    public static PostServiceDAO getInstance() {
        return InstanceHolder.postServiceDAOImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param post the post to be added
     * @return the ID of the added post, or 0 if unsuccessful
     */
    public Long addPost(final Post post) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                postSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, post.getUserId());
            preparedStatement.setString(2, post.getCaption());
            preparedStatement.setBoolean(3, post.isPrivate());
            preparedStatement.setInt(4, post.getType().getId());

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.getId(preparedStatement);
            }

            return null;
        } catch (SQLException exception) {
            LOGGER.error("Post creation failed");
            throw new PostCreationFailedException("Post creation failed");
        }
    }

    /**
     *{@inheritDoc}
     *
     * @param id the ID of the post to be removed
     * @return true if the post is removed successfully, otherwise false
     */
    public boolean removePost(final Long id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                postSqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException exception) {
            LOGGER.error("Post removal failed");
            throw new PostRemovalFailedException("Post removal failed");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param postId the ID of the post to be retrieved
     * @return the retrieved post, or null if not found
     */
    public Post getPost(final Long postId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                postSqlInjector.getRetrieveQuery())) {
            preparedStatement.setLong(1, postId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return setReelUnique(resultSet);
            }

            return null;
        } catch (Exception exception) {
            LOGGER.error("Post retrival failed");
            throw new PostRetrivalFailedException("Post retrival failed");
        }
    }

    /**
     * <p>
     * Sets the details of a post retrieved from the database ResultSet.
     * </p>
     *
     * @param resultSet The ResultSet containing post details
     * @return The Post object with retrieved details, or null if no data found
     */
    private Post setReelUnique(final ResultSet resultSet) {
        final Post post = new Post();

        try {
            post.setPostId(resultSet.getLong(1));
            post.setUserId(resultSet.getLong(2));
            post.setUserName(resultSet.getString(3));
            post.setCaption(resultSet.getString(4));
            post.setType(Media.getMedia(resultSet.getInt(5)));
            post.setPrivate(resultSet.getBoolean(6));
            post.setTimestamp(resultSet.getTimestamp(7));

            return post;
        } catch (final SQLException exception) {
            LOGGER.error("Resultset insertion in post object failed");
            throw new PostException("Resultset insertion in post object failed");
        }
    }
}
