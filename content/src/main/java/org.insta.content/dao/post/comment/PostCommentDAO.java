package org.insta.content.dao.post.comment;

import org.insta.content.model.Comment;

/**
 * <p>
 * Data Access Object interface for managing post comments.
 * </p>
 *
 * <p>
 * This interface provides methods for adding and deleting comments for a post.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0, 6 Feb 2024
 * @see PostCommentDAOImpl
 */
public interface PostCommentDAO {

    /**
     * <p>
     * Adds a comment for the post.
     * </p>
     *
     * @param comment the comment to be added
     * @return the ID of the added comment, or 0 if unsuccessful
     */
    Long postComment(final Comment comment);

    /**
     * <p>
     * Deletes a comment for the post.
     * </p>
     *
     * @param id the ID of the comment to be deleted
     * @return true if the comment is deleted successfully, otherwise false
     */
    boolean deleteComment(final Long id);
}
