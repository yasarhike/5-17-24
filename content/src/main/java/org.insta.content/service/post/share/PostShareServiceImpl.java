package org.insta.content.service.post.share;

import org.insta.content.dao.post.share.PostShareDAO;
import org.insta.content.dao.post.share.PostShareDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Manage post share.
 * </p>
 *
 * @see PostShareService
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class PostShareServiceImpl implements PostShareService{

    private final PostShareDAO postShareDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostShareServiceImpl() {
        postShareDAO = PostShareDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostShareService postShareService = new PostShareServiceImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of PostShareServiceImpl class.
     * </p>
     *
     * @return The singleton instance of PostShareServiceImpl class.
     */
    public static PostShareService getInstance() {
        return InstanceHolder.postShareService;
    }

    /**
     * <p>
     * Shares a post.
     * </p>
     *
     * @param postId the ID of the post to be shared
     * @param userId the ID of the user sharing the post
     * @return a byte array representing the result of the operation
     */
    @Override
    public byte[] postShare(final Long postId, final Long userId) {
        return objectValidator.responseWithID(postShareDAO.postShare(userId, postId), new byte[]{});
    }

    /**
     * <p>
     * Unshares a post.
     * </p>
     *
     * @param shareId the ID of the post share to be removed
     * @return a byte array representing the result of the operation
     */
    @Override
    public byte[] removeShare(final Long shareId) {
        return objectValidator.manualResponse(postShareDAO.removeShare(shareId));
    }
}
