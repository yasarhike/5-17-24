package org.insta.content.service.post.comment;

import org.insta.content.model.Comment;

/**
 * <p>
 * Implemented class for managing post comment operations.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see PostCommentServiceImpl
 */
public interface PostCommentService {


    /**
     * Adds a comment for the post.
     *
     * @param comment the comment to be added
     * @return a byte array representing the result of the operation
     */
    byte[] postComment(final Comment comment);

    /**
     * Deletes a comment for the post.
     *
     * @param id the ID of the comment to be deleted
     * @return a byte array representing the result of the operation
     */
    byte[] deleteComment(final Long id);
}
