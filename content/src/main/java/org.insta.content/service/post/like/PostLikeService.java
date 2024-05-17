package org.insta.content.service.post.like;

/**
 * <p>
 * Service interface for managing user post likes.
 * </p>
 *
 * <p>
 * This interface provides methods for adding and removing likes for a post.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostLikeServiceImpl
 */
public interface PostLikeService {

    /**
     * <p>
     * Adds a like for the particular post.
     * </p>
     *
     * @param userId the ID of the user
     * @param postId the ID of the post
     * @return a byte array representing the result of the operation
     */
    byte[] postLike(final Long userId, final Long postId);

    /**
     * <p>
     * Removes a like for a particular post.
     * </p>
     *
     * @param postId the ID of the post
     * @return a byte array representing the result of the operation
     */
    byte[] postUnlike(final Long postId);
}
