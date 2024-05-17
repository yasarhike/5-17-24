package org.insta.content.service.reel.like;

/**
 * <p>
 * Service for managing reel liking operations.
 * </p>
 *
 * <p>
 * Provides methods for adding and removing likes on reels, allowing users to like and unlike reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelLikeServiceImpl
 */
public interface ReelLikeService {

    /**
     * <p>
     * Remove a like for the particular reel.
     * </p>
     *
     * @param reelId Refers to the id of the reel.
     * @param userId Refers to the id of the user.
     * @return A byte array representing the result of the operation.
     */
    public byte[] reelLike(final Long reelId, final Long userId);

    /**
     * <p>
     * Remove a like for the particular reel.
     * </p>
     *
     * @param id Refers to the ID of the user.
     * @return A byte array representing the result of the operation.
     */

    public byte[] reelUnlike(final Long id);
}
