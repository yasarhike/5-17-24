package org.insta.content.dao.story.like;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.content.exception.story.storylike.StoryLikeCreationFailedException;
import org.insta.content.exception.story.storylike.StoryLikeRemovalFailedException;
import org.insta.content.dao.IdSetter;
import org.insta.content.sqlinjector.story.like.StoryLikeSqlInjector;
import org.insta.databaseconnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <p>
 * Managing user story like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryLikeDAO
 */
public final class StoryLikeDAOImpl implements StoryLikeDAO {

    private static final Logger LOGGER = LogManager.getLogger(StoryLikeDAOImpl.class);
    private final Connection connection;
    private final IdSetter idSetter;
    private final StoryLikeSqlInjector storyLikeSqlInjector;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private StoryLikeDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        storyLikeSqlInjector = StoryLikeSqlInjector.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final StoryLikeDAO storyLikeDAOImpl = new StoryLikeDAOImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryLikeDAO class.
     * </p>
     *
     * @return The singleton instance of StoryLikeDAO class.
     */
    public static StoryLikeDAO getInstance() {
        return InstanceHolder.storyLikeDAOImpl;
    }

    /**
     * <p>
     * Adds a like for the specified story by the user.
     * </p>
     *
     * @param userId  The ID of the user who likes the story.
     * @param storyId The ID of the story to be liked.
     * @return The ID of the inserted like if successful, otherwise 0.
     */
    public Long storyLike(final Long userId, final Long storyId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                storyLikeSqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(true);
            preparedStatement.setLong(1, storyId);
            preparedStatement.setLong(2, userId);

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.getId(preparedStatement);
            }

            return null;
        } catch (final SQLException exception) {
            LOGGER.error("story like creation failed");
            throw new StoryLikeCreationFailedException("story like creation failed");
        }
    }

    /**
     * <p>
     * Removes a like for the specified story.
     * </p>
     *
     * @param storyId The ID of the story like to be removed.
     * @return True if the like is removed successfully, otherwise false.
     */
    public boolean storyUnlike(final Long storyId) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                storyLikeSqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, storyId);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException exception) {
            LOGGER.error("Story like removal failed");
            throw new StoryLikeRemovalFailedException("Story like removal failed");
        }
    }
}
