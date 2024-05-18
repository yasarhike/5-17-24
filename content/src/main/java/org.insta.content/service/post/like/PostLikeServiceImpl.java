package org.insta.content.service.post.like;

import org.insta.content.dao.post.like.PostLikeDAO;
import org.insta.content.dao.post.like.PostLikeDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Managing user post like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ObjectValidator
 * @see PostLikeDAO
 */
public final class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeDAO postLikeDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private PostLikeServiceImpl() {
        postLikeDAO = PostLikeDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostLikeService postLikeService = new PostLikeServiceImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of PostLikeServiceImpl class.
     * </p>
     *
     * @return The singleton instance of PostLikeServiceImpl class.
     */
    public static PostLikeService getInstance() {
        return InstanceHolder.postLikeService;
    }

    /**
     *{@inheritDoc}
     *
     * @param userId the ID of the user
     * @param postId the ID of the post
     * @return a byte array representing the result of the operation
     */
    public byte[] postLike(final Long userId, final Long postId) {
        return objectValidator.responseWithID(postLikeDAO.postLike(userId, postId), new byte[]{});
    }

    /**
     * {@inheritDoc}
     *
     * @param postId the ID of the post
     * @return a byte array representing the result of the operation
     */
    public byte[] postUnlike(final Long postId) {
        return objectValidator.manualResponse(postLikeDAO.postUnlike(postId));
    }
}
