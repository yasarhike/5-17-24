package org.insta.content.dao.story;

import org.insta.content.model.story.Story;

/**
 * <p>
 * managing story service operation.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface StoryServiceDAO {

    /**
     * <p>
     * Adds a story for the specified user.
     * </p>
     *
     * @param story The story to be added.
     * @return The ID of the added story if successful, otherwise 0.
     */
    Long addStory(final Story story);

    /**
     * <p>
     * Removes a story with the specified ID.
     * </p>
     *
     * @param id The ID of the story to be removed.
     * @return True if the story is successfully removed, otherwise false.
     */
    boolean removeStory(final Long id);

    /**
     * <p>
     * Retrieves a story with the specified ID.
     * </p>
     *
     * @param id The ID of the story to be retrieved.
     * @return The retrieved story, or null if not found.
     */
    Story getStory(final Long id);
}
