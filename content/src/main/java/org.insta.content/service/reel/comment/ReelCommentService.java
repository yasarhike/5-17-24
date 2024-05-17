package org.insta.content.service.reel.comment;

import org.insta.content.model.Comment;

/**
 * <p>
 * Managing user reel comments.
 * </p>
 *
 * <p>
 * This interface defines methods for adding and deleting comments on reels.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see ReelCommentServiceImpl
 */
public interface ReelCommentService {

    /**
     * <p>
     * Add a comment for the particular reel.
     * </p>
     *
     * @param comment Refers to the {@link Comment} object representing the comment.
     * @return A byte array representing the result of the operation.
     */
    byte[] addComment(final Comment comment);

    /**
     * <p>
     * Delete a comment for the particular reel.
     * </p>
     *
     * @param commentId Refers to the ID of the comment.
     * @return A byte array representing the result of the operation.
     */
    byte[] deleteComment(final Long commentId);
}
