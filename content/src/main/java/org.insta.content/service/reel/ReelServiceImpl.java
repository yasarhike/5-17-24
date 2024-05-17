package org.insta.content.service.reel;

import org.insta.content.dao.reel.ReelServiceDAO;
import org.insta.content.dao.reel.ReelServiceDAOImpl;
import org.insta.content.groups.ReelValidator;
import org.insta.content.model.reel.Reel;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import java.util.Objects;

/**
 * <p>
 * Implementation class for managing reels.
 * </p>
 *
 * <p>
 * This class provides methods to add, remove, and retrieve reels for users.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelService
 * @see ReelServiceDAO
 * @see ObjectValidator
 */
public final class ReelServiceImpl implements ReelService {

    private final ReelServiceDAO reelServiceDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Private constructor to restrict the object creation outside of the class.
     * </p>
     */
    private ReelServiceImpl() {
        reelServiceDAO = ReelServiceDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelService reelServiceImpl = new ReelServiceImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelServiceImplementaion class.
     * </p>
     *
     * @return The singleton instance of ReelServiceImplementation class.
     */
    public static ReelService getInstance() {
        return InstanceHolder.reelServiceImpl;
    }

    /**
     * <p>
     * Adds a reel for the specified user.
     * </p>
     *
     * @param reel The reel to add.
     * @return A byte array representing either validation violations or a success response.
     * @see Reel
     */
    public byte[] addReel(final Reel reel) {
        final byte[] violations = objectValidator.validate(reel, ReelValidator.class);

        return violations.length > 0 ? violations
                : objectValidator.responseWithID(reelServiceDAO.addReel(reel), violations);
    }

    /**
     * <p>
     * Removes a reel with the specified ID.
     * </p>
     *
     * @param reelId The ID of the reel to remove.
     * @return A byte array representing a manual response.
     */
    public byte[] removeReel(final Long  reelId) {
        return objectValidator.manualResponse(reelServiceDAO.removeReel(reelId));
    }

    /**
     * <p>
     * Retrieves a reel with the specified ID.
     * </p>
     *
     * @param reelId The ID of the reel to retrieve.
     * @return A byte array representing the retrieved reel.
     */
    public byte[] getReel(final Long  reelId) {
        final Reel reel = reelServiceDAO.getReel(reelId);

        return Objects.nonNull(reel) ? objectValidator.objectResponse(reel)
                : objectValidator.manualResponse(false);
    }
}
