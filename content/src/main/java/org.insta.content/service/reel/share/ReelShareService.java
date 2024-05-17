package org.insta.content.service.reel.share;

import org.insta.content.dao.reel.share.ReelShareDAO;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Service interface for managing reel sharing operations.
 * </p>
 *
 * <p>
 * This interface defines methods for adding and removing shares of reels, allowing users to share and unshare reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelShareServiceImpl
 * @see ReelShareDAO
 * @see ObjectValidator
 */
public interface ReelShareService {

    /**
     * <p>
     * Add the people who shared the reel.
     * </p>
     *
     * @param reelId Refers to the reel ID of the reel.
     * @param userId Refers to the ID of the user.
     * @return A byte array representing the result of the operation.
     */
    byte[] reelShare(final Long  userId, final Long  reelId);

    /**
     * <p>
     * Remove the people who unshared the reel.
     * </p>
     *
     * @param id Refers to the ID of the user.
     * @return A byte array representing the result of the operation.
     */
    byte[] removeShare(final Long  id);
}
