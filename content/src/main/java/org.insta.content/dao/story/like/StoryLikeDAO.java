package org.insta.content.dao.story.like;

/**
 * <p>
 * Data Access Object (DAO) interface for managing story likes.
 * </p>
 *
 * <p>
 * Allows adding and removing likes for stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface StoryLikeDAO {

    /**
     * <p>
     * Adds a like for the specified story by the user.
     * </p>
     *
     * @param userId  The ID of the user who likes the story.
     * @param storyId The ID of the story to be liked.
     * @return The ID of the inserted like if successful, otherwise 0.
     */
    Long storyLike(final Long userId, final Long storyId);

    /**
     * <p>
     * Removes a like for the specified story.
     * </p>
     *
     * @param id The ID of the story like to be removed.
     * @return True if the like is removed successfully, otherwise false.
     */
    boolean storyUnlike(final Long id);
}
