package org.insta.content.service.story.share;

import org.insta.content.dao.story.share.StoryShareDAO;
import org.insta.content.dao.story.share.StoryShareDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Implementation class for managing story sharing operations.
 * </p>
 *
 * <p>
 * This class provides methods to share and unshare stories.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see StoryShareService
 * @see StoryShareDAO
 * @see ObjectValidator
 */
public class StoryShareServiceImpl implements StoryShareService {

    private final StoryShareDAO storyShareDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private StoryShareServiceImpl() {
        storyShareDAO = StoryShareDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final StoryShareService storyShareService = new StoryShareServiceImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of StoryShareServiceImpl class.
     * </p>
     *
     * @return The singleton instance of StoryShareServiceImpl class.
     */
    public static StoryShareService getInstance() {
        return InstanceHolder.storyShareService;
    }

    /**
     *{@inheritDoc}
     *
     * @param storyId The ID of the story to share.
     * @param userId  The ID of the user sharing the story.
     * @return A byte array representing a success response.
     */
    @Override
    public byte[] storyShare(final Long storyId, final Long  userId) {
        return objectValidator.responseWithID(storyShareDAO.addShare(storyId, userId), new byte[]{});
    }

    /**
     * {@inheritDoc}
     *
     * @param storyId The ID of the story to unshare.
     * @return A byte array representing a manual response.
     */
    @Override
    public byte[] storyUnShare(final Long storyId) {
        return objectValidator.manualResponse(storyShareDAO.removeShare(storyId));
    }
}
