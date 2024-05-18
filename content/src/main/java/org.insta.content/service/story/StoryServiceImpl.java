package org.insta.content.service.story;

import org.insta.content.dao.story.StoryServiceDAO;
import org.insta.content.dao.story.StoryServiceDAOImpl;
import org.insta.content.groups.StoryValidator;
import org.insta.content.model.story.Story;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Implementation class for managing stories.
 * </p>
 *
 * <p>
 * This class provides methods to add, remove, and retrieve stories for users.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryService
 * @see Story
 * @see StoryValidator
 */
public final class StoryServiceImpl implements StoryService {

    private final StoryServiceDAO storyServiceDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryServiceImpl() {
        storyServiceDAO = StoryServiceDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final StoryService storyShareService = new StoryServiceImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryServiceImplementation class.
     * </p>
     *
     * @return The singleton instance of StoryServiceImplementation class.
     */
    public static StoryService getInstance() {
        return InstanceHolder.storyShareService;
    }

    /**
     *{@inheritDoc}
     *
     * @param story The story to add.
     * @return A byte array representing either validation violations or a success response.
     * @see Story
     */
    public byte[] addStory(final Story story) {
        final byte[] violations = objectValidator.validate(story, StoryValidator.class);

        return violations.length > 0 ? violations
                : objectValidator.responseWithID(storyServiceDAO.addStory(story), violations);
    }

    /**
     * {@inheritDoc}
     *
     * @param storyId The ID of the story to remove.
     * @return A byte array representing a manual response.
     */
    public byte[] removeStory(final Long storyId) {
        return objectValidator.manualResponse(storyServiceDAO.removeStory(storyId));
    }

    /**
     * {@inheritDoc}
     *
     * @param storyId The ID of the story to retrieve.
     * @return A byte array representing the retrieved story.
     */
    public byte[] getStory(final Long storyId) {
        return objectValidator.objectResponse(storyServiceDAO.getStory(storyId));
    }
}
