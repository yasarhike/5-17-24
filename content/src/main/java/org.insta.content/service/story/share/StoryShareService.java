package org.insta.content.service.story.share;

/**
 * <p>
 * Service interface for managing story sharing operations.
 * </p>
 *
 * <p>
 * This interface declares methods to share and unshare stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryShareServiceImpl
 */
public interface StoryShareService {

    /**
     * <p>
     * Shares a story with the specified story ID and user ID.
     * </p>
     *
     * @param storyId The ID of the story to share.
     * @param userId  The ID of the user sharing the story.
     * @return A byte array representing a success response.
     */
    byte[] storyShare(final Long storyId, final Long userId);

    /**
     * <p>
     * Unshares a story with the specified story ID.
     * </p>
     *
     * @param storyId The ID of the story to unshare.
     * @return A byte array representing a manual response.
     */
    byte[] storyUnShare(final Long storyId);
}
