package org.insta.content.dao.reel.like;

/**
 * <p>
 * Managing user reel like.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface ReelLikeDAO {

    /**
     * <p>
     * Adds a like for a particular reel.
     * </p>
     *
     * @param reelId The ID of the reel to like
     * @param userId The ID of the user performing the like
     * @return The ID of the added like, or 0 if unsuccessful
     */
    Long reelLike(final Long reelId, final Long userId);

    /**
     * <p>
     * Unlikes a particular reel.
     * </p>
     *
     * @param reelId The ID of the reel to unlike
     * @return true if the reel is unliked successfully, otherwise false
     */
    boolean reelUnlike(final Long reelId);
}
