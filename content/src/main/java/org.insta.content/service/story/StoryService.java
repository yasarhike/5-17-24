package org.insta.content.service.story;

import org.insta.content.groups.StoryValidator;
import org.insta.content.model.story.Story;

/**
 * <p>
 * manage stories.
 * </p>
 *
 * <p>
 * Provides methods to add, remove, and retrieve stories for users.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryService
 * @see Story
 * @see StoryValidator
 */
public interface StoryService {

    /**
     * <p>
     * Adds a story for the specified user.
     * </p>
     *
     * @param story The story to add.
     * @return A byte array representing either validation violations or a success response.
     * @see Story
     */
    byte[] addStory(final Story story);

    /**
     * <p>
     * Removes a story with the specified ID.
     * </p>
     *
     * @param storyId The ID of the story to remove.
     * @return A byte array representing a manual response.
     */
    byte[] removeStory(final Long storyId);

    /**
     * <p>
     * Retrieves a story with the specified ID.
     * </p>
     *
     * @param storyId The ID of the story to retrieve.
     * @return A byte array representing the retrieved story.
     */
    byte[] getStory(final Long storyId);
}
