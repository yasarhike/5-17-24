package org.insta.content.dao.reel.share;

/**
 * <p>
 * Manages the sharing of reels.
 * </p>
 *
 * <p>
 * This interface provides methods to add users who shared a reel and to remove users who unshared a reel.
 * Implementations of this interface should handle the persistence of shared reel data.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */

public interface ReelShareDAO {

    /**
     * <p>
     * Adds a user who shared the reel.
     * </p>
     *
     * @param userId The ID of the user who shared the reel.
     * @param reelId The ID of the reel being shared.
     * @return The ID of the share record if the user is successfully added as a sharer, otherwise 0.
     */
    Long reelShare(final Long userId, final Long reelId);

    /**
     * <p>
     * Removes a user who unshared the reel.
     * </p>
     *
     * @param id The ID of the share record to be removed.
     * @return True if the user is successfully removed as a sharer, otherwise false.
     */
    boolean removeShare(final Long id);
}
