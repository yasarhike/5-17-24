package org.insta.content.service.reel;

import org.insta.content.model.reel.Reel;

/**
 * <p>
 * Managing reels service operation.
 * </p>
 *
 * <p>
 * This interface declares methods for managing reels, including adding, removing, and retrieving reels for users.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelServiceImpl
 * @see Reel
 */
public interface ReelService {

    /**
     * <p>
     * Adds a reel for the specified user and sets the timestamp.
     * </p>
     *
     * @param reel The reel to add.
     * @return A byte array representing the result of the operation.
     * @see Reel
     */
    byte[] addReel(final Reel reel);

    /**
     * <p>
     * Removes a reel with the specified ID for the specified user.
     * </p>
     *
     * @param reelId The ID of the reel to remove.
     * @return A byte array representing the result of the operation.
     */
    byte[] removeReel(final Long  reelId);

    /**
     * <p>
     * Retrieves a reel with the specified ID.
     * </p>
     *
     * @param id The ID of the reel to retrieve.
     * @return A byte array representing the retrieved reel.
     */
    byte[] getReel(final Long  id);
}
