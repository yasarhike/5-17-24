package org.insta.content.dao.reel.comment;

import org.insta.content.model.Comment;

/**
 * <p>
 * Managing user reel comment.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface ReelCommentDAO {

    /**
     * <p>
     * Adds a comment for a particular reel.
     * </p>
     *
     * @param comment The comment to be added
     * @return The ID of the added comment, or 0 if unsuccessful
     */
    Long addComment(final Comment comment);

    /**
     * <p>
     * Deletes a comment for a particular reel.
     * </p>
     *
     * @param commentId The ID of the comment to be deleted
     * @return true if the comment is deleted successfully, otherwise false
     */
    boolean deleteComment(final Long commentId);
}
