package org.insta.content.dao.story;

import org.insta.content.exception.story.StoryCreationFailedException;
import org.insta.content.exception.story.StoryException;
import org.insta.content.exception.story.StoryRemovalFailedException;
import org.insta.content.exception.story.StoryRetrivalFailedException;
import org.insta.content.dao.IdSetter;
import org.insta.content.model.story.Story;
import org.insta.content.sqlinjector.story.StorySqlInjector;
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
 * managing story service operation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryServiceDAO
 */
public final class StoryServiceDAOImpl implements StoryServiceDAO {

    private static final Logger LOGGER = LogManager.getLogger(StoryServiceDAOImpl.class);
    private final Connection connection;
    private final IdSetter idSetter;
    private final StorySqlInjector storySqlInjector;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private StoryServiceDAOImpl() {
        connection = DatabaseConnection.get();
        idSetter = IdSetter.getInstance();
        storySqlInjector = StorySqlInjector.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final StoryServiceDAO storyServiceDAOImpl = new StoryServiceDAOImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryServiceDAOImpl class.
     * </p>
     *
     * @return The singleton instance of StoryServiceDAOImpl class.
     */
    public static StoryServiceDAO getInstance() {
        return InstanceHolder.storyServiceDAOImpl;
    }

    /**
     * {@inheritDoc}
     *
     * @param story The story to be added.
     * @return The ID of the added story if successful, otherwise 0.
     */
    public Long addStory(final Story story) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(storySqlInjector.getInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, story.getUserId());
            preparedStatement.setString(2, story.getText());
            preparedStatement.setBoolean(3, story.isPrivate());
            preparedStatement.setString(4, story.getMusic());
            preparedStatement.setInt(5, story.getMedia().getId());

            if (preparedStatement.executeUpdate() > 0) {
                return idSetter.getId(preparedStatement);
            }

            return null;
        } catch (final SQLException exception) {
            LOGGER.error("Story creation failed");
            throw new StoryCreationFailedException("Story creation failed");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param id The ID of the story to be removed.
     * @return True if the story is successfully removed, otherwise false.
     */
    public boolean removeStory(final Long id) {
        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(storySqlInjector.getDeleteQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (final SQLException exception) {
            LOGGER.error("Story removal failed");
            throw new StoryRemovalFailedException("Story removal failed");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param id The ID of the story to be retrieved.
     * @return The retrieved story, or null if not found.
     */
    public Story getStory(final Long id) {
        final Story story = new Story();

        try (final PreparedStatement preparedStatement = connection
                .prepareStatement(storySqlInjector.getSelectQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setLong(1, id);

            return setStoryUnique(story, preparedStatement.executeQuery());
        } catch (final SQLException exception) {
            LOGGER.error("Story retrival failed");
            throw new StoryRetrivalFailedException("Story retrival failed");
        }
    }

    /**
     * <p>
     * Sets the unique details of a story from the given ResultSet.
     * </p>
     *
     * @param story     The story object to set the details to.
     * @param resultSet The ResultSet containing the story details.
     * @return The story object with the unique details set, or null if not found.
     */
    private Story setStoryUnique(final Story story, final ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                story.setStoryId(resultSet.getLong(1));
                story.setUserId(resultSet.getLong(2));
                story.setUserName(resultSet.getString(3));
                story.setText(resultSet.getString(4));
                story.setPrivate(resultSet.getBoolean(5));
                story.setMusic(resultSet.getString(6));
                story.setTimestamp(resultSet.getTimestamp(7));
            }

            return story;
        } catch (final SQLException exception) {
            LOGGER.error("Resultset insert failed exception");
            throw new StoryException("Resultset insert failed exception");
        }
    }
}
