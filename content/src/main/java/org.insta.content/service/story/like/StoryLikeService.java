package org.insta.content.service.story.like;

/**
 * <p>
 * Service interface for managing story liking operations.
 * </p>
 *
 * <p>
 * This interface declares methods to like and unlike stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryLikeServiceImpl
 */
public interface StoryLikeService {

    /**
     * <p>
     * Adds a like for the particular story.
     * </p>
     *
     * @param userId  The ID of the user liking the story.
     * @param storyId The ID of the story to like.
     * @return A byte array representing a success response.
     */
    byte[] storyLike(final Long userId, final Long storyId);

    /**
     * <p>
     * Removes a like for the particular story.
     * </p>
     *
     * @param id The ID of the story to unlike.
     * @return A byte array representing a manual response.
     */
    byte[] storyUnlike(final Long id);
}
