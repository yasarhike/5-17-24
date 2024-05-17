package org.insta.content.dao.post.like;

/**
 * <p>
 * Data Access Object interface for managing post likes.
 * </p>
 *
 * <p>
 * This interface provides methods for adding and removing likes for a post.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 */
public interface PostLikeDAO {

    /**
     * <p>
     * Adds a like for a post.
     * </p>
     *
     * @param userId the ID of the user adding the like
     * @param postId the ID of the post to be liked
     * @return the ID of the added like, or 0 if unsuccessful
     */
    Long postLike(final Long userId, final Long postId);

    /**
     * <p>
     * Removes a like for a post.
     * </p>
     *
     * @param postId the ID of the post from which the like is to be removed
     * @return true if the like is removed successfully, otherwise false
     */
    boolean postUnlike(final Long postId);
}
