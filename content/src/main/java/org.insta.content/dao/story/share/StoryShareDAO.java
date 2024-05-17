package org.insta.content.dao.story.share;

/**
 * <p>
 * Interface for managing story sharing operations in the data access layer.
 * </p>
 * <p>
 * Defines methods for adding and removing shares for stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface StoryShareDAO {

    /**
     * <p>
     * Adds a share for the specified story.
     * </p>
     *
     * @param storyId  The ID of the story to be shared.
     * @param sharedBy The ID of the user who shared the story.
     * @return The ID of the added share if successful, otherwise 0.
     */
    Long addShare(final Long storyId, final Long sharedBy);

    /**
     * <p>
     * Removes a share for the specified story.
     * </p>
     *
     * @param storyId The ID of the story to be unshared.
     * @return True if the share is successfully removed, otherwise false.
     */
    boolean removeShare(final Long storyId);
}
