package org.insta.content.dao.post.share;

/**
 * <p>
 * Data Access Object interface for managing post shares.
 * </p>
 *
 * <p>
 * This interface provides methods for sharing and unsharing posts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostShareDAOImpl
 */
public interface PostShareDAO {

    /**
     * <p>
     * Shares a post.
     * </p>
     *
     * @param postId the ID of the post to be shared
     * @param userId the ID of the user sharing the post
     * @return the ID of the added share, or 0 if unsuccessful
     */
    Long postShare(final Long postId, final Long userId);

    /**
     * <p>
     * Unshares a post.
     * </p>
     *
     * @param shareId the ID of the share to be removed
     * @return true if the share is removed successfully, otherwise false
     */
    boolean removeShare(final Long shareId);
}
