package org.insta.content.service.reel.share;

import org.insta.content.dao.reel.share.ReelShareDAO;
import org.insta.content.dao.reel.share.ReelShareDAOImpl;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Service implementation for managing reel sharing operations.
 * </p>
 *
 * <p>
 * This class provides methods for adding and removing shares of reels, allowing users to share and unshare reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelShareService
 * @see ReelShareDAO
 * @see ObjectValidator
 */
public class ReelShareServiceImpl implements ReelShareService {

    private final ReelShareDAO reelShareDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Private constructor to restrict object creation outside of the class.
     * </p>
     */
    private ReelShareServiceImpl() {
        reelShareDAO = ReelShareDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelShareService reelShareService = new ReelShareServiceImpl();
    }

    /**
     * <p>
     * Retrieves the singleton instance of ReelShareServiceImpl class.
     * </p>
     *
     * @return The singleton instance of ReelShareServiceImpl class.
     */
    public static ReelShareService getInstance() {
        return InstanceHolder.reelShareService;
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Refers to the id of the user.
     * @param reelId Refers to the reelId of the reel.
     * @return True if it is added successfully, otherwise false.
     */
    @Override
    public byte[] reelShare(final Long  userId, final Long  reelId) {
        return objectValidator.responseWithID(reelShareDAO.reelShare(userId, reelId), new byte[]{});
    }

    /**
     * {@inheritDoc}
     *
     * @param id Refers to the id of the user.
     * @return True if it is unshared successfully, otherwise false.
     */
    @Override
    public byte[] removeShare(final Long  id) {
        return objectValidator.manualResponse(reelShareDAO.removeShare(id));
    }
}
