package org.insta.content.service.reel.comment;

import org.insta.content.dao.reel.comment.ReelCommentDAO;
import org.insta.content.dao.reel.comment.ReelCommentDAOImpl;
import org.insta.content.groups.CommentValidator;
import org.insta.content.model.Comment;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Service implementation for managing comments on reels.
 * </p>
 *
 * <p>
 * This class provides methods for adding and deleting comments on reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelCommentService
 * @see ReelCommentDAO
 * @see ObjectValidator
 */
public class ReelCommentServiceImpl implements ReelCommentService {

    private final ReelCommentDAO reelCommentDAOImpl;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Restrict object creation outside of the class
     * </p>
     */
    private ReelCommentServiceImpl() {
        reelCommentDAOImpl = ReelCommentDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final ReelCommentService reelCommentService = new ReelCommentServiceImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of ReelCommentServiceImpl class.
     * </p>
     *
     * @return The singleton instance of ReelCommentServiceImpl class.
     */
    public static ReelCommentService getInstance() {
        return InstanceHolder.reelCommentService;
    }

    /**
     * <p>
     * Add a comment for the particular reel.
     * </p>
     *
     * @param comment Refers to the {@link Comment} object representing the comment.
     * @return A byte array representing the result of the operation.
     */
    @Override
    public byte[] addComment(Comment comment) {
        final byte[] violations = objectValidator.validate(comment, CommentValidator.class);

        return violations.length > 0 ? violations
                : objectValidator.responseWithID(reelCommentDAOImpl.addComment(comment), violations);
    }

    /**
     * <p>
     * Delete a comment for the particular reel.
     * </p>
     *
     * @param commentId Refers to the ID of the comment.
     * @return A byte array representing the result of the operation.
     */
    @Override
    public byte[] deleteComment(final Long commentId) {
        return objectValidator.manualResponse(reelCommentDAOImpl.deleteComment(commentId));
    }
}
