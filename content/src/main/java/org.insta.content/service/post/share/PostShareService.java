package org.insta.content.service.post.share;

/**
 * <p>
 * Service interface for managing post sharing.
 * </p>
 *
 * <p>
 * This interface provides methods for sharing and unsharing posts.
 * </p>
 *
 * @see PostShareServiceImpl
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 */
public interface PostShareService {

    /**
     * <p>
     * Shares a post.
     * </p>
     *
     * @param postId the ID of the post to be shared
     * @param userId the ID of the user sharing the post
     * @return a byte array representing the result of the operation
     */
    byte[] postShare(final Long postId, final Long userId);

    /**
     * <p>
     * Unshares a post.
     * </p>
     *
     * @param shareId the ID of the post share to be removed
     * @return a byte array representing the result of the operation
     */
    byte[] removeShare(final Long shareId);
}
