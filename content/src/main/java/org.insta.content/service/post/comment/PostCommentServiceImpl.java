package org.insta.content.service.post.comment;

import org.insta.content.dao.post.comment.PostCommentDAO;
import org.insta.content.dao.post.comment.PostCommentDAOImpl;
import org.insta.content.groups.CommentValidator;
import org.insta.content.model.Comment;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

/**
 * <p>
 * Managing user post comment.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentDAO postCommentDAO;
    private final ObjectValidator objectValidator;

    /**
     * <p>
     * Restrict the object creation outside of the class.
     * </p>
     */
    private PostCommentServiceImpl() {
        postCommentDAO = PostCommentDAOImpl.getInstance();
        objectValidator = ObjectValidator.getInstance();
    }

    /**
     * <p>
     *  Static class for creating singleton instance.
     * </p>
     */
    private static class InstanceHolder {

        private static final PostCommentService postCommentService = new PostCommentServiceImpl();
    }

    /**
     * <p>
     * Returns the singleton instance of PostCommentServiceImpl class.
     * </p>
     *
     * @return The singleton instance of PostCommentServiceImpl class.
     */
    public static PostCommentService getInstance() {
        return InstanceHolder.postCommentService;
    }

    /**
     * <p>
     * Adds a comment for the post.
     * </p>
     *
     * @param comment the comment to be added
     * @return a byte array representing the result of the operation
     */
    public byte[] postComment(final Comment comment) {
        final byte[] violations = objectValidator.validate(comment, CommentValidator.class);

        return violations.length > 0 ? violations
                : objectValidator.responseWithID(postCommentDAO.postComment(comment), violations);
    }

    /**
     * <p>
     * Deletes a comment for the post.
     * </p>
     *
     * @param id the ID of the comment to be deleted
     * @return a byte array representing the result of the operation
     */
    public byte[] deleteComment(final Long id) {
        return objectValidator.manualResponse(postCommentDAO.deleteComment(id));
    }
}
